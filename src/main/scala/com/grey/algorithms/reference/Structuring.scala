package com.grey.algorithms.reference

import com.grey.configurations.EnvironmentAgency
import com.grey.environment.LocalSettings
import com.grey.source.ReferenceData
import org.apache.spark.sql.{Dataset, Row, SparkSession}

import java.nio.file.Paths

class Structuring(spark: SparkSession) {

  private val area = new Area(spark = spark)
  private val determinands = new Determinands(spark = spark)
  private val samplingPoint = new SamplingPoint(spark = spark)
  private val samplingPointTypes = new SamplingPointTypes(spark = spark)
  private val subarea = new Subarea(spark = spark)

  private val referenceData = new ReferenceData(spark = spark)
  private val localSettings = new LocalSettings()

  private def structuringReference(node: EnvironmentAgency.Node): Dataset[Row] = {

    // Read the reference data asset
    val uri = Paths.get(localSettings.referencesDirectory, node.base).toString
    val reference: Dataset[Row] = referenceData.referenceData(uri = uri, schemaString = node.schema)
    reference

  }

  def structuringInitial(node: EnvironmentAgency.Node): Dataset[Row] = {

    val reference = structuringReference(node = node)

    node.name match {
      case "sampling-point-types" => samplingPointTypes.samplingPointTypes(reference = reference)
      case "environment-agency-subarea" => subarea.subarea(reference = reference)
      case _ => sys.error("Missing")
    }

  }

  def structuringMiscellaneous(node: EnvironmentAgency.Node, subareaFrame: Dataset[Row] = null,
                               samplingPointTypesFrame: Dataset[Row] = null): Unit = {

    val reference = structuringReference(node = node)

    node.name match {
      case "environment-agency-area" => area.area(reference = reference)
      case "determinands" => determinands.determinands(reference = reference)
      case "sampling-point" => samplingPoint.samplingPoint(reference = reference)
      case _ => sys.error("Missing")
    }

  }

}
