package com.grey.environment

import java.nio.file.{Path, Paths}


/**
 *
 */
class LocalSettings {

  // The environment
  private val projectDirectory: String = System.getProperty("user.dir")
  private val separator: String = System.getProperty("file.separator")

  // Data directory
  private val dataDirectory: String = s"$projectDirectory${separator}data"
  val referencesDirectory: String = Paths.get(dataDirectory, "references").toString

  // Warehouse directory
  val warehouseDirectory: String = Paths.get(projectDirectory, "warehouse").toString
  val referencesWarehouse: String = Paths.get(warehouseDirectory, "references").toString

  // Resources directory
  private val resourcesDirectory: String = Paths.get(projectDirectory, "src", "main", "resources").toString
  val schemataDirectory: String = Paths.get(resourcesDirectory, "schemata").toString
  val configurationsDirectory: String = Paths.get(resourcesDirectory, "configurations").toString

}
