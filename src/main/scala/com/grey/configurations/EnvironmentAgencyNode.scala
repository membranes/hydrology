package com.grey.configurations

import EnvironmentAgency.Node

/**
 *
 * @param nodes: An object consisting environment agency nodes, wherein each node is a set of parameters
 *               for retrieving the internet/space based data, for storing the data locally, etc.
 */
class EnvironmentAgencyNode(nodes: EnvironmentAgency.EnvironmentAgency) {

  def environmentAgencyNode(name: String): Node = {

    val excerpt: List[Node] = nodes.nodes.filter(_.name == name)

    excerpt.head

  }

}
