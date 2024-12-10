package com.xing.aoc24.day10


fun main() {
    Day10().main()
}

class Day10 {
    fun main() {
        val map = getMap()
        println(map)
        val resultP1 = p1(map)
        println(resultP1)
        val resultP2 = p2(map)
        println(resultP2)
    }

    private fun p1(coordMap: CoordMap): Int =
        coordMap.getSumOfScores()

    private fun p2(coordMap: CoordMap): Int =
        coordMap.getSumOfRatings()


//    private fun p2(diskMap: List<Int>): BigInteger {
//        val (file, pointers) = generateFileWithPointers(diskMap)
//        val compactedFile = compactFileP2(file, pointers)
//        val checksum = calculateCheckSumP2(compactedFile)
//        return checksum
//    }


    private fun getMap(): CoordMap {
        val fileName = "/com/xing/aoc24/day10/input.txt"
        return try {
            val reader = Day10::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val text = reader!!.readText()
            buildCoordMap(text)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            mapOf()
        }
    }


}





