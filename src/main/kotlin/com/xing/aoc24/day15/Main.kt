package com.xing.aoc24.day15

fun main() {
    Day15().main()
}

class Day15 {
    fun main() {
        val (puzzle, movements) = gePuzzleAndMovements()
        println(puzzle)
        println(movements)
        val resultP1 = p1(puzzle, movements)
        println(resultP1)
//       val resultP2 = p2(updates,rules)
//       println(resultP2)
    }

    private fun p1(puzzle: Puzzle, movements: List<Movement>): Long {
        return puzzle.order(movements).sumAllBoxesGPSCoords()
    }

//    private fun p1(updates: Updates, rules: Rules): Int {
//        val sumOfMiddleCorrectUpdates= updates.getSumofMiddleCorrectUpdates(rules)
//        return sumOfMiddleCorrectUpdates
//    }
//    private fun p2(updates: Updates, rules: Rules): Int {
//        val sumOfMiddleWrongAndSortedUpdates= updates.getSumofMiddleWrongAndSortedUpdates(rules)
//        return sumOfMiddleWrongAndSortedUpdates
//
//    }

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





