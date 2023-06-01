package com.grey.configurations

import com.grey.environment.LocalSettings
import com.grey.interfaces.EnvironmentAgencyInterface.EnvironmentAgencyCase
import com.typesafe.config.{Config, ConfigFactory}

import java.io.File
import java.nio.file.Paths
import scala.util.Try
import scala.util.control.Exception


/**
 *
 * @param name: The name of the environment agency's API (Application Programming Interface) conf file
 */
class EnvironmentAgencyAPI(name: String) {

  private val localSettings = new LocalSettings()
  private val path: String = Paths.get(localSettings.configurationsDirectory, name).toString

  /**
   * @param interface : EnvironmentAgency(node, group, key) ->
   *      node: A node/object of the configuration file
   *      group: A group within the aforementioned node/object
   *      key: The key of a key/value pair within the aforementioned group
   * @return
   */
  def environmentAgencyAPI(interface: EnvironmentAgencyCase): String = {

    /**
     * Read a node of the configuration file
     */
    val config: Try[Config] = Exception.allCatch.withTry(
      ConfigFactory.parseFile(new File(path)).getConfig(interface.node)
    )
    if (config.isFailure) {
      sys.error(config.failed.get.getMessage)
    }

    /**
     * Query a node/object, i.e., get the value of <key> in a <group>
     */
    val text = Exception.allCatch.withTry(
      config.get.getConfig(interface.group).getString(interface.key)
    )
    if (text.isFailure) {
      sys.error(text.failed.get.getMessage)
    } else {
      text.get
    }

  }

}
