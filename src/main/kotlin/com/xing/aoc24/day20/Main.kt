package com.xing.aoc24.day20


fun main() {
    Day20().main()
}

class Day20 {
    fun main() {
        val puzzle = getPuzzle()

        val resultP1 = p1(puzzle)
        println(resultP1)

//        val resultP2 = p2(puzzle)
//        println(resultP2)
    }

    fun p1(puzzle: Puzzle): Int {
        val map = puzzle.getSavingsCount(geThanPseconds = 100)
        val sumOfValues = map.values.sum()
        return sumOfValues
    }

    private fun getPuzzle(): Puzzle {
        val fileName = "/com/xing/aoc24/day20/input.txt"

        return try {
            val reader = Day20::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawPuzzle = reader!!.readText()
            val puzzle = Puzzle(rawPuzzle)
            puzzle
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            Puzzle()
        }
    }
}





