package com.grey.algorithms.reference

import com.grey.environment.LocalSettings
import org.apache.spark.sql.functions.{col, split, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


class Subarea(spark: SparkSession) {


  private val localSettings = new LocalSettings()

  def subarea(reference: Dataset[Row]): Unit = {

    import spark.implicits._

    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "subOrganizationOf")
    val synonyms = Seq("subarea_id", "description", "url_area_id")

    // The relevant area columns
    var data: DataFrame = reference.selectExpr(names: _*)

    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }

    val test = data.withColumn(colName = "area_id", col = split($"url_area_id", "/"))
    test.show()

  }

}
