package com.grey.configurations

import EnvironmentAgency.Node

class EnvironmentAgencyNode(nodes: EnvironmentAgency.EnvironmentAgency) {

  def environmentAgencyNode(name: String): Node = {

    val excerpt: List[Node] = nodes.nodes.filter(_.name == name)

    excerpt.head

  }

}
