package com.grey

import com.grey.algorithms.reference.ReferenceInterface
import com.grey.configurations.{EnvironmentAgency, EnvironmentAgencyNode}
import com.grey.environment.LocalSettings
import com.grey.source.{ReferenceAsset, ReferenceData}
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.immutable.ParSeq


/**
 *
 * @param spark : A instance of SparkSession
 */
class DataSteps(spark: SparkSession) {

  private val nodes: EnvironmentAgency.EnvironmentAgency = EnvironmentAgency.environmentAgency()
  private val getNode: EnvironmentAgencyNode = new EnvironmentAgencyNode(nodes = nodes)
  private val referenceInterface = new ReferenceInterface(spark = spark)

  private val referenceData = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  /**
   *
   */
  def dataSteps(): Unit = {


    // Nodes
    val names: List[String] = List("determinands", "environment-agency-area", "environment-agency-subarea",
      "sampling-point", "sampling-point-types")
    val nodes: ParSeq[EnvironmentAgency.Node] = names.par.map { name =>
      getNode.environmentAgencyNode(name = name)
    }


    // Downloading the reference assets of interest
    nodes.par.foreach { node =>
      // Download the reference data asset
      new ReferenceAsset().referenceAsset(node = node)
    }


    // Structuring
    nodes.foreach { node =>
      // Read the reference data asset
      val uri = Paths.get(localSettings.referencesDirectory, node.base).toString
      val reference: Dataset[Row] = referenceData.referenceData(uri = uri, schemaString = node.schema)
      // Structure
      referenceInterface.referenceInterface(node = node, reference = reference)
    }

  }

}




