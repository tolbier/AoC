package com.xing.aoc24.day18


fun main() {
    Day18().main()
}

class Day18 {
    fun main() {
        val puzzle = getPuzzle()
        println(puzzle)
        println(puzzle.lowestDijkstraScore())
    }

    private fun getPuzzle(): Puzzle {
        val fileName = "/com/xing/aoc24/day18/input.txt"

        return try {
            val reader = Day18::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawPuzzle = reader!!.readText()
            val puzzle = Puzzle(rawPuzzle, 1024)
            puzzle
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            Puzzle()
        }
    }
}





