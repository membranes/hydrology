package com.grey.algorithms.reference

import com.grey.functions.CaseClassOf
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{col, trim, udf}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}


/**
 *
 * @param spark : An instance of SparkSession
 */
class Subarea(spark: SparkSession) {


  /**
   * #
   *
   * @param reference : The subarea reference data
   */
  def subarea(reference: Dataset[Row]): Dataset[Row] = {

    /**
     * Import implicits for
     * encoding (https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-sql-Encoder.html)
     * implicit conversions, e.g., converting a RDD to a DataFrames.
     * access to the "$" notation.
     */
    import spark.implicits._


    // The names of the relevant columns in the raw data file, and the preferred
    // names, i.e., <synonyms>
    val names = Seq("notation", "label", "subOrganizationOf")
    val synonyms = Seq("subarea_id", "subarea_desc", "area_id")


    // The relevant area columns
    var data: DataFrame = reference.selectExpr(names: _*)


    // Renaming
    val fields: Seq[(String, String)] = names.zip(synonyms)
    fields.foreach { case (name: String, synonym: String) =>
      data = data.withColumnRenamed(existingName = name, newName = synonym)
      data = data.withColumn(synonym, trim(col(synonym)))
    }


    // The <area_id> has been recorded in URL (Uniform Resource Locator) form.  The actual identification code
    // is the base string of the URL, hence ...
    val equation: UserDefinedFunction = udf((x: String) => {
      x.split("/").reverse.head
    })
    val inspected = data.withColumn(colName = "area_id", col = equation($"area_id"))


    // Hence
    val caseClassOf = CaseClassOf.caseClassOf(schema = inspected.schema)
    inspected.as(caseClassOf)

  }

}
