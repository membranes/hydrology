package com.grey.algorithms.reference

import com.grey.environment.LocalSettings
import com.grey.source.ReferenceData
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


/**
 *
 * @param spark : An instance of Spark Session
 */
class Area(spark: SparkSession) {

  private val referenceData = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  /**
   *
   * @param reference : The <area> reference data
   */
  def area(reference: Dataset[Row]): Unit = {

    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label")
    val synonyms = Seq("area_id", "description")

    // The relevant area columns
    var data: DataFrame = reference.selectExpr(names: _*)

    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
    }

    // Save


  }

}
