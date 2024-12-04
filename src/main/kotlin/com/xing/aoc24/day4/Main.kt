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

       val result =
           puzzle.countXMAS() + puzzle.countSAMX() +
               puzzle90Clockwise.countXMAS() + puzzle90Clockwise.countSAMX()+
                   puzzle45Clockwise.countXMAS() + puzzle45Clockwise.countSAMX() +
                puzzle45Anticlockwise.countXMAS() + puzzle45Anticlockwise.countSAMX()
   println(result)
   }

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



