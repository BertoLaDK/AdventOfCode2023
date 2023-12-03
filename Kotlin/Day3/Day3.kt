package Day3

import Common.Input
import kotlin.system.measureTimeMillis


fun main() {
    var input = Input("Day3")

    var numbers = getNumberList(input);

    val part1time = measureTimeMillis {
        part1(input, numbers)
    }
    val part2time = measureTimeMillis {
        part2(input, numbers)
    }

    println("Part1: $part1time ms, Part2: $part2time ms")
}

fun getNumberList(input: Input): MutableList<NumberIndex> {
    var numberlist: MutableList<NumberIndex> = arrayListOf()

    var regex = Regex("[0-9]")
    for ((y, line) in input.Get().withIndex()) {
        if (regex.containsMatchIn(line)) {
            var tempnum = StringBuilder();
            for ((x, char) in line.withIndex()) {
                if (char.isDigit()) {
                    tempnum.append(char)
                    if (line.lastIndex == x) {
                        numberlist.add(
                            NumberIndex(
                                Position(x - (tempnum.length - 1), y),
                                tempnum.length,
                                tempnum.toString().toInt()
                            )
                        )
                        tempnum.clear()
                    }
                } else if (tempnum.isNotEmpty()) {
                    numberlist.add(
                        NumberIndex(
                            Position(x - tempnum.length, y),
                            tempnum.length,
                            tempnum.toString().toInt()
                        )
                    )
                    tempnum.clear()
                }

            }
        }
    }
    return numberlist;
}

fun part1(input: Input, numberlist: MutableList<NumberIndex>) {
    var grid = Grid(input)


    var sum = 0;
    for (numberIndex in numberlist) {
        var indexX = -1
        var indexXMax = 0
        if (numberIndex.StartPos.X == 0)
            indexX = 0
        if (grid.getMaxX() <= numberIndex.StartPos.X + numberIndex.Length)
            indexXMax = -1

        var foundChar = false;

        for (i in indexX..numberIndex.Length + indexXMax) {
            if (numberIndex.StartPos.Y != 0) {
                var char = grid.findByCoords(numberIndex.StartPos.X + i, numberIndex.StartPos.Y - 1)
                if (char != '.' && !char.isDigit()) {
                    foundChar = true;
                }
            }
            if (numberIndex.StartPos.Y < grid.getMaxY() - 1) {
                var char = grid.findByCoords(numberIndex.StartPos.X + i, numberIndex.StartPos.Y + 1)
                if (char != '.' && !char.isDigit()) {
                    foundChar = true
                }
            }
        }

        if (numberIndex.StartPos.X > 0) {
            var char = grid.findByCoords(numberIndex.StartPos.X - 1, numberIndex.StartPos.Y);
            if (char != '.' && !char.isDigit()) {
                foundChar = true
            }
        }
        if (numberIndex.StartPos.X + numberIndex.Length < grid.getMaxX()) {
            var char = grid.findByCoords(numberIndex.StartPos.X + numberIndex.Length, numberIndex.StartPos.Y);
            if (char != '.' && !char.isDigit()) {
                foundChar = true
            }
        }
        if (foundChar)
            sum += numberIndex.Value
    }
    println("Sum Part1: ${sum}")
}

fun part2(input: Input, numberlist: MutableList<NumberIndex>) {

    var grid = Grid(input)
    var numbersWithStar: MutableList<Triple<Int, Position, Position>> = arrayListOf()

    for (numberIndex in numberlist) {
        var indexX = -1
        var indexXMax = 0
        if (numberIndex.StartPos.X == 0)
            indexX = 0
        if (grid.getMaxX() <= numberIndex.StartPos.X + numberIndex.Length)
            indexXMax = -1


        for (i in indexX..numberIndex.Length + indexXMax) {
            if (numberIndex.StartPos.Y != 0) {
                var char = grid.findByCoords(numberIndex.StartPos.X + i, numberIndex.StartPos.Y - 1)
                if (char == '*') {
                    numbersWithStar.add(
                        Triple(
                            numberIndex.Value,
                            Position(numberIndex.StartPos.X + i, numberIndex.StartPos.Y - 1),
                            numberIndex.StartPos
                        )
                    )
                }
            }
            if (numberIndex.StartPos.Y < grid.getMaxY() - 1) {
                var char = grid.findByCoords(numberIndex.StartPos.X + i, numberIndex.StartPos.Y + 1)
                if (char == '*') {
                    numbersWithStar.add(
                        Triple(
                            numberIndex.Value,
                            Position(numberIndex.StartPos.X + i, numberIndex.StartPos.Y + 1),
                            numberIndex.StartPos
                        )
                    )
                }
            }
        }

        if (numberIndex.StartPos.X > 0) {
            var char = grid.findByCoords(numberIndex.StartPos.X - 1, numberIndex.StartPos.Y);
            if (char == '*') {
                numbersWithStar.add(
                    Triple(
                        numberIndex.Value,
                        Position(numberIndex.StartPos.X - 1, numberIndex.StartPos.Y),
                        numberIndex.StartPos
                    )
                )
            }
        }
        if (numberIndex.StartPos.X + numberIndex.Length < grid.getMaxX()) {
            var char = grid.findByCoords(numberIndex.StartPos.X + numberIndex.Length, numberIndex.StartPos.Y);
            if (char == '*') {
                numbersWithStar.add(
                    Triple(
                        numberIndex.Value,
                        Position(numberIndex.StartPos.X + numberIndex.Length, numberIndex.StartPos.Y),
                        numberIndex.StartPos
                    )
                )
            }
        }

    }

    var sum = 0;
    for (star in numbersWithStar) {
        var star2 = numbersWithStar.find { it.second == star.second && it.third != star.third }
        if (star2 != null) {
            sum += star.first * star2.first;
        }
    }
    println("Sum part2: ${sum / 2}")

}

class NumberIndex(startPos: Position, length: Int, value: Int) {
    var StartPos = startPos;
    var Length = length;
    var Value = value;
}
