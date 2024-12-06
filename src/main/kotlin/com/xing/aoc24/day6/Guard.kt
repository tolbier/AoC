package com.xing.aoc24.day6

data class Guard(val direction: Direction, val coords: Coords) {
    fun nextCoords(): Coords {
        return direction.goStep(coords)
    }

    fun turn() = Guard(direction.turn(), coords)

    fun move(coords: Coords): Guard = copy(coords = coords)

    fun moveNext(): Guard = move(nextCoords())
    fun nextIsBlock(maze: Maze) = maze.getCell(this.nextCoords()) == '#'

}
