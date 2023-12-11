package Day4

import Common.Input
import kotlin.math.pow


fun main() {
    var input = Input("Day4")

    part1(input)
    part2(input)
}

fun part1(input: Input) {
    var score = 0;
    for (line in input.Get()) {

        var card = line.split(":")
        var cardnums = card[1].split("|")
        var winners = cardnums[0].trim().split("\\s+".toRegex())
        var numbers = cardnums[1].trim().split("\\s+".toRegex())

        var wins = 0
        for (num in numbers) {
            if (num in winners && num != null) {
                wins += 1
            }
        }
        if (wins > 0) {
            var temp = 2.pow(wins - 1).toInt()
            score += temp
        }


    }
    println("Part 1 Score: ${score}")
}

fun part2(input: Input) {
    var cards: MutableList<Card> = arrayListOf()
    for (line in input.Get()) {
        var card = Card()
        var cardline = line.split(":")
        var cardnums = cardline[1].split("|")
        var winners = cardnums[0].trim().split("\\s+".toRegex())
        var numbers = cardnums[1].trim().split("\\s+".toRegex())
        card.number = cardline[0].removePrefix("Card ").trim().toInt()
        card.winners = winners
        card.numbers = numbers
        cards.add(card)
    }


    var calculatedCards = calculateCards(cards)
    var acquiredCards = arrayListOf<CalculatedCard>()

    for (card in calculatedCards){
        acquiredCards.add(card)
        acquiredCards.addAll(getCards(card, calculatedCards));

    }

    for(i in 1..cards.size){

    println("number of $i cards: ${acquiredCards.count{it.number == i}}")
    }

    println("Combined number of cards gained: ${acquiredCards.count()}")

}

class Card {
    var number = 0
    var winners: List<String> = listOf()
    var numbers: List<String> = listOf()
}

class CalculatedCard {
    var number = 0
    var wins = 0

    constructor(number: Int, wins: Int) {
        this.number = number
        this.wins = wins
    }
}

fun calculateCards(cards: List<Card>): List<CalculatedCard> {
    var calculatedCards: MutableList<CalculatedCard> = arrayListOf()
    for (card in cards) {
        var wins = 0
        for (num in card.numbers) {
            if (num in card.winners && num != null) {
                wins += 1
            }
        }
        calculatedCards.add(CalculatedCard(card.number, wins))
    }
    return calculatedCards
}


fun getCards(card: CalculatedCard, calculatedCards: List<CalculatedCard>): List<CalculatedCard> {
    var cards: MutableList<CalculatedCard> = arrayListOf()

    if (card.wins > 0) {
        for (i in 0..card.wins-1) {
            cards += calculatedCards[card.number+i]
            cards += getCards(calculatedCards[card.number + i], calculatedCards)
        }
    }

    return cards
}

fun Int.pow(exponent: Int): Long {
    if (exponent == 0)
        return 1
    var exponent = exponent - 1
    var result: Long = this.toLong();
    while (exponent > 0) {
        result *= this;
        exponent--;
    }
    return result;
}