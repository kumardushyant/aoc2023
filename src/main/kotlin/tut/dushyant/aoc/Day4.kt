package tut.dushyant.aoc

import kotlin.math.pow


data class Card (val id:Int, val leftArray: List<Int>, val rightArray: List<Int>) {

    override fun toString(): String {
        return "ID: $id with left array as ${leftArray.toList()} and right array as ${rightArray.toList()}"
    }
}

fun getCards(inpFile: String): List<Card> = Common.readInputAsArray(inpFile).map { row ->
    val (idPart, winArray, myArray) = row.joinToString(separator = "").split(":", " | ")
    Card(idPart.substring(4).trim().toInt(),
        winArray.chunked(3).map { it.trim().toInt() },
        myArray.chunked(3).map { it.trim().toInt() })
}

fun part1(fileToTest: String) = getCards(fileToTest).sumOf { card ->
        2.0.pow( card.leftArray.count { it in card.rightArray }-1).toInt()
    }

fun part2(fileToTest: String) = getCards(fileToTest).map { card ->
        val found:Int = card.leftArray.count { it in card.rightArray }
        card to found
    }.reversed().fold(emptyList<Int>()) { acc, (_,found) ->
        val sum = 1 + (0..<found).sumOf {
            if(acc.isEmpty())
                0
            else
                acc[it]
        }
        listOf(sum)+acc
    }.sum()

fun main() {

    val fileToTest = "day4.txt"
    println(part1(fileToTest))

    println(part2(fileToTest))

}