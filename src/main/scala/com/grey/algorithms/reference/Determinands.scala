package com.grey.algorithms.reference

import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


/**
 *
 * @param spark : An instance of Spark Session
 */
class Determinands(spark: SparkSession) {


  /**
   *
   * @param reference : The <determinands> reference data
   */
  def determinands(reference: Dataset[Row]): Unit = {


    // Addressing the <dot> issue
    val blob = reference.toDF(reference.columns.map(_.replace(".", "_")): _*)


    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "definition", "unit_label", "unit_comment")
    val synonyms = Seq("determinand_id", "determinand_desc", "definition", "unit_of_measure", "unit_of_measure_desc")


    // The relevant area columns
    var data: DataFrame = blob.selectExpr(names: _*)


    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }
    data.show()


  }

}
