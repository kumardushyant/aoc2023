package tut.dushyant.aoc

import com.google.common.io.Resources
import java.nio.charset.StandardCharsets

class Common {
    companion object {
        fun readInputAsArray(file: String): Array<CharArray> {
            val lines:List<String> = Resources.readLines(Resources.getResource(file), StandardCharsets.UTF_8)
            return Array(lines.size) { lines[it].toCharArray() }
        }

        fun readInputAsLines(file: String): List<String> = Resources.readLines(Resources.getResource(file), StandardCharsets.UTF_8)
    }
}