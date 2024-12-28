package com.xing.aoc24.day16


fun main() {
    Day16().main()
}

class Day16 {
    fun main() {
        val puzzle = getPuzzle()
        println(puzzle)
        println(puzzle.lowestScore())
        println(puzzle.numberOfTilesInPaths())
    }

    private fun getPuzzle(): Puzzle {
        val fileName = "/com/xing/aoc24/day16/input.txt"

        return try {
            val reader = Day16::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawPuzzle = reader!!.readText()
            val puzzle = Puzzle(rawPuzzle)
            puzzle
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            Puzzle()
        }
    }
}





