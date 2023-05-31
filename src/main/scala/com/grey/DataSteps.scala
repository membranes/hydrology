package com.grey

import com.grey.configurations.EnvironmentAgencyAPI
import com.grey.environment.LocalSettings
import com.grey.source.GetReference
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths

class DataSteps(spark: SparkSession) {

  private val environmentAgencyAPI = new EnvironmentAgencyAPI(name = "EnvironmentAgencyAPI.conf")
  private val getReference = new GetReference(spark = spark)
  private val localSettings = new LocalSettings()

  def dataSteps(): Unit = {

    val name = environmentAgencyAPI.environmentAgencyAPI(node = "reference", group = "name", key = "determinands")
    val uri = Paths.get(localSettings.dataDirectory, "references", name).toString

    val determinands: Dataset[Row] = getReference.getReference(uri = uri, schemaString = "schemaDeterminands.json")
    determinands.show()

  }

}
