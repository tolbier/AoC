package com.xing.aoc24.day15

data class Block(val type: BlockType, val coordsList: List<Coords>) {
    fun move(puzzle: Puzzle, movement: Movement): Puzzle? =
        type.move(puzzle, movement, coordsList)
}

fun Block?.isNotEmpty() = this != null