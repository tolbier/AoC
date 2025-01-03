package com.xing.aoc24.day20

data class DijkstraResult(val pseconds: Map<Coords, Psecond>, val pathMap: Map<Coords, Set<Coords>>) {
    fun lowestPsecond(coords: Coords): Psecond {
        val scores = this.pseconds
        val exitScore = scores.get(coords)!!
        return exitScore
    }

    fun getMapCoordsAndTime(baseTime: Psecond) =
        pseconds.mapValues { baseTime - it.value }

}