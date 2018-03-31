import org.apache.spark._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.log4j.{Level, Logger}

import scala.io.Source

object twitterScala extends App{

  for(line <- Source.fromFile("/home/ncarvalho/Documentos/twitter.txt").getLines){
    val fields = line.split(" ")
    if(fields.length == 2){
      System.setProperty("twitter4j.oauth." + fields(0), fields(1))
    }
  }

  val sparkConf = new SparkConf().setMaster("local[*]").setAppName("TwitterPopularTags")
  val ssc = new StreamingContext(sparkConf, Seconds(5))

  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)

  val tweets = TwitterUtils.createStream(ssc, None)
  val statuses = tweets.map(status => status.getText)
  val split = statuses.map(text => text.split(" "))

  val hashTags = statuses.filter(word => word.matches("#"))
  val hashTagKeyValues = hashTags.map(hashtag => (hashtag, 1))
  val hashtagCounts = hashTagKeyValues.reduceByKeyAndWindow((x, y) => x + y, (x, y) => x+y , Seconds(200), Seconds(10))
  var sortedResults = hashtagCounts.transform(rdd => rdd.sortBy(x => x._2, false))


  sortedResults.print(200)
  ssc.checkpoint("checkpoints")
  ssc.start()
  ssc.awaitTermination()

}
