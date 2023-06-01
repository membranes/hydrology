package com.grey

import com.grey.configurations.{EnvironmentAgency, EnvironmentAgencyNode}
import com.grey.environment.LocalSettings
import com.grey.source.{ReferenceAsset, ReferenceData}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths

class DataSteps(spark: SparkSession) {

  private val nodes: EnvironmentAgency.EnvironmentAgency = EnvironmentAgency.environmentAgency()
  private val getNode: EnvironmentAgencyNode = new EnvironmentAgencyNode(nodes = nodes)

  private val getReference = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  def dataSteps(): Unit = {

    // A reference asset of interest
    val name = "samplingPointTypes"
    val node: EnvironmentAgency.Node = getNode.environmentAgencyNode(name = name)

    // Download a reference data asset
    new ReferenceAsset().referenceAsset(node = node)

    // Read the reference data asset
    val uri = Paths.get(localSettings.referencesDirectory, node.base).toString
    val determinands: Dataset[Row] = getReference.referenceData(uri = uri, schemaString = node.schema)
    determinands.show()

  }

}
