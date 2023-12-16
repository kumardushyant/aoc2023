package tut.dushyant.aoc

import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    @Order(1)
    fun testPart1() {
        val inpFile = "testday5.txt"
        println(part1(getData(inpFile)))
    }

    @Test
    @Order(2)
    fun testPart2() {
        val inpFile = "testday5.txt"
        println(part2(getData(inpFile)))
    }
}