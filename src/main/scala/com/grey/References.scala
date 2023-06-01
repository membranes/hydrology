package com.grey

import com.grey.configurations.EnvironmentAgencyAPI
import com.grey.environment.LocalSettings
import com.grey.interfaces.EnvironmentAgencyInterface.EnvironmentAgency
import com.grey.source.UnloadDocument

import java.nio.file.Paths

class References {

  private val environmentAgencyAPI = new EnvironmentAgencyAPI(name = "EnvironmentAgencyAPI.conf")
  private val localSettings = new LocalSettings()

  def references(): Unit = {

    // Test
    val unloadDocument = new UnloadDocument()
    val urlString = environmentAgencyAPI.environmentAgencyAPI(
      interface = EnvironmentAgency(node = "reference", group = "url", key = "determinands"))

    unloadDocument.unloadDocument(urlString = urlString + "?_limit=1000000",
      pathString = Paths.get(localSettings.dataDirectory, urlString.split("/").reverse.head).toString)

  }

}
