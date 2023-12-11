package Day9

import Common.Input

fun main() {

    var input = Input("Day9")

    var sequences = arrayListOf<MutableList<Int>>()

    for (sequence in input.Get()){
        var seqlist = sequence.trim().split("\\s+".toRegex())
        var nums = arrayListOf<Int>()
        for (num in seqlist){
            nums.add(num.toInt())
        }
        sequences.add(nums)
    }

    var sumN = 0
    var sumP = 0
    for (sequence in sequences){
        var next = getNextNumber(sequence);
        var prev = getPreviousNumber(sequence);
        sumN += next
        sumP += prev
        println("$sequence next: ${next}, previous: $prev");
    }
    println("Next sum (part 1): " + sumN)
    println("Previous sum (part 2): " + sumP)
}

fun getNextNumber(sequence: MutableList<Int>) : Int {

    var numsbetween = arrayListOf<Int>()

    for (i in 0 until sequence.size-1) {
        numsbetween.add(sequence[i+1] - sequence[i])
    }
    if(isAllNumbersEqual(numsbetween)){
        return numsbetween[0] + sequence.last()
    } else {
        var nextnum = getNextNumber(numsbetween);
        return nextnum + sequence.last();
    }
}

fun isAllNumbersEqual(numsbetween: ArrayList<Int>): Boolean {
    return numsbetween.sum() / numsbetween.count() == numsbetween[0];
}

fun getPreviousNumber(sequence: MutableList<Int>) : Int {

    var numsbetween = arrayListOf<Int>()

    for (i in 0 until sequence.size-1) {
        numsbetween.add(sequence[i+1] - sequence[i])
    }
    if(isAllNumbersEqual(numsbetween)){
        return sequence.first() - numsbetween.first()
    } else {
        var prevnum = getPreviousNumber(numsbetween);
        return sequence.first() - prevnum;
    }
}