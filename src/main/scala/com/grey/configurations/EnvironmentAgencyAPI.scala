package com.grey.configurations

import com.grey.environment.LocalSettings
import com.typesafe.config.{Config, ConfigFactory}

import java.io.File
import java.nio.file.Paths
import scala.util.Try
import scala.util.control.Exception

class EnvironmentAgencyAPI(name: String) {

  private val localSettings = new LocalSettings()
  private val path: String = Paths.get(localSettings.configurationsDirectory, name).toString

  def environmentAgencyAPI(node: String, group: String, variable: String): String = {

    /**
     * Read a node of the configuration file
     */
    val config: Try[Config] = Exception.allCatch.withTry(
      ConfigFactory.parseFile(new File(path)).getConfig(node)
    )
    if (config.isFailure) {
      sys.error(config.failed.get.getMessage)
    }


    /**
     * Query formula
     */

      val text = Exception.allCatch.withTry(
        config.get.getConfig(group).getString(variable)
      )
      if (text.isFailure) {
        sys.error(text.failed.get.getMessage)
      } else {
        text.get
      }






  }

}
