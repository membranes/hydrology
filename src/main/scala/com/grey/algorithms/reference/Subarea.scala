package com.grey.algorithms.reference

import com.grey.environment.LocalSettings
import com.grey.source.ReferenceData
import org.apache.spark.sql.SparkSession


class Subarea(spark: SparkSession) {

  private val referenceData = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  def subarea(): Unit = {

  }

}
