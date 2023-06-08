package com.grey.algorithms.reference

import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


/**
 *
 * @param spark: An instance of SparkSession
 */
class SamplingPoint(spark: SparkSession) {

  /**
   *
   * @param reference: The raw data
   */
  def samplingPoint(reference: Dataset[Row], subareaFrame: Dataset[Row], samplingPointTypesFrame: Dataset[Row]): Unit = {

    import spark.implicits._


    // Addressing the <dot> issue
    val blob = reference.toDF(reference.columns.map(_.replace(".", "_")): _*)


    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "comment", "easting", "northing", "lat", "long",
      "area_label", "subArea_label", "samplingPointType_label", "samplingPointStatus_label")
    val synonyms = Seq("sampling_point_id", "sampling_point_desc", "sampling_point_definition",
      "easting", "northing", "latitude", "longitude", "area_desc", "subarea_desc", "sampling_point_type_desc",
      "sampling_point_state")


    // The relevant area columns
    var data: DataFrame = blob.selectExpr(names: _*)


    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }
    data.show()


    // Identifier Coding
    data = data.join(subareaFrame, Seq("subarea_desc"), joinType = "left").drop(
      $"area_desc", $"subarea_desc")
    data.show()

    data = data.join(samplingPointTypesFrame.select($"sampling_point_type_id", $"sampling_point_type_desc"),
      Seq("sampling_point_type_desc"), joinType = "left").drop($"sampling_point_type_desc")
    data.show()



  }

}
