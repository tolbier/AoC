package com.xing.aoc24.day6

class Game(val maze: Maze) {
    fun play(): Maze =
        goSteps(maze)


    private fun goSteps(maze: Maze): Maze {
        if (maze.noGuard()) return maze
        return goSteps(maze.goStep())
    }


}




