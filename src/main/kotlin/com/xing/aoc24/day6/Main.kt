package com.xing.aoc24.day6

fun main() {
    Kata().main()
}

class Kata {
    fun main() {
        val maze = getMaze()
        val game = Game(maze)
        println(maze.draw())
        println()
//        println(maze.draw())
        val resultP1 = game.playP1()
//
        println(resultP1)
//
        val resultP2 = game.playP2()
        println(resultP2)
    }


    private fun getMaze(): Maze {
        val fileName = "/com/xing/aoc24/day6/input.txt"

        return Maze(try {
            val reader = Kata::class.java.getResourceAsStream(fileName)?.bufferedReader()
            reader.use { reader!!.readText() }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            ""
        })
    }
}







