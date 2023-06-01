package com.grey.configurations

import EnvironmentAgency.Node

class GetEnvironmentAgencyNode(nodes: EnvironmentAgency.EnvironmentAgency) {

  def getEnvironmentAgencyNode(name: String): Node = {

    val excerpt: List[Node] = nodes.nodes.filter(_.name == name)

    excerpt.head

  }

}
