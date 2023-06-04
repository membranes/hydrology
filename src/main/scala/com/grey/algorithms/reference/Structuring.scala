package com.grey.algorithms.reference

import com.grey.configurations.EnvironmentAgency
import org.apache.spark.sql.{Dataset, Row, SparkSession}

class Structuring(spark: SparkSession) {

  private val area = new Area(spark = spark)
  private val determinands = new Determinands(spark = spark)
  private val samplingPoint = new SamplingPoint(spark = spark)
  private val samplingPointTypes = new SamplingPointTypes(spark = spark)
  private val subarea = new Subarea(spark = spark)

  def referenceInterface(node: EnvironmentAgency.Node, reference: Dataset[Row],
                         subareaFrame: Dataset[Row] = null, samplingPointTypesFrame: Dataset[Row] = null): Unit = {

    node.name match {
      case "environment-agency-area" => area.area(reference = reference)
      case "determinands" => determinands.determinands(reference = reference)
      case "sampling-point" => samplingPoint.samplingPoint(reference = reference)
      case "sampling-point-types" => samplingPointTypes.samplingPointTypes(reference = reference)
      case "environment-agency-subarea" => subarea.subarea(reference = reference)
      case _ => sys.error("Missing")
    }

  }

}
