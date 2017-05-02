#!/usr/bin/python
# -*- coding: utf-8 -*-

import os
from pyspark.sql.types import *
import time
from pyspark.sql import SQLContext
from pyspark.sql.types import *
from pyspark.ml.recommendation import ALS
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.sql import Row
from pyspark.sql import functions as F


class MovieRecommendation:
    def createDf(self):

        moviesCustomSchema = StructType([StructField('movieID',
                                                     IntegerType(), True), StructField('title',
                                                                                       StringType(), True),
                                         StructField('genre', StringType(),
                                                     True)])

        ratingsCustomSchema = StructType([StructField('userId',
                                                      IntegerType(), True), StructField('movieId',
                                                                                        IntegerType(), True),
                                          StructField('rating',
                                                      DoubleType(), True)])
        self.movies_df = self.sqlContext.read.format("jdbc").option("url", "jdbc:mysql://127.0.0.1:3306/music").option("driver", "com.mysql.jdbc.Driver").option("dbtable", "movies").option("user", "root").option("password", "root").load()
        self.movies_df = self.movies_df.withColumnRenamed("movieId","ID")
        self.movies_df = self.movies_df.cache()
        self.ratings_df = self.sqlContext.read.format("jdbc").option("url", "jdbc:mysql://127.0.0.1:3306/music").option("driver", "com.mysql.jdbc.Driver").option("dbtable", "ratings").option("user", "root").option("password", "root").load()
        self.ratings_df = self.ratings_df.drop('timestamp')
        self.ratings_df = self.ratings_df.cache()

    def topRatedMovies(self):

        # movies_df = movies_df.drop('genres')
        self.movie_names_with_avg_ratings_df = self.ratings_df.groupBy('movieId'
                                                             ).agg({'rating': 'avg', 'userId': 'count'
                                                                    }).withColumnRenamed('avg(rating)', 'average'
                                                                                         ).withColumnRenamed(
            'count(userId)', 'count')

        self.moviesRatingsJoined_df = \
            self.movies_df.join(self.movie_names_with_avg_ratings_df,
                           self.movies_df.ID
                           == self.movie_names_with_avg_ratings_df.movieId,
                           'inner')

        self.moviesRatingsJoined_df = \
            self.moviesRatingsJoined_df.sort(self.moviesRatingsJoined_df.average.desc()).drop('ID'
                                                                                    )

        self.moviesWithHighestRatingWithCountMoreThan500 = \
            self.moviesRatingsJoined_df.filter('count >= 500')
      
        
    def splitDataset(self):

        (split_60_df, split_a_20_df, split_b_20_df) = \
            self.ratings_df.randomSplit([0.6, 0.2, 0.2], 123)

        self.training_df = split_60_df.cache()
        self.validation_df = split_a_20_df.cache()
        self.test_df = split_b_20_df.cache()

    def alternatingLeastSquare(self):

        self.als = ALS(maxIter=5, regParam=0.01, userCol='userId',
                  itemCol='movieId', ratingCol='rating')
        model = self.als.fit(self.training_df)

        # Create an RMSE evaluator using the label and predicted columns

        self.reg_eval = RegressionEvaluator(predictionCol='prediction',
                                       labelCol='rating', metricName='rmse')

        self.tolerance = 0.03
        self.ranks = [4, 8, 12]
        self.errors = []
        self.models = []
        self.min_error = float('inf')
        self.best_rank = -1
        for rank in self.ranks:

            # Set the rank here:

            self.als.setRank(rank)

            # Create the model with these parameters.

            model = self.als.fit(self.training_df)

            # Run the model to create a prediction. Predict against the validation_df.

            self.predict_df = model.transform(self.validation_df)

            # Remove NaN values from prediction (due to SPARK-14489)

            self.predicted_ratings_df = \
                self.predict_df.filter(self.predict_df.prediction != float('nan'))

            # Run the previously created RMSE evaluator, reg_eval, on the predicted_ratings_df DataFrame

            error = self.reg_eval.evaluate(self.predicted_ratings_df)
            self.errors.append(error)
            self.models.append(model)
            if error < self.min_error:
                self.min_error = error
                self.best_rank = rank
                self.my_model = model

        self.als.setRank(self.best_rank)
       
    def testModel(self):
        self.predict_df = self.my_model.transform(self.test_df)

        # Remove NaN values from prediction (due to SPARK-14489)

        predicted_test_df = self.predict_df.filter(self.predict_df.prediction
                                              != float('nan'))

        # Run the previously created RMSE evaluator, reg_eval, on the predicted_test_df DataFrame

        self.reg_eval = RegressionEvaluator(predictionCol='prediction',
                                       labelCol='rating', metricName='rmse')
        test_RMSE = self.reg_eval.evaluate(predicted_test_df)

        return test_RMSE


    def get_top_ratings(self,user_id,movies_count):

        ratings_df_for_user = self.ratings_df.filter('userId='+str(user_id)+'')
        ratings_df_for_user.show(5);
        list_of_movies_row = ratings_df_for_user.select('movieId').collect()
        my_rated_movie_ids = [i.movieId for i in list_of_movies_row]
        not_rated_df = self.movies_df.filter(~self.movies_df['ID'
        ].isin(my_rated_movie_ids))

        # Rename the "ID" column to be "movieId", and add a column with my_user_id as "userId".
        print my_rated_movie_ids

        my_unrated_movies_df = not_rated_df.selectExpr('ID as movieId'
                                                       ).withColumn('userId', F.lit(user_id))

        # Use my_rating_model to predict ratings for the movies that I did not manually rate.
        my_unrated_movies_df.show(5)
        raw_predicted_ratings_df = \
            self.my_model.transform(my_unrated_movies_df)

        predicted_ratings_df = \
            raw_predicted_ratings_df.filter(raw_predicted_ratings_df['prediction'
                                            ] != float('nan')).withColumnRenamed("movieId", "ID")
        predicted_ratings_df.show(5)
        # Join your predicted_ratings_df DataFrame with the movie_names_with_avg_ratings_df DataFrame to obtain the ratings counts for each movie

        predicted_with_counts_df = \
            predicted_ratings_df.join(self.moviesRatingsJoined_df,
                                      self.moviesRatingsJoined_df['movieId']
                                      == predicted_ratings_df['ID']).drop('ID')
        predicted_with_counts_df = predicted_with_counts_df.sort(predicted_with_counts_df.prediction.desc()).filter(
            'count >= 50').filter('prediction >= 3.0')
        list_of_predictions = map(lambda row: row.asDict(), predicted_with_counts_df.collect())
        return list_of_predictions

    def add_ratings(self,ratings):
        print ratings
    	# my_ratings_df = self.sqlContext.createDataFrame(ratings,['userId', 'movieId', 'rating'])
        self.ratings_df = self.sqlContext.read.format("jdbc").option("url", "jdbc:mysql://127.0.0.1:3306/music").option("driver", "com.mysql.jdbc.Driver").option("dbtable", "ratings").option("user", "root").option("password", "root").load()
    	# self.ratings_df = self.ratings_df.unionAll(my_ratings_df)
    	self.topRatedMovies()
    	self.als = ALS(maxIter=5, regParam=0.01, userCol='userId',
                  itemCol='movieId', ratingCol='rating')

        self.als.setPredictionCol('prediction'
                             ).setMaxIter(5).setSeed(123).setRegParam(0.1).setUserCol('userId'
                                                                                      ).setItemCol(
            'movieId').setRatingCol('rating'
                                    ).setRank(self.best_rank)

        # Create the model with these parameters.

        self.my_model = self.als.fit(self.ratings_df)

        return ratings


    def __init__(self, sc, sqlcontext):
        self.sqlContext = SQLContext(sc)
   	self.createDf()
        self.topRatedMovies()
	self.splitDataset()
 	self.alternatingLeastSquare()
	test_RMSE = self.testModel()


