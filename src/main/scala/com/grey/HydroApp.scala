package com.grey

import com.grey.configurations.{EnvironmentAgency, EnvironmentAgencyNode}
import com.grey.environment.{LocalDirectories, LocalSettings}
import com.grey.functions.UnloadDocument
import com.typesafe.scalalogging
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

import java.nio.file.Paths


/**
 *
 */
object HydroApp {

  private val localSettings = new LocalSettings()
  private val localDirectories = new LocalDirectories()

  /**
   *
   * @param args: Input arguments, if any
   */
  def main(args: Array[String]): Unit = {

    // Minimising log print-outs
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("aka").setLevel(Level.OFF)


    // Spark Session
    val spark: SparkSession = SparkSession.builder().appName("architecture")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.sql.warehouse.dir", localSettings.warehouseDirectory)
      .getOrCreate()


    // Spark logs
    spark.sparkContext.setLogLevel("ERROR")


    /* Configuring for computation
     *
     * java.lang.Runtime.getRuntime.availableProcessors() deduces the number of machine threads
     * spark.sql.shuffle.partitions: The number of shuffle partitions for joins & aggregation
     * spark.default.parallelism: The default number of partitions delivered after a transformation
     * spark.speculation: If true "performs speculative execution of tasks. This means if one or more tasks are
     *                             running slowly in a stage, they will be re-launched."
     */
    val threads: Int = scala.math.ceil(java.lang.Runtime.getRuntime.availableProcessors()).toInt
    spark.conf.set("spark.sql.shuffle.partitions", threads.toString)
    spark.conf.set("spark.default.parallelism", threads.toString)


    // Directories; empty the outputs directory
    val logger = scalalogging.Logger(LoggerFactory.getLogger(getClass))
    logger.info(localSettings.warehouseDirectory)
    localDirectories.localDirectoryReset(directoryName = localSettings.warehouseDirectory)


    // Steps
    val dataSteps = new DataSteps(spark = spark)
    dataSteps.dataSteps()


  }

}
