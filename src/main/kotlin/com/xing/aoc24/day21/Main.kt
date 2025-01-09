package com.xing.aoc24.day21


fun main() {
    Day21().main()
}

class Day21 {
    val numericKeyPad = KeyPad.NumericKeyPad()
    val directionalKeyPad1 = KeyPad.DirectionalKeyPad(numericKeyPad)
    val directionalKeyPad2 = KeyPad.DirectionalKeyPad(directionalKeyPad1)

    fun main() {
        val inputs = getInputs()
        println(inputs)
        val resultP1 = p1(inputs)
        println(resultP1)

//        val resultP2 = p2(puzzle)
//        println(resultP2)
    }

    fun p1(inputs: List<String>) =
        directionalKeyPad2.complexity(inputs)


    //    fun p2(puzzle: Puzzle): Coords? = puzzle.firstCorruptedByteThatBlockedTheWay()
    private fun getInputs(): List<String> {
        val fileName = "/com/xing/aoc24/day21/input.txt"

        return try {
            val reader = Day21::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawInputs = reader!!.readText()
            rawInputs.split("\n")
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            listOf<String>()
        }
    }
}





