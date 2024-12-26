package com.xing.aoc24.day15

data class Robot(val coords: Coords = Coords()) {
    fun next(movement: Movement): Coords = coords.next(movement)
    fun moveNext(movement: Movement): Robot =
        copy(coords = coords.next(movement))
}

