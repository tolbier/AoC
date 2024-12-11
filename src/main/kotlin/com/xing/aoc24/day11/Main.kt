package com.xing.aoc24.day11


fun main() {
    Day11().main()
}

class Day11 {
    fun main() {
        val row = getRow()
        println(row)
        val resultP1 = p1(row)
        println(resultP1)
        val resultP2 = p2(row)
        println(resultP2)
    }

    fun p1(row: Row): Long =
        row.blinkTimesAndSum(25)

    fun p2(row: Row): Long =
        row.blinkTimesAndSum(75)


    private fun getRow(): Row {
        val fileName = "/com/xing/aoc24/day11/input.txt"
        return try {
            val reader = Day11::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val text = reader!!.readText()
            buildRow(text)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            listOf()
        }
    }


}







