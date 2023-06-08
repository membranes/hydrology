package com.grey.functions

import com.grey.environment.LocalSettings
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataType, StructType}

import java.nio.file.Paths
import scala.util.Try
import scala.util.control.Exception


/**
 *
 * @param spark: An instance of SparkSession
 */
class SchemaOf(spark: SparkSession) {

  private val localSettings = new LocalSettings()

  /**
   *
   * @param schemaString: The name of a JSON schema file, including its extension
   */
  def schemaOf(schemaString: String): Try[StructType] = {

    // The <path + file name + extension> of schema JSON file
    val pathString: String = Paths.get(localSettings.schemataDirectory, schemaString).toString

    // Read-in the schema
    val fieldProperties: Try[RDD[String]] = Exception.allCatch.withTry(
      spark.sparkContext.textFile(pathString)
    )

    // Convert schema to StructType
    val schema: Try[StructType] = if (fieldProperties.isSuccess) {
      Exception.allCatch.withTry(
        DataType.fromJson(fieldProperties.get.collect.mkString("")).asInstanceOf[StructType]
      )
    } else {
      sys.error(fieldProperties.failed.get.getMessage)
    }

    if (schema.isSuccess) {
      schema
    } else {
      sys.error(schema.failed.get.getMessage)
    }

  }

}
