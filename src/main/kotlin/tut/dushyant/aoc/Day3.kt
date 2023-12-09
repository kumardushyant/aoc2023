package tut.dushyant.aoc

import com.google.common.io.Resources
import java.nio.charset.StandardCharsets

fun readInput(): Array<CharArray> {
    val lines:List<String> = Resources.readLines(Resources.getResource("day3.txt"), StandardCharsets.UTF_8)
    return Array(lines.size) { lines[it].toCharArray() }
}
sealed class InputObj
data class NumberObj(val value: Int, val row: Int, val range: IntRange): InputObj() {

    override fun toString(): String {
        return "{$row,$value,$range}"
    }
}
data class SymbolObj(val value: Char, val row: Int, val idx: Int): InputObj() {
    override fun toString(): String {
        return "[$row:$value:$idx]"
    }
}

fun main() {
    val numbers: MutableList<NumberObj> = mutableListOf()
    val symbols: MutableList<SymbolObj> = mutableListOf()

    fun MutableList<NumberObj>.getNumbersAtRowAndIdx(rowIndex: Int, columnIdx: Int) = this
        .filter { it.row == rowIndex }
        .filter { it.range.contains(columnIdx) }

    readInput().forEachIndexed { rowIdx,row -> run {
        var startIdx = -1
        var currentNum = ""
        row.forEachIndexed { index, c ->
            when {
                (c.isDigit()) -> {
                    currentNum += c
                    if (startIdx == -1) startIdx = index
                    if (index == row.size-1) {
                        if ((c != '.') && (!c.isDigit())) symbols.add(SymbolObj(c, rowIdx, index))
                        if (currentNum.isNotBlank()) {
                            numbers.add(NumberObj(currentNum.toInt(), rowIdx, startIdx..index))
                            currentNum = ""
                            startIdx = -1
                        }
                    }
                }
                else -> {
                    if (c != '.') symbols.add(SymbolObj(c, rowIdx, index))
                    if (currentNum.isNotBlank()) {
                        numbers.add(NumberObj(currentNum.toInt(), rowIdx, startIdx..<index))
                        currentNum = ""
                        startIdx = -1
                    }
                }
            }
        }
    } }

    print("Part 1 => ")
    println(symbols.map { symbol ->
                setOfNotNull(
                    numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx - 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx + 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx),
                    numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx - 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx + 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx),
                    numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx - 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx + 1),
                    numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx)
                )
            }.flatten().toSet().sumOf { objs -> objs.sumOf { it.value } })

    println()
    print("Part 2 => ")
    print(symbols.asSequence().filter { it.value == '*' }.map { symbol ->
        setOfNotNull(
            numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx - 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx + 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row, symbol.idx).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx - 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx + 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row - 1, symbol.idx).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx - 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx + 1).getOrNull(0),
            numbers.getNumbersAtRowAndIdx(symbol.row + 1, symbol.idx).getOrNull(0)
        )
    }.filter { objs -> objs.size == 2 }.map { it.fold(1){ acc, next -> acc * next.value } }.sum())

}