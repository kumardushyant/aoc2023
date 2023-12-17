package tut.dushyant.aoc

enum class Type {
    SEED,
    SOIL,
    FERTILIZER,
    WATER,
    LIGHT,
    TEMPERATURE,
    HUMIDITY,
    LOCATION
}

data class MapInput (
    var sourceType: Type = Type.SEED,
    var destinationType: Type = Type.SEED,
    var values: List<List<Long>> = mutableListOf()
)

data class ListElement (
    val type: Type, val value: Long
)

fun getData(inpFile: String): List<Any> {
    val lines = Common.readInputAsLines(inpFile).filterNot { it.isEmpty() }

    val input: List<Any> = lines[0].removePrefix("seeds: ").split('-', ' ')
    val retList: List<Any> = mutableListOf()
    retList.addFirst(input)

    return retList.plus(lines.drop(1).fold(mutableListOf(mutableListOf<String>())) { acc, st ->
        if (st[0].isLetter())
            acc.add(mutableListOf(st))
        else
            acc.last().add(st)
        acc
    }.filterNot { it.isEmpty() }.map { list ->
        val (sourceType, _, destinationType) = list[0].split('-', ' ')
        val values:MutableList<List<Long>> = mutableListOf()
        list.drop(1).forEach { nums ->
            values.add(nums.split(' ').map { it.toLong() }.toList())
        }
        MapInput(
            enumValueOf<Type>(sourceType.uppercase()),
            enumValueOf<Type>(destinationType.uppercase()),
            values
        )
    })
}


/*private fun generateSeedLinkedList(seed: Long, mapInputs: List<MapInput>): Long =
    mapInputs.fold(seed) { acc, mapInput ->
        mapInput.values.sumOf {
            if ((acc >= it[1]) && (acc < (it[1] + it[2])))
                it[0] + (acc - it[1])
            else
                0
        }.let {
            if (it.toInt() == 0) acc else it
        }
    }*/


fun part1(data:List<Any>) : Long = (data[0] as List<*>).map { it.toString().toLong() }.fold(Long.MAX_VALUE){ acc,seed ->
    val genMin = findMin(data, seed)
    if (genMin <= acc) genMin else acc
}

fun part2(data: List<Any>): Long = (data[0] as List<*>).map {
    it.toString().toLong()
}.toList().chunked(2).minOf { pair ->
    (pair[0]..(pair[0] + pair[1])).toList().fold(Long.MAX_VALUE){acc: Long, seed: Long ->
        val genMin = findMin(data, seed)
        if(genMin <= acc) genMin else acc
    }
}

private fun findMin(data: List<Any>, seed: Long): Long {
    val genMin = data.drop(1).map { it as MapInput }.fold(seed) { acc, mapInput ->
        mapInput.values.sumOf {
            if ((acc >= it[1]) && (acc < (it[1] + it[2])))
                it[0] + (acc - it[1])
            else
                0
        }.let {
            if (it.toInt() == 0) acc else it
        }
    }
    return genMin
}

fun main() {
    val data:List<Any> = getData("day5.txt")

    println(part1(data))
    println(part2(data))
}
