package Day8

import Common.Input
import java.util.Currency

fun main() {
    var input = Input("Day8")

    var instructions = ""

    var nodes = arrayListOf<Node>()

    for ((index, node) in input.Get().withIndex()) {
        if (index == 0)
            instructions = node
        else if (!node.isNullOrBlank()) {
            var nodeValues = node.split('=')
            var nodeObj = Node()
            nodeObj.node = nodeValues[0].trim()
            var subValues = nodeValues[1].trim().trim('(').trim(')').split(',')
            nodeObj.left = subValues[0].trim()
            nodeObj.right = subValues[1].trim()
            nodes.add(nodeObj)
        }
    }
    //part 1
    var currentNode: Node = nodes.find { it.node == "AAA" }!!
    var steps = getStepsToFinish(currentNode, nodes, instructions)
    println("part 1: " + steps)

    //part 2
    var currentNodes: MutableList<Node> = nodes.filter { it.node.endsWith('A') }.toMutableList()
    var steps2 = getStepsToFinishMultiple(currentNodes, nodes, instructions)
    println("part 2: " + steps2)
}

fun getStepsToFinishMultiple(currentNodes: MutableList<Node>, nodes: ArrayList<Node>, instructions: String): Long {

    var nodeCycleLengths = arrayListOf<Long>()


    for (i in 0..currentNodes.size - 1) {
        var index = 0
        var nodeSteps = 0
        while (!currentNodes[i].node.endsWith('Z')) {
            if (instructions.length - 1 < index)
                index = 0
            if (instructions[index] == 'R') {
                currentNodes[i] = nodes.find { it.node == currentNodes[i].right }!!
            } else if (instructions[index] == 'L') {
                currentNodes[i] = nodes.find { it.node == currentNodes[i].left }!!
            }
            nodeSteps++;
            index++;
        }
        nodeCycleLengths.add(nodeSteps.toLong())

    }
    var steps = lowestCommonDenominator(nodeCycleLengths);
    return steps;
}

fun lowestCommonDenominator(nodeCycleLengths: ArrayList<Long>): Long {
    var nom = nodeCycleLengths.max()
    var maxnom = nodeCycleLengths.reduce{acc, i -> acc * i};
    while (!nodeCycleLengths.all { nom % it == 0L } && nom <= maxnom ) {
        nom += nodeCycleLengths.max();
    }
    return nom;
}

fun getStepsToFinish(firstNode: Node, nodes: ArrayList<Node>, instructions: String): Int {
    var currentNode = firstNode
    var steps = 0
    for (instruction in instructions) {
        if (instruction == 'R') {
            currentNode = nodes.find { it.node == currentNode.right }!!
        } else if (instruction == 'L') {
            currentNode = nodes.find { it.node == currentNode.left }!!
        }
        steps++
    }
    if (currentNode != nodes.find { it.node == "ZZZ" }) {
        steps += getStepsToFinish(currentNode, nodes, instructions);
    }
    return steps;
}

class Node {
    var node = ""
    var left = ""
    var right = ""
}