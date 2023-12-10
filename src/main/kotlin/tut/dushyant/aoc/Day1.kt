package tut.dushyant.aoc

fun main() {
    val numbers: Map<String, Int> = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun calibrateValue(line: String): Int {
        return "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
    }

    fun String.isDigitWord(idx: Int): List<String> = (3..5).map {
            size: Int -> substring(idx, (idx + size).coerceAtMost(length))
    }

    println()
    print("Part 1 => ")
    Common.readInputAsLines("day1.txt").sumOf { calibrateValue(it) }.also(::println)

    print("Part 2 => ")
    Common.readInputAsLines("day1.txt").sumOf {
        line:String -> calibrateValue(line.mapIndexedNotNull { index, c ->
        if (c.isDigit()) c else line.isDigitWord(index).firstNotNullOfOrNull { numbers[it] }
    }.joinToString())}.also(::println)
}

