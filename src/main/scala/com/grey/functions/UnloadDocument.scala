package com.grey.source

import org.apache.commons.io.FileUtils

import java.io.File
import java.net.URL

import scala.util.control.Exception

class UnloadDocument {

  def unloadDocument(urlString: String, pathString: String): Unit = {

    Exception.allCatch.withTry(
      FileUtils.copyURLToFile(new URL(urlString),
        new File(pathString))
    )

  }

}
