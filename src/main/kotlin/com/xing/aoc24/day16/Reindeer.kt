package com.xing.aoc24.day16

data class Reindeer(val coords: Coords = Coords(), val direction: Direction = Direction.EAST) {
    fun next(direction: Direction): Coords = coords.next(direction)
    fun moveNext(direction: Direction): Reindeer =
        copy(coords = coords.next(direction))
}

