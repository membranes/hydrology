package com.grey.configurations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.grey.environment.LocalSettings

import java.io.File
import java.util.{List => JList}
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
import scala.util.control.Exception


object EnvironmentAgency {

  val localSettings = new LocalSettings()

  def environmentAgency(): EnvironmentAgency = {

    // val uri: Path = Paths.get(localSettings.resourcesDirectory, "keys.yaml")
    val uri: File = new File(localSettings.configurationsDirectory, "EnvironmentAgency.yaml")


    // If the string is a valid URL parse & verify its parameters
    val mapper: ObjectMapper = new ObjectMapper(new YAMLFactory())
    val getEnvironmentAgency = if (uri.exists()) {
      Exception.allCatch.withTry(
        mapper.readValue(uri, classOf[EnvironmentAgency])
      )
    } else {
      sys.error(s"$uri does not exist")
    }

    if (getEnvironmentAgency.isSuccess) {
      getEnvironmentAgency.get
    } else {
      sys.error(getEnvironmentAgency.failed.get.getMessage)
    }


  }


  /**
   *
   * @param _nodes: nodes
   */
  class EnvironmentAgency(@JsonProperty("nodes") _nodes: JList[Node]) {
    require(_nodes.nonEmpty, "The object is not nullable")
    val nodes: List[Node] = _nodes.toList
  }


  /**
   *
   * @param _name: name
   * @param _url: url
   * @param _base: base
   * @param _schema: schema
   */
  class Node(@JsonProperty("name") _name: String,
              @JsonProperty("url") _url: String,
              @JsonProperty("base") _base: String,
              @JsonProperty("schema") _schema: String) {

    require(_name != null, "The <name> of the data asset of interest is mandatory; it is a string.")
    val name: String = _name

    require(_url != null, "The <url> of the data asset is required.")
    val url: String = _url

    require(_base != null, "The <base>, i.e., the basename or preferred basename, of the url is required.")
    val base: String = _base

    require(_schema != null, "The name of the schema file, i.e., <schema>, of the data asset is required; it must include the file extension.")
    val schema: String = _schema

  }


}
