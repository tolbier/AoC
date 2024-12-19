package com.xing.aoc24.day12


fun main() {
    Day16().main()
}

class Day16 {
    fun main() {
        val gardenMap = getGardenMap()
        println(gardenMap)
        val resultP1 = p1(gardenMap)
        println(resultP1)
        val resultP2 = p2(gardenMap)
        println(resultP2)
    }

    private fun p1(gardenMap: GardenMap): Int =
        gardenMap.getSumOfPerimeterCosts()

    private fun p2(gardenMap: GardenMap): Int =
        gardenMap.getSumOfSidesCosts()

    private fun getGardenMap(): GardenMap {
        val fileName = "/com/xing/aoc24/day12/input.txt"
        try {
            val reader = Day16::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawMap = reader!!.readText()
            return GardenMap(rawMap)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            return GardenMap()
        }
    }


}






