package Day2

import Common.Input
import kotlin.system.measureTimeMillis

var efficent = false;
fun main() {
    val input = Input("Day2");

    val notefficent = measureTimeMillis {
        println("Part1: " + part1(input))
        println("Part2: " + part2(input))
    }
    efficent = true;
    val efficenttime = measureTimeMillis {
        println("Part1: " + part1(input))
        println("Part2: " + part2(input))
    }
    println("not efficient: ${notefficent} ms")
    println("efficient: ${efficenttime} ms")
}


fun part1(input: Input): Int {
    var maxRed = 12
    var maxGreen = 13
    var maxBlue = 14


    var gameIdSum = 0;
    for (line in input.Get()) {
        var game = Game(line, efficent)

        if (game.CheckColor(maxRed, Game.CubeColor.Red)
            && game.CheckColor(maxGreen, Game.CubeColor.Green)
            && game.CheckColor(maxBlue, Game.CubeColor.Blue)
        ) {
            gameIdSum += game.Id
        }
    }
    return gameIdSum


}

fun part2(input: Input): Int {
    var powersSum = 0;
    for (line in input.Get()) {
        var game = Game(line, efficent)

        powersSum += game.Red * game.Blue * game.Green;
    }
    return powersSum;
}
