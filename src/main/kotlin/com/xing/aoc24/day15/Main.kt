package com.xing.aoc24.day15


fun main() {
    Day15().main()
}

class Day15 {
    fun main() {
        val (puzzle, movements) = gePuzzleAndMovements()
        val widerPuzzle = Puzzle(puzzle.draw().wider())
//        println(puzzle)
//        println(movements)
        val game = Game(puzzle, movements)
//        println(game.draw())
        val resultP1 = p1(game)
        println(resultP1)
        val widerGame = Game(widerPuzzle, movements)
        val resultP2 = p1(widerGame)
        println(resultP2)
    }

    private fun p1(game: Game): Long {
        return game.playAndSumAllBoxesGPSCoords()
    }

    private fun gePuzzleAndMovements(): Pair<Puzzle, List<Movement>> {
        val fileName = "/com/xing/aoc24/day15/input.txt"

        return try {
            val reader = Day15::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawPuzzleAndMovements = reader!!.readText()
            val (rawPuzzle, rawMovements) = rawPuzzleAndMovements.split("\n\n")
            val puzzle = Puzzle(rawPuzzle)
            val movements = rawMovements.buildMovements()
            Pair(puzzle, movements)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            Pair(Puzzle(), emptyList())
        }
    }
}





