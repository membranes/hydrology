package com.grey.algorithms.reference

import com.grey.environment.LocalSettings
import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

import java.nio.file.Paths


/**
 *
 * @param spark : An instance of Spark Session
 */
class Area(spark: SparkSession) {

  private val localSettings = new LocalSettings()

  /**
   *
   * @param reference : The <area> reference data
   */
  def area(reference: Dataset[Row]): Unit = {

    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label")
    val synonyms = Seq("area_id", "area_desc")

    // The relevant area columns
    var data: DataFrame = reference.selectExpr(names: _*)

    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }

    // Save
    data.coalesce(numPartitions = 1).write
      .format("csv")
      .option(key = "encoding", value = "UTF-8")
      .option(key = "header", "true")
      .save(path = Paths.get(localSettings.referencesWarehouse, "environment_agency_area.csv").toString)


  }

}
