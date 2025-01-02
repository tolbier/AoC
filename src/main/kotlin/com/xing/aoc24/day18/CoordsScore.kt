package com.xing.aoc24.day18

data class CoordsScore(val coords: Coords = Coords(), val score: Score = 0L) : Comparable<CoordsScore> {
    override fun compareTo(other: CoordsScore): Int =
        this.score.compareTo(other.score)


}

