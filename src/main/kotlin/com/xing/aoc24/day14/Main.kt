package com.xing.aoc24.day14


fun main() {
    Day14().main()
}

class Day14 {
    fun main() {
        val space = getSpace()
        println(space)
        val resultP1 = p1(space)
        println(resultP1)
//        val resultP2 = p2(machines)
//        println(resultP2)

    }

    private fun p1(space: Space): Long =
        space.predict(100).safetyFactor()


    private fun getSpace(): Space {
        val fileName = "/com/xing/aoc24/day14/input.txt"
        try {
            val reader = Day14::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawRobots = reader!!.readText()
            return buildSpace(rawRobots)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            return Space()
        }
    }
}

fun buildSpace(rawSpace: String): Space {
    val regex = "p=(\\d+),(\\d+)\\sv=([+\\-]?\\d+),([+\\-]?\\d+)".toRegex()
    val matchResults = regex.findAll(rawSpace)
    val robots = matchResults.map { match ->
        val x = match.groups[1]!!.value.toInt()
        val y = match.groups[2]!!.value.toInt()
        val vX = match.groups[3]!!.value.toInt()
        val vY = match.groups[4]!!.value.toInt()

        Robot(Coords(x, y), Speed(vX, vY))
    }.toList()
    val width = robots.maxOf { it.coords.x } + 1
    val height = robots.maxOf { it.coords.y } + 1
    return Space(width, height, robots)
}




