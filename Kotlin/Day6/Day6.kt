package Day6

import Common.Input
import kotlin.math.sign

fun main() {
    var input = Input("Day6")

    var times = input.Get()[0].split(":")[1].trim().split("\\s+".toRegex()).map {it.toLong()}
    var distances = input.Get()[1].split(":")[1].trim().split("\\s+".toRegex()).map { it.toLong() }

    var posibilityMultiply = 0
    for ((index,time) in times.withIndex()){
        if(index == 0)
            posibilityMultiply = getPosiblities(time, distances[index]).toInt()
        else
            posibilityMultiply *= getPosiblities(time, distances[index]).toInt()
    }
    println("Error margin multiply (part1): $posibilityMultiply")

    var part2 = getPosiblities(times.joinToString("").toLong(),distances.joinToString("").toLong())
    println("part2: $part2")
}


fun getPosiblities(time: Long, distance: Long) : Long {
    var possiblewins = 0L
    for (i in 1..time){
        if((time - i)*i > distance){
            possiblewins++;
        }
    }
    return possiblewins
}
