package com.xing.aoc24.day21

data class DijkstraResult(val distances: Map<Coords, NumOfSteps>, val pathMap: Map<Coords, Set<Coords>>) {
    fun lowestNumOfSteps(coords: Coords): NumOfSteps {
        val exitScore = distances.get(coords)!!
        return exitScore
    }
}