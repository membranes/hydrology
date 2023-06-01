package com.grey

import com.grey.configurations.EnvironmentAgencyAPI
import com.grey.environment.LocalSettings
import com.grey.interfaces.EnvironmentAgencyInterface
import com.grey.interfaces.EnvironmentAgencyInterface.EnvironmentAgencyCase
import com.grey.source.GetReferenceData
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths

class DataSteps(spark: SparkSession) {

  private val environmentAgencyAPI = new EnvironmentAgencyAPI(name = "EnvironmentAgencyAPI.conf")
  private val getReference = new GetReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  def dataSteps(): Unit = {

    val baseString = environmentAgencyAPI.environmentAgencyAPI(
      interface = EnvironmentAgencyCase(node = "reference", group = "base", key = "determinands"))

    val schemaString = environmentAgencyAPI.environmentAgencyAPI(
      interface = EnvironmentAgencyCase(node = "reference", group = "schema", key = "determinands"))

    val uri = Paths.get(localSettings.dataDirectory, "references", baseString).toString

    val determinands: Dataset[Row] = getReference.getReferenceData(uri = uri, schemaString = schemaString)
    determinands.show()

  }

}
