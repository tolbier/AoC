package com.xing.aoc24.day21

data class CoordsNumOfSteps(val coords: Coords, val numOfSteps: NumOfSteps = 0) : Comparable<CoordsNumOfSteps> {
    override fun compareTo(other: CoordsNumOfSteps): Int =
        this.numOfSteps.compareTo(other.numOfSteps)
}