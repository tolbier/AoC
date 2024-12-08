package com.xing.aoc24.day6

class Game(val maze: Maze) {
    fun playP1(): Int {
        val lastMaze = maze.goSteps()
        println(lastMaze.draw())
        return lastMaze.countVisited()
    }


    fun playP2(): Int {
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
        val initialMaze = maze.goSteps()
        val visitedCells = initialMaze.setOfVisited().toList()

        val result = visitedCells.map { obstacleCoords ->
            maze.setCell('#', obstacleCoords)
        }.foldIndexed(0) { idx, acc, mazeWithObstacle ->
            println(idx)
            try {
                mazeWithObstacle.goSteps()
                acc
            } catch (ce: CycleException) {
                acc + 1
            }
        }
        return result
    }
//
//    //    private fun goSteps(maze: Maze, debug: Boolean = false): Maze {
////
////        if (debug) {
////            println()
////            println()
////            println()
////            println(maze.draw())
////        }
////        if (maze.noGuard()) return maze
////        return goSteps(maze.goStep(), debug)
////    }


}





