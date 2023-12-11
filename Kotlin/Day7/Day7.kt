package Day7

import Common.Input

var powers = mutableMapOf<Char, Int>(
    '2' to 2,
    '3' to 3,
    '4' to 4,
    '5' to 5,
    '6' to 6,
    '7' to 7,
    '8' to 8,
    '9' to 9,
    'T' to 10,
    'J' to 11,
    'Q' to 12,
    'K' to 13,
    'A' to 14
)

fun main() {
    var input = Input("Day7")


    var hands: MutableList<Hand> = arrayListOf()
    var handsWJoker: MutableList<Hand> = arrayListOf()
    for (game in input.Get()) {
        var hand = Hand(game)
        var handwjoker = Hand(game)
        //part 1
        hand.calculateType()
        hands.add(hand)

        //part 2
        powers['J'] = 1;
        handwjoker.calculateTypeWJoker()
        handsWJoker.add(handwjoker)
    }

    //part 1
    hands.sortBy { it.type }
    hands.sortWith { hand1, hand2 -> hand1.greaterThan(hand2) }

    var totalWinningsPart1 = 0
    for (hand in hands) {
        hand.rank = hands.indexOf(hand) + 1
        totalWinningsPart1 += hand.rank * hand.bet
    }
    println(totalWinningsPart1)


    //part 2
    handsWJoker.sortBy { it.type }
    handsWJoker.sortWith { hand1, hand2 -> hand1.greaterThan(hand2) }

    var totalWinningsPart2 = 0
    for (hand in handsWJoker) {
        hand.rank = handsWJoker.indexOf(hand) + 1
        totalWinningsPart2 += hand.rank * hand.bet
    }
    println(totalWinningsPart2)
}

enum class Type {
    HighCard,
    OnePair,
    TwoPair,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind
}

class Hand {
    var rank = 0
    var cards = ""
    var type = Type.HighCard
    var bet = 0

    constructor(hand: String) {
        cards = hand.split(" ")[0]
        bet = hand.split(" ")[1].toInt()
    }

    fun greaterThan(otherHand: Hand): Int {
        if (this.type != otherHand.type) {
            if (this.type > otherHand.type)
                return 1
            else if (this.type < otherHand.type)
                return -1
            else
                return 0
        }

        for (i in 0..4) {
            if (powers[this.cards[i]] != powers[otherHand.cards[i]]) {
                if (powers[this.cards[i]]!! > powers[otherHand.cards[i]]!!)
                    return 1
                else if (powers[this.cards[i]]!! < powers[otherHand.cards[i]]!!)
                    return -1
                else
                    return 0
            }

        }
        println("How did we get here? ${this.cards} and ${otherHand.cards}")
        if (this.cards > otherHand.cards)
            return 1
        else if (this.cards < otherHand.cards)
            return -1
        else
            return 0
    }

    fun calculateType(): Type {
        if (this.uniques() == 1) {
            this.type = Type.FiveOfAKind;
        } else if (this.uniques() == 2) {
            var charsCount = countUniqueCharacters(this.cards)
            if (charsCount.values.any { it > 3 }) {
                this.type = Type.FourOfAKind
            } else {
                this.type = Type.FullHouse
            }
        } else if (this.uniques() == 3) {
            var charsCount = countUniqueCharacters(this.cards)
            if (charsCount.values.any { it > 2 }) {
                this.type = Type.ThreeOfAKind
            } else {
                this.type = Type.TwoPair
            }
        } else if (this.uniques() == 4) {
            this.type = Type.OnePair
        } else if (this.uniques() == 5) {
            this.type = Type.HighCard
        }

        return this.type
    }

    fun uniques(): Int {
        return cards.chars().distinct().count().toInt()
    }

    fun calculateTypeWJoker() : Type {
        if (this.uniques() == 1 || this.uniques() == 2 && this.cards.contains('J')) {
            this.type = Type.FiveOfAKind;
        } else if (this.uniques() == 2 || this.uniques() == 3 && this.cards.contains('J')) {
            var charsCount = countUniqueCharacters(this.cards, true)
            if (charsCount.values.any { it > 3 }) {
                this.type = Type.FourOfAKind
            } else {
                this.type = Type.FullHouse
            }
        } else if (this.uniques() == 3 || this.uniques() == 4 && this.cards.contains('J')) {
            var charsCount = countUniqueCharacters(this.cards, true)
            if (charsCount.values.any { it > 2 }) {
                this.type = Type.ThreeOfAKind
            } else {
                this.type = Type.TwoPair
            }
        } else if (this.uniques() == 4 || this.uniques() == 5 && this.cards.contains('J')) {
            this.type = Type.OnePair
        } else if (this.uniques() == 5) {
            this.type = Type.HighCard
        }
        println("${this.cards} is a ${this.type}")
        return this.type
    }
}

fun countUniqueCharacters(inputString: String, withJoker: Boolean = false): Map<Char, Int> {
    val charCount = mutableMapOf<Char, Int>()

    for (char in inputString) {
        charCount[char] = charCount.getOrDefault(char, 0) + 1
    }
    if(withJoker && charCount.contains('J')){
        var amount = charCount['J']
        var largest = charCount.maxBy { it.value }
        charCount.remove('J')
        charCount.set(largest.key, largest.value + amount!!)
    }
    return charCount
}