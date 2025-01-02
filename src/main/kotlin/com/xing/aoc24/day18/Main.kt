package com.xing.aoc24.day18


fun main() {
    Day18().main()
}

class Day18 {
    fun main() {
        val puzzle = getPuzzle()
        println(puzzle)
        val resultP1 = p1(puzzle)
        println(resultP1)

        val resultP2 = p2(puzzle)
        println(resultP2)
    }

    fun p1(puzzle: Puzzle) = puzzle.lowestDijkstraScore()
    fun p2(puzzle: Puzzle): Coords? = puzzle.firstCorruptedByteThatBlockedTheWay()
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





