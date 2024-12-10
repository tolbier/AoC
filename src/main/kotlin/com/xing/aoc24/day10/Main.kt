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

    private fun p1(map: Map): Int =
        map.getSumOfScores()

    private fun p2(map: Map): Int =
        map.getSumOfRatings()


//    private fun p2(diskMap: List<Int>): BigInteger {
//        val (file, pointers) = generateFileWithPointers(diskMap)
//        val compactedFile = compactFileP2(file, pointers)
//        val checksum = calculateCheckSumP2(compactedFile)
//        return checksum
//    }


    private fun getMap(): Map {
        val fileName = "/com/xing/aoc24/day10/input.txt"
        return try {
            val reader = Day10::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val result = reader!!.use {
                reader.lineSequence()
                    .map { line ->
                        line.toList().map { it.toString().toInt() }
                    }.toList()
            }
            result
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            listOf()
        }
    }


}





