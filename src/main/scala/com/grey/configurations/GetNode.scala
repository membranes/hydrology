package com.grey.configurations

import com.grey.interfaces.EnvironmentAgency
import com.grey.interfaces.EnvironmentAgency.Node

class GetNode(nodes: EnvironmentAgency.EnvironmentAgency) {

  def getNode(name: String): Node = {

    val excerpt: List[Node] = nodes.nodes.filter(_.name == name)

    excerpt.head

  }

}
