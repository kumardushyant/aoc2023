package tut.dushyant.aoc

import kotlin.math.pow

data class Card (val id:Int, val leftArray: IntArray, val rightArray: IntArray) {

    override fun toString(): String {
        return "ID: $id with left array as ${leftArray.toList()} and right array as ${rightArray.toList()}"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (id != other.id) return false
        if (!leftArray.contentEquals(other.leftArray)) return false
        if (!rightArray.contentEquals(other.rightArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + leftArray.contentHashCode()
        result = 31 * result + rightArray.contentHashCode()
        return result
    }
}

fun main() {

    println(Common.readInputAsArray("day4.txt").map { row ->
        val id:Int = row.copyOfRange(4, row.indexOf(':')).joinToString(separator = "").trim().toInt()
        Card(id,
            row.copyOfRange(row.indexOf(':') + 1, row.indexOf('|')).joinToString(separator = "").trim().split(" ")
                .filter { it.isNotBlank() }.map {
                    it.toInt()
                }.toIntArray(),
            row.copyOfRange(row.indexOf('|') + 1, row.size).joinToString(separator = "").trim().split(" ")
                .filter { it.isNotBlank() }.map {
                    it.toInt()
                }.toIntArray())
    }.sumOf {
        2.0.pow((it.leftArray.asIterable().intersect(it.rightArray.asIterable().toSet()).count().toDouble())-1).toInt()
    })
}