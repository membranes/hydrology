package com.grey.algorithms.reference

import com.grey.functions.CaseClassOf
import org.apache.spark.sql.functions.{col, trim}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


/**
 *
 * @param spark: An instance of SparkSession
 */
class SamplingPointTypes(spark: SparkSession) {

  /**
   *
   * @param reference : The raw data
   */
  def samplingPointTypes(reference: Dataset[Row]): Dataset[Row] = {

    /**
     * Import implicits for
     * encoding (https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-sql-Encoder.html)
     * implicit conversions, e.g., converting a RDD to a DataFrames.
     * access to the "$" notation.
     */
    import spark.implicits._


    // Addressing the <dot> issue
    val blob = reference.toDF(reference.columns.map(_.replace(".", "_")): _*)


    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "group", "group_label")
    val synonyms = Seq("sampling_point_type_id", "sampling_point_type_description", "group", "group_desc")


    // The relevant area columns
    var data: DataFrame = blob.selectExpr(names: _*)


    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }


    // Save


    // Hence
    val caseClassOf = CaseClassOf.caseClassOf(schema = data.schema)
    data.as(caseClassOf)
    

  }

}
