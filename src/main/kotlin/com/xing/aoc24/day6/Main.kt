package com.xing.aoc24.day6

fun main() {
    Kata().main()
}

class Kata {
    fun main() {
        val maze = getMaze()
        val game = Game(maze)
        println(maze.draw())
        val resultP1 = game.playP1()

        println(resultP1.draw())
        println(resultP1.countVisited())
    }

    private fun getMaze(): Maze {
        val fileName = "/com/xing/aoc24/day6/input.txt"

        return try {
            val a = Kata::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a.use { it!!.readLines() }

        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            emptyList()
        }
    }
}







