package com.grey

import com.grey.configurations.EnvironmentAgencyAPI
import org.apache.spark.sql.SparkSession

class DataSteps(spark: SparkSession) {

  private val environmentAgencyAPI = new EnvironmentAgencyAPI(name = "EnvironmentAgencyAPI")

  def dataSteps(): Unit = {

    val url = environmentAgencyAPI.environmentAgencyAPI(node = "reference", group = "url", key = "determinands")
    println(url)

  }

}
