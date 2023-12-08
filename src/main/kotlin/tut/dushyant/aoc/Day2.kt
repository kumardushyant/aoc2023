import com.google.common.io.Resources
import java.nio.charset.StandardCharsets

fun main() {
    data class Cubes(val red: Int, val blue: Int, val green: Int)

    data class Game(val id:Int, val cubeSet: List<Cubes>)

    val limitSet = Cubes(12,14,13)

    fun parse(lines: List<String>): List<Game> = lines.map { line ->
        val (game: String, sets: String) = line.split(":")
        val gameId: Int = "Game (\\d+)".toRegex().find(game)!!.groupValues[1].toInt()
        sets.split(";").map {
            val red: Int = "(\\d+) red".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
            val blue: Int = "(\\d+) blue".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
            val green: Int = "(\\d+) green".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?:0
            Cubes(red, blue, green)
        }.let { Game(gameId, it) }
    }

    fun powerOfGame(game: Game): Int = game.cubeSet.maxOf { it.red } * game.cubeSet.maxOf { it.blue } * game.cubeSet.maxOf { it.green }

    println(parse(Resources.readLines(Resources.getResource("day2.txt"), StandardCharsets.UTF_8)).filter { game ->
        game.cubeSet.none {
            it.red > limitSet.red || it.blue > limitSet.blue || it.green > limitSet.green
        }
    }.sumOf { it.id })

    print(parse(Resources.readLines(Resources.getResource("day2.txt"), StandardCharsets.UTF_8)).sumOf { powerOfGame(it) })

}
