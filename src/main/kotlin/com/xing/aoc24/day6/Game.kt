package com.xing.aoc24.day6

class Game(val maze: Maze) {
    fun playP1(): Maze =
        goSteps(maze)

    fun playP2() {
        /*
        Execute the go Steps
        retrieve a Set A of the VisitedCoords

        those are the candidates to put an obstacle

        for all coords in A
          obsX,obsY
          make a new map with the original and the obstacle
          when goStep, it has to leave breadcrumbs (NESW) with the sense of the way
          if guard enters a cell which has the same breadcrumb of the guarddirection
          then it is a loop
        */
        val initialMaze = goSteps(maze)
        val visitedSet = initialMaze.setOfVisited()
    }

    private fun goSteps(maze: Maze): Maze {
        if (maze.noGuard()) return maze
        return goSteps(maze.goStep())
    }


}





