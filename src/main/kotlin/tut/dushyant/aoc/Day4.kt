package tut.dushyant.aoc

import kotlin.math.pow

data class Card (val id:Int, val leftArray: List<Int>, val rightArray: List<Int>) {

    override fun toString(): String {
        return "ID: $id with left array as ${leftArray.toList()} and right array as ${rightArray.toList()}"
    }
}

fun getCards(): List<Card> = Common.readInputAsArray("testday4.txt").map { row ->
    val (idPart, winArray, myArray) = row.joinToString(separator = "").split(":", " | ")
    Card(idPart.substring(4).trim().toInt(),
        winArray.chunked(3).map { it.trim().toInt() },
        myArray.chunked(3).map { it.trim().toInt() })
}

fun main() {

    //part 1
    /*println(getCards().sumOf { card ->
        2.0.pow( card.leftArray.count { it in card.rightArray }-1).toInt()
    })*/

    //part 2
    //map of card to found
    getCards().map { card ->
        val found:Int = card.leftArray.count { it in card.rightArray }
        card to found
    }.reversed().fold(emptyList<Int>()) { acc, (card,found) ->
        listOf(1 + (0..found).sumOf { acc[card.id] })+acc
    }.sum().also(::println)

}