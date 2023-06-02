package com.grey.algorithms.reference

import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

class SamplingPoint(spark: SparkSession) {

  def samplingPoint(reference: Dataset[Row]): Unit = {

    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "comment", "easting", "northing", "lat", "long", "")
    val synonyms = Seq("sampling_point_id", "sampling_point_desc", "definition", "unit_of_measure", "unit_of_measure_desc")

    // The relevant area columns
    var data: DataFrame = reference.selectExpr(names: _*)

    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }
    data.show()


  }

}
