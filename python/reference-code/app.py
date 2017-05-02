from pyspark.sql import SQLContext
from flask import Blueprint
main = Blueprint('main', __name__)

import json
from moviesRecommendation import MovieRecommendation
 
import logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)
 
from flask import Flask, request
 
@main.route("/<int:user_id>/ratings/top/<int:count>", methods=["GET"])
def top_ratings(user_id, count):
    print user_id
    logger.debug("User %s TOP ratings requested", user_id)
    top_ratings = recommendation_engine.get_top_ratings(user_id,count)
    print "recommendation:"
    print json.dumps(top_ratings)
    # return json.dumps(top_ratings)
    return json.dumps(top_ratings)

@main.route("/<int:user_id>/ratings", methods = ["POST"])
def add_ratings(user_id):
    # get the ratings from the Flask POST request object
    print request
    ratings_list = request.form.keys()[0].strip().split("\n")
    print ratings_list
    ratings_list = map(lambda x: x.split(","), ratings_list)
    # create a list with the format required by the negine (user_id, movie_id, rating)
    ratings = map(lambda x: (user_id, int(x[0]), int(x[1])), ratings_list)
    # add them to the model using then engine API
    recommendation_engine.add_ratings(ratings)

    return json.dumps(ratings)


 
def create_app(spark_context):
    global recommendation_engine
    sqlContext = SQLContext(spark_context)
    recommendation_engine = MovieRecommendation(spark_context,sqlContext)
    
    app = Flask(__name__)
    app.register_blueprint(main)
    return app 
