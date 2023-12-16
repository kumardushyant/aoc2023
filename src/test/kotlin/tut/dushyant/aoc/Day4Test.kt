package tut.dushyant.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun testCardIdPart() {
        val idPart = "ID: 1234"
        val card = Card(idPart.substring(4).trim().toInt(), emptyList(), emptyList())
        assertEquals(1234, card.id)
    }

    @Test
    fun testGetCards() {
        val inpFile = "testday4.txt"
        val expectedCards = listOf(
            Card(1, listOf(41,48,83,86,17),  listOf(83,86,6,31,17,9,48,53)),
            Card(2, emptyList(), emptyList()),
            Card(3, emptyList(), emptyList()),
            Card(4, emptyList(), emptyList()),
            Card(5, emptyList(), emptyList()),
            Card(6, emptyList(), emptyList())
        )
        val actualCards = getCards(inpFile)
        assertEquals(expectedCards, actualCards)
    }

    @Test
    fun testPart1() {
        val fileToTest = "testday4.txt"
        val expectedSum = 0 // Replace with expected sum
        val actualSum = part1(fileToTest)
        assertEquals(expectedSum, actualSum)
    }

    @Test
    fun testPart2() {
        val fileToTest = "testday4.txt"
        val expectedSum = 0 // Replace with expected sum
        val actualSum = part2(fileToTest)
        assertEquals(expectedSum, actualSum)
    }
}