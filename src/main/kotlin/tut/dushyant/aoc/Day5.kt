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


private fun generateSeedLinkedList(seed: Long, mapInputs: List<MapInput>): List<ListElement> {

    var start: Long = seed

    return mapInputs.map {mapInput ->
        val listElem = ListElement(mapInput.sourceType, start)
        start = mapInput.values.sumOf {
            if ((start >= it[1]) && (start < (it[1] + it[2])))
                it[0] + (start - it[1])
            else
                0
        }.let {
            if (it.toInt() == 0) start else it
        }
        listElem
    }.plus(ListElement(Type.LOCATION,start))
}

fun part1(data:List<Any>): Long = (data[0] as List<*>).map { it.toString().toLong() }.map { seed ->
        generateSeedLinkedList(seed, data.drop(1).map { it as MapInput })
            .filter {
            it.type == Type.LOCATION
        }.map {
            it.value
        }
    }.flatten().min()

fun part2(data:List<Any>): Long = (data[0] as List<*>).map { it.toString().toLong() }.toList().chunked(2).map { pair ->
        (pair[0]..(pair[0]+pair[1])).toList().parallelStream().map {seed ->
            generateSeedLinkedList(seed, data.drop(1).map { it as MapInput }).filter {
                it.type == Type.LOCATION
            }.map {
                it.value
            }
        }.toList().flatten()
    }.flatten().min()

fun main() {
    val data:List<Any> = getData("day5.txt")

    println(part1(data))

    println(part2(data))
}
