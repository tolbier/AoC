package com.xing.aoc24.day4

fun main(){
    Day4().main()
}
class Day4 {
   fun main(){
       val puzzle = getPuzzle()
       println(puzzle)
       val puzzle90Clockwise = puzzle.rotate90ClockWise()
       println(puzzle90Clockwise)
       val puzzle45Clockwise = puzzle.rotate45ClockWise()
       println(puzzle45Clockwise)
       val puzzle45Anticlockwise = puzzle.rotate45AnticlockWise()
       println(puzzle45Anticlockwise)

       val resultP1 =
           p1(puzzle, puzzle90Clockwise, puzzle45Clockwise, puzzle45Anticlockwise)
        println(resultP1)

       val resultP2 =
           p2(puzzle)
       println(resultP2)

   }

    private fun p2(puzzle: Puzzle): Int {
        var result = 0
        puzzle.indices.forEach { y ->
            puzzle.indices.forEach { x->
                if (puzzle.isXMas(x,y))
                    result++
            }
        }
        return result
    }

    private fun p1(
        puzzle: Puzzle,
        puzzle90Clockwise: Puzzle,
        puzzle45Clockwise: Puzzle,
        puzzle45Anticlockwise: Puzzle
    ) = puzzle.countXMAS() + puzzle.countSAMX() +
            puzzle90Clockwise.countXMAS() + puzzle90Clockwise.countSAMX() +
            puzzle45Clockwise.countXMAS() + puzzle45Clockwise.countSAMX() +
            puzzle45Anticlockwise.countXMAS() + puzzle45Anticlockwise.countSAMX()

    private fun getPuzzle ():Puzzle {
        val fileName = "/com/xing/aoc24/day4/input.txt"

       return  try {
            val a = Day4::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a.use { it!!.readLines() }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
           emptyList()
        }
    }
}




