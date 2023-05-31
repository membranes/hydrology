package com.grey.environment

import java.nio.file.Paths


/**
 *
 */
class LocalSettings {

  // The environment
  private val projectDirectory: String = System.getProperty("user.dir")
  private val separator: String = System.getProperty("file.separator")

  // Data directory
  val dataDirectory: String = s"$projectDirectory${separator}data"

  // Warehouse directory
  val warehouseDirectory: String = Paths.get(projectDirectory, "warehouse").toString

  // Resources directory
  private val resourcesDirectory: String = Paths.get(projectDirectory, "src", "main", "resources").toString
  val schemataDirectory: String = Paths.get(resourcesDirectory, "schemata").toString

}
