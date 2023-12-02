package Day1

import Common.Input
import java.io.File

var input: Input = Input("Day1")
val writtenNumbers:Map<String, Int> = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {

    println("part1: " + part1());
    println("part2: " + part2());
}


private fun part2() : Int {

    var numbers: MutableList<Int> = arrayListOf();
    for (line in input.Get()) {
        var indexes: MutableMap<Int, Int> = mutableMapOf();
        if (writtenNumbers.containsAKey(line)) {
            for (num in writtenNumbers) {
                if(num.key in line){
                    var firstindex = line.indexOf(num.key)
                    var lastindex = line.lastIndexOf(num.key)
                    if(firstindex != lastindex){
                        indexes.put(firstindex,num.value);
                        indexes.put(lastindex,num.value);
                    } else {
                        indexes.put(firstindex,num.value);
                    }
                }
            }
            for(digit in line) {
                if(digit.isDigit()){
                    var firstindex = line.indexOf(digit)
                    var lastindex = line.lastIndexOf(digit)
                    if(firstindex != lastindex){
                        indexes.put(firstindex,digit.digitToInt());
                        indexes.put(lastindex,digit.digitToInt());
                    } else {
                        indexes.put(firstindex,digit.digitToInt());
                    }
                }
            }

        var number = indexes[indexes.keys.min()].toString() + indexes[indexes.keys.max()].toString()
        numbers.add(number.toInt());
        } else {
            numbers.add(firstAndLastOf(line).sum())
        }

    }

    return numbers.sum();
}

private fun part1() : Int{
    var numbers:MutableList<Int> = arrayListOf();
    for (line in input.Get()){
        numbers.add(firstAndLastOf(line).sum())
    }

    return numbers.sum()
}



private fun firstAndLastOf(line: String) : Pair<Char,Char> {
    return Pair(line.first{it.isDigit()},line.last{it.isDigit()})
}

private fun MutableList<Int>.sum(): Int {
    var temp = 0;
    for (num:Int in this){
        temp += num;
    }
    return temp;
}

private fun Map<String, Int>.containsAKey(inputString: String): Boolean {
    for (key in this.keys) {
        if (inputString.contains(key)) {
            return true
        }
    }
    return false
}

private fun Pair<Char, Char>.sum(): Int {
    return (this.first.toString() + this.second.toString()).toInt();
}