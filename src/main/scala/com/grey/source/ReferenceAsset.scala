package com.grey.source

import com.grey.configurations.EnvironmentAgency
import com.grey.environment.LocalSettings
import com.grey.functions.UnloadDocument

import java.nio.file.Paths


/**
 * This class will download a specified reference asset from the United Kingdom's Environment Agency
 * data platform, data application programming interface.
 */
class ReferenceAsset {

  private val localSettings = new LocalSettings()
  private val unloadDocument = new UnloadDocument()

  /**
   * @param node: A node of parameters with respect to a reference asset in focus
   *
   */
  def referenceAsset(node: EnvironmentAgency.Node): Unit = {

    // Unload
    val urlString = node.url
    unloadDocument.unloadDocument(urlString = urlString + "?_limit=1000000",
      pathString = Paths.get(localSettings.dataDirectory, urlString.split("/").reverse.head).toString)

  }

}
