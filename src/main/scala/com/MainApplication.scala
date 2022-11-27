package com
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession



object MainApplication {
    def main(args: Array[String]) {

        val sparkConf = new SparkConf().setAppName("Keyword Data")
        val sc = new SparkContext(sparkConf)
        val spark = SparkSession.builder.getOrCreate()
        import spark.implicits._

        val json_path = "/Users/faurelgema/Documents/scala/simple-transofrming/src/raw_json/*.json"
        val df = spark.read.json(json_path)
        val df_2 = spark.read.json(json_path)

        var xp_df_1 = df.withColumn("term_flat", explode(df("keyword_data")))
        var xp_df_2 = xp_df_1.drop(xp_df_1.col("keyword_data"))

        var xp_df_data_points = xp_df_2.withColumn("data_points", xp_df_2("term_flat.data_points"))

        var xp_df_name = xp_df_data_points.withColumn("m_guid", xp_df_data_points("measure.name"))
        var xp_df_name_val = xp_df_name.withColumn("m_name", xp_df_name("measure.value"))

        var xp_df_final = xp_df_name_val.drop(xp_df_name_val.col("term_flat"))
        var final_df = xp_df_final.drop(xp_df_final.col("data_points"))

        final_df.write
            .format("com.databricks.spark.csv")
            .option("header", "true")
            .save("social_media_data.csv")
    }
}