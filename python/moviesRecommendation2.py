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
				StringType(), True), StructField('genre', StringType(),
				True)])

		ratingsCustomSchema = StructType([StructField('userId',
				IntegerType(), True), StructField('movieId',
				IntegerType(), True), StructField('rating',
				DoubleType(), True)])
		movies_df = sqlContext.read.format('csv').options(header='true'
				, inferSchema='true',
				schema=moviesCustomSchema).load('C://movies.csv').withColumnRenamed("movieId", "ID")
		movies_df = movies_df.cache()
		ratings_df = sqlContext.read.format('csv').options(header='true'
				, inferSchema='true',
				schema=ratingsCustomSchema).load('C://ratings.csv')
		ratings_df = ratings_df.drop('timestamp')
		ratings_df = ratings_df.cache()
		DictionaryOfDf = {'movies_df': movies_df,
						  'ratings_df': ratings_df}
		return DictionaryOfDf

	def topRatedMovies(self,movies_df,ratings_df):

		# movies_df = movies_df.drop('genres')
		movie_names_with_avg_ratings_df = ratings_df.groupBy('movieId'
				).agg({'rating': 'avg', 'userId': 'count'
					  }).withColumnRenamed('avg(rating)', 'average'
				).withColumnRenamed('count(userId)', 'count')

		moviesRatingsJoined_df = \
			movies_df.join(movie_names_with_avg_ratings_df,
						   movies_df.ID
						   == movie_names_with_avg_ratings_df.movieId,
						   'inner')

		moviesRatingsJoined_df = \
			moviesRatingsJoined_df.sort(moviesRatingsJoined_df.average.desc()).drop('ID'
				)

		moviesWithHighestRatingWithCountMoreThan500 = \
			moviesRatingsJoined_df.filter('count >= 500')

		DictionaryOfDf = \
			{'movie_names_with_avg_ratings_df': movie_names_with_avg_ratings_df,
			 'moviesRatingsJoined_df': moviesRatingsJoined_df,
			 'moviesWithHighestRatingWithCountMoreThan500': moviesWithHighestRatingWithCountMoreThan500}

		return DictionaryOfDf

	def splitDataset(self, ratings_df):
		

		(split_60_df, split_a_20_df, split_b_20_df) = \
			ratings_df.randomSplit([0.6, 0.2, 0.2], 123)

		training_df = split_60_df.cache()
		validation_df = split_a_20_df.cache()
		test_df = split_b_20_df.cache()

		SplitDictionary = {'training_df': training_df,
						   'validation_df': validation_df,
						   'test_df': test_df}

		return SplitDictionary

	def alternatingLeastSquare(self, training_df, validation_df):

		als = ALS(maxIter=5, regParam=0.01, userCol='userId',
				  itemCol='movieId', ratingCol='rating')
		model = als.fit(training_df)

		# Create an RMSE evaluator using the label and predicted columns

		reg_eval = RegressionEvaluator(predictionCol='prediction',
				labelCol='rating', metricName='rmse')

		tolerance = 0.03
		ranks = [4, 8, 12]
		errors = []
		models = []
		min_error = float('inf')
		best_rank = -1
		for rank in ranks:

		  # Set the rank here:

			als.setRank(rank)

		  # Create the model with these parameters.

			model = als.fit(training_df)

		  # Run the model to create a prediction. Predict against the validation_df.

			predict_df = model.transform(validation_df)

		  # Remove NaN values from prediction (due to SPARK-14489)

			predicted_ratings_df = \
				predict_df.filter(predict_df.prediction != float('nan'))

		  # Run the previously created RMSE evaluator, reg_eval, on the predicted_ratings_df DataFrame

			error = reg_eval.evaluate(predicted_ratings_df)
			errors.append(error)
			models.append(model)
			if error < min_error:
				min_error = error
				best_rank = rank
				my_model = model

		als.setRank(best_rank)
		modelDictionary = { "best_rank": best_rank, "model": my_model }
		return modelDictionary

	def testModel(self, my_model, test_df):
		predict_df = my_model.transform(test_df)

		# Remove NaN values from prediction (due to SPARK-14489)

		predicted_test_df = predict_df.filter(predict_df.prediction
				!= float('nan'))

		# Run the previously created RMSE evaluator, reg_eval, on the predicted_test_df DataFrame

		reg_eval = RegressionEvaluator(predictionCol='prediction',
				labelCol='rating', metricName='rmse')
		test_RMSE = reg_eval.evaluate(predicted_test_df)

		return test_RMSE

	def createDfFromUserRatings(self,my_rated_movies):

		my_ratings_df = sqlContext.createDataFrame(my_rated_movies,
				['userId', 'movieId', 'rating'])
		return my_ratings_df

	def addAndTrainModel(self,training_df, my_ratings_df):

		# Add to the training set

		training_with_my_ratings_df = \
			training_df.unionAll(my_ratings_df)

		# Reset the parameters for the ALS object.

		als = ALS(maxIter=5, regParam=0.01, userCol='userId',
				  itemCol='movieId', ratingCol='rating')

		als.setPredictionCol('prediction'
							 ).setMaxIter(5).setSeed(123).setRegParam(0.1).setUserCol('userId'
				).setItemCol('movieId').setRatingCol('rating'
				).setRank(best_rank)

		# Create the model with these parameters.

		my_ratings_model = als.fit(training_with_my_ratings_df)

		return my_ratings_model

	def predictRatings(self,
		my_rated_movies,
		movies_df,
		my_ratings_model,
		movie_names_with_avg_ratings_df,
		moviesRatingsJoined_df
		):

		my_rated_movie_ids = [x[1] for x in my_rated_movies]

		# Filter out the movies I already rated.

		not_rated_df = movies_df.filter(~movies_df['ID'
				].isin(my_rated_movie_ids))

		# Rename the "ID" column to be "movieId", and add a column with my_user_id as "userId".

		my_unrated_movies_df = not_rated_df.selectExpr('ID as movieId'
				).withColumn('userId', F.lit(my_user_id))

		# Use my_rating_model to predict ratings for the movies that I did not manually rate.

		raw_predicted_ratings_df = \
			my_ratings_model.transform(my_unrated_movies_df)

		predicted_ratings_df = \
			raw_predicted_ratings_df.filter(raw_predicted_ratings_df['prediction'
				] != float('nan')).withColumnRenamed("movieId", "ID")

		# Join your predicted_ratings_df DataFrame with the movie_names_with_avg_ratings_df DataFrame to obtain the ratings counts for each movie

		predicted_with_counts_df = \
			predicted_ratings_df.join(moviesRatingsJoined_df,
				moviesRatingsJoined_df['movieId']
				== predicted_ratings_df['ID']).drop('ID')
		predicted_with_counts_df=predicted_with_counts_df.sort(predicted_with_counts_df.prediction.desc()).filter('count >= 50').filter('prediction >= 3.0')
		

		print 'My 25 highest rated movies as predicted (for movies with more than 50 reviews):'
		return predicted_with_counts_df 


if __name__ == '__main__':
	movieRecommend = MovieRecommendation()
	dictionaryOfDf = movieRecommend.createDf()
	movies_df = dictionaryOfDf["movies_df"]
	ratings_df = dictionaryOfDf["ratings_df"]
	topRatedMovies = movieRecommend.topRatedMovies(movies_df, ratings_df)
	movie_names_with_avg_ratings_df = topRatedMovies['movie_names_with_avg_ratings_df']
	moviesRatingsJoined_df = topRatedMovies['moviesRatingsJoined_df']
	splitDictionary = movieRecommend.splitDataset( ratings_df)
	training_df = splitDictionary["training_df"]
	validation_df = splitDictionary["validation_df"]
	test_df = splitDictionary["test_df"]
	modelDictionary = movieRecommend.alternatingLeastSquare(training_df, validation_df)
	best_rank = modelDictionary["best_rank"]
	model = modelDictionary["model"]
test_RMSE = movieRecommend.testModel(model, test_df)
my_user_id=0
my_rated_movies = [
	  (my_user_id, 318, 5.5),
	  (my_user_id, 858, 4.1),
	  (my_user_id, 58559, 5.0),
	  (my_user_id, 3475, 1.50),
	  (my_user_id, 589, 4.0),
	  (my_user_id, 44191, 4.0),
	  (my_user_id, 89745, 5.0),
	  (my_user_id, 63082, 4.0),
	  (my_user_id, 88125, 2.0),
	  (my_user_id, 1293, 0.5),
	  (my_user_id, 116797, 5.0)
]
my_ratings_df = movieRecommend.createDfFromUserRatings(my_rated_movies)
my_ratings_model = movieRecommend.addAndTrainModel(training_df, my_ratings_df)

predicted_highest_rated_movies_df = movieRecommend.predictRatings(my_rated_movies,movies_df,my_ratings_model,movie_names_with_avg_ratings_df,moviesRatingsJoined_df)
list_of_predictions = map(lambda row: row.asDict(), predicted_highest_rated_movies_df.collect())
dict_of_predictions = {person['movieId']: person for person in list_of_predictions}

	