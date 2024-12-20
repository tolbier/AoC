package com.xing.aoc24.day13


fun main() {
    Day13().main()
}

class Day13 {
    fun main() {
        val machines = getMachines()
        println(machines)
        val resultP1 = p1(machines)
        println(resultP1)
        val resultP2 = p2(machines)
        println(resultP2)
//        val resultP2 = p2(gardenMap)
//        println(resultP2)
    }

    private fun p1(machines: List<Machine>): Long =
        machines.mapNotNull { it.cost() }.sum()

    private fun p2(machines: List<Machine>): Long {
        val extra = 10000000000000L
        return machines.map { it.copy(prizeX = it.prizeX + extra, prizeY = it.prizeY + extra) }.mapNotNull { it.cost() }
            .sum()
    }

//    private fun p2(gardenMap: GardenMap): Int =
//        gardenMap.getSumOfSidesCosts()

    private fun getMachines(): List<Machine> {
        val fileName = "/com/xing/aoc24/day13/input.txt"
        try {
            val reader = Day13::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawMachines = reader!!.readText()
            return buildMachines(rawMachines)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            return emptyList()
        }
    }


}

fun buildMachines(rawMachines: String): List<Machine> {
    val regex =
        "Button\\sA: X\\+(\\d+),\\sY\\+(\\d+)\\nButton\\sB: X\\+(\\d+),\\sY\\+(\\d+)\\nPrize:\\sX=(\\d+),\\sY=(\\d+)".toRegex()
    val matchResults = regex.findAll(rawMachines)
    return matchResults.map { match ->
        Machine(
            aX = match.groups[1]!!.value.toInt(),
            aY = match.groups[2]!!.value.toInt(),
            bX = match.groups[3]!!.value.toInt(),
            bY = match.groups[4]!!.value.toInt(),
            prizeX = match.groups[5]!!.value.toLong(),
            prizeY = match.groups[6]!!.value.toLong(),
        )
    }.toList()
}




