package com.grey.source

import com.grey.environment.LocalSettings
import com.grey.functions.UnloadDocument
import com.grey.interfaces.GetNode

import java.nio.file.Paths


/**
 * This class will download a specified reference asset from the United Kingdom's Environment Agency
 * data platform, data application programming interface.
 */
class ReferenceAsset {

  private val localSettings = new LocalSettings()
  private val unloadDocument = new UnloadDocument()

  /**
   *
   * @param getNode: An assessor method that would retrieve/provide the parameter values of
   *                 reference asset.  For example, if the value of <name> is `determinands`
   *                 it will provide `url`, etc., details of the chemical determinands reference asset.
   * @param name: The name of the reference asset of interest; which will be downloaded and saved locally.
   */
  def referenceAsset(getNode: GetNode, name: String): Unit = {

    // The get the parameters of the asset in focus
    val node = getNode.getNode(name = name)
    val urlString = node.url

    // Unload
    unloadDocument.unloadDocument(urlString = urlString + "?_limit=1000000",
      pathString = Paths.get(localSettings.dataDirectory, urlString.split("/").reverse.head).toString)

  }

}
