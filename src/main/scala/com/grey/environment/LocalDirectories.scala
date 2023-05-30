package com.grey.environment

import org.apache.commons.io.FileUtils

import java.io.File
import scala.util.Try
import scala.util.control.Exception


/**
 *
 */
class LocalDirectories {

  /**
   * @define localDirectoryReset: Deletes and recreates
   *
   * @param directoryName: The directory ...
   * @return
   */
  def localDirectoryReset(directoryName: String): Try[Boolean] = {

    // Delete
    val delete = localDirectoryDelete(directoryName = directoryName)

    // Create
    if (delete.isSuccess) {
      localDirectoryCreate(directoryName = directoryName)
    } else {
      sys.error(delete.failed.get.getMessage)
    }

  }


  /**
   * @define localDirectoryCreate: Creates ...
   *
   * @param directoryName: The directory ...
   * @return
   */
  private def localDirectoryCreate(directoryName: String): Try[Boolean] = {

    // Object
    val directoryObject = new File(directoryName)

    // Create
    val create: Try[Boolean] = Exception.allCatch.withTry(
      if (!directoryObject.exists()) {
        directoryObject.mkdirs()
      } else {
        true
      }
    )

    // State
    if (create.isFailure) {
      sys.error(create.failed.get.getMessage)
    } else {
      create
    }

  }


  /**
   * @define localDirectoryDelete: Deletes a directory
   *
   * @param directoryName: The directory ...
   * @return
   */
  private def localDirectoryDelete(directoryName: String): Try[Unit] = {

    // Object
    val directoryObject = new File(directoryName)

    // Delete
    val delete: Try[Unit] = Exception.allCatch.withTry(
      if (directoryObject.exists()) {
        FileUtils.deleteDirectory(directoryObject)
      }
    )

    // State
    if (delete.isFailure) {
      sys.error(delete.failed.get.getMessage)
    } else {
      delete
    }

  }


}
