package com.grey.source

import com.grey.configurations.EnvironmentAgencyAPI
import com.grey.environment.LocalSettings
import com.grey.functions.UnloadDocument
import com.grey.interfaces.EnvironmentAgencyInterface.EnvironmentAgencyCase

import java.nio.file.Paths

class ReferenceDocument {

  private val environmentAgencyAPI = new EnvironmentAgencyAPI(name = "EnvironmentAgency.conf")
  private val localSettings = new LocalSettings()

  def referenceDocument(): Unit = {

    // Test
    val unloadDocument = new UnloadDocument()
    val urlString = environmentAgencyAPI.environmentAgencyAPI(
      interface = EnvironmentAgencyCase(node = "reference", group = "url", key = "determinands"))

    unloadDocument.unloadDocument(urlString = urlString + "?_limit=1000000",
      pathString = Paths.get(localSettings.dataDirectory, urlString.split("/").reverse.head).toString)

  }

}
