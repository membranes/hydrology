package com.grey.source

import com.grey.environment.LocalSettings
import com.grey.functions.{CaseClassOf, SchemaOf}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.types.StructType
import org.apache.commons.io
import org.apache.commons.io.IOUtils

import java.net.URL
import scala.util.Try
import scala.util.control.Exception

class GetReference(spark: SparkSession) {

  private val schemaOf = new SchemaOf(spark = spark)
  val localSettings = new LocalSettings()

  def getReference(uri: String, schemaString: String): Dataset[Row] = {

    // Get the schema details of the reference file
    val schema: Try[StructType] = schemaOf.schemaOf(schemaString = schemaString)


    // Read in the reference file via its schema
    val data: Try[DataFrame] = Exception.allCatch.withTry(
      spark.read.schema(schema = schema.get)
        .format("csv")
        .option("header", value = true)
        .option("encoding", "UTF-8")
        .load(uri)
    )


    // If the data reading step fails
    if (data.isFailure) {
      sys.error(data.failed.get.getMessage)
    }


    // Otherwise
    val caseClassOf = CaseClassOf.caseClassOf(schema = data.get.schema)
    data.get.as(caseClassOf)


  }

}
