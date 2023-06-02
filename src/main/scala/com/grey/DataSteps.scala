package com.grey

import com.grey.configurations.{EnvironmentAgency, EnvironmentAgencyNode}
import com.grey.environment.LocalSettings
import com.grey.source.{ReferenceAsset, ReferenceData}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths
import scala.collection.parallel.ParSeq


/**
 *
 * @param spark: A instance of SparkSession
 */
class DataSteps(spark: SparkSession) {

  private val nodes: EnvironmentAgency.EnvironmentAgency = EnvironmentAgency.environmentAgency()
  private val getNode: EnvironmentAgencyNode = new EnvironmentAgencyNode(nodes = nodes)

  private val referenceData = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()


  /**
   *
   */
  def dataSteps(): Unit = {

    // Nodes
    val names: Seq[String] = List("determinands", "environment-agency-area", "environment-agency-subarea",
      "sampling-point", "sampling-point-types")
    val nodes: ParSeq[EnvironmentAgency.Node] = names.par.map{ name =>
      getNode.environmentAgencyNode(name = name)
    }

    // Downloading the reference assets of interest
    nodes.par.foreach{node =>
      // Download the reference data asset
      new ReferenceAsset().referenceAsset(node = node)
    }

    // Previewing
    nodes.foreach{node =>

      // Read the reference data asset
      val uri = Paths.get(localSettings.referencesDirectory, node.base).toString
      val reference: Dataset[Row] = referenceData.referenceData(uri = uri, schemaString = node.schema)

      // Preview
      reference.show()

    }

  }

}
