package com.grey.algorithms.reference

import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

class SamplingPointTypes(spark: SparkSession) {

  def samplingPointTypes(reference: Dataset[Row]): Unit = {

    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "group", "group.label")
    val synonyms = Seq("sampling_point_type_id", "sampling_point_type_description", "group", "group_desc")

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
