package com.xing.aoc24.day15

class Game(val puzzle: Puzzle, val movements: List<Movement>) {
    val debug = false
    fun draw(): String {
        return puzzle.draw()
    }

    fun play(): Puzzle {
        return movements.fold(puzzle) { accPuzle, movement ->
            val result = accPuzle.moveRobot(movement)
            if (debug) {
                println(movement.char)
                println(result.draw())
            }
            result
        }
    }

    fun playAndSumAllBoxesGPSCoords(): Long =
        play().sumAllBoxesGPSCoords()

}
