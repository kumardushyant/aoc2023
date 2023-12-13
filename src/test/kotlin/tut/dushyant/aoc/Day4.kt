package tut.dushyant.aoc

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {

    @Test
    @DisplayName("Testing getting cards")
    fun testGetCards() {
         getCards("testDay4.txt").sortedBy { it.id }
    }

    @Test
    @DisplayName("Testing Part 1")
    fun testPart1() {
       assertEquals(13, part1("testDay4.txt"), "Part 1 test failed")
    }

    @Test
    @DisplayName("Testing Part 2")
    fun testPart2() {
       assertEquals(30,part2("testDay4.txt"),"Part 2 test failed")
    }
}