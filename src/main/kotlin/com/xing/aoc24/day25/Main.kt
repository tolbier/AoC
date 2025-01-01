package com.xing.aoc24.day25


fun main() {
    Day25().main()
}

class Day25 {
    fun main() {
        val floor = getFloor()
        println(floor)

        val resultP1: Int = p1(floor)
        println(resultP1)

//        val resultP2 = p2(devices)
//        println(resultP2)


    }

    private fun p1(floor: Floor): Int =
        floor.getFitsNumber()


//    private fun p1(devices: Devices): Long {
//
//        return devices.getOutput()
//    }

    private fun getFloor(): Floor {
        val fileName = "/com/xing/aoc24/day25/input.txt"

        return try {
            val reader = Day25::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawFile = reader!!.readText()
            return buildFloor(rawFile)

        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            emptyList()
        }
    }
}







