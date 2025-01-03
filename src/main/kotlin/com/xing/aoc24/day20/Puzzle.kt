package com.xing.aoc24.day20

import java.util.*


data class Puzzle(
    val blocks: List<Coords> = emptyList(),
    val start: Coords = Coords(0, 0),
    val exit: Coords = Coords(0, 0)
) {
    val height = blocks.maxOfOrNull { it.y }?.plus(1) ?: 0
    val width = blocks.maxOfOrNull { it.x }?.plus(1) ?: 0
    val freeBlocks: Set<Coords> =
        (0..<height).flatMap { y ->
            (0..<width).map { x ->
                Coords(x, y)
            }
        }.toSet().minus(blocks)
    val allCoords = blocks.union(freeBlocks)

    constructor(puzzle: Puzzle) : this(puzzle.blocks, puzzle.start, puzzle.exit)
    constructor(rawPuzzle: String) : this(buildPuzzle(rawPuzzle))

    companion object {
        private fun buildPuzzle(rawPuzzle: String): Puzzle {
            val blocks = mutableListOf<Coords>()
            var start: Coords? = null
            var exit: Coords? = null
            rawPuzzle.trimIndent().split("\n")
                .forEachIndexed() { y, line ->
                    line.forEachIndexed() { x, char ->
                        val coords = Coords(x, y)
                        when (CellType.fromChar(char)) {
                            CellType.BLOCK -> blocks.add(coords)
                            CellType.EXIT -> exit = coords
                            CellType.START -> start = coords
                            else -> {}
                        }
                    }

                }
            return Puzzle(blocks, start!!, exit!!)
        }
    }

    fun draw(): String {
        val listResult = MutableList(height) {
            MutableList(width) { CellType.EMPTY }
        }
        blocks.forEach { block ->
            listResult[block.y][block.x] = CellType.BLOCK
        }
        listResult[start.y][start.x] = CellType.START
        listResult[exit.y][exit.x] = CellType.EXIT

        val result = listResult.map { it.map(CellType::char) }.joinToString(separator = "\n") { lineList ->
            lineList.joinToString("")
        }
        return result
    }

    private fun initMinPseconds(): MutableMap<Coords, Psecond> =
        freeBlocks.associateWith {
            Psecond.MAX_VALUE
        }.toMutableMap()


    fun getNeighborPseconds(from: CoordsPsecond) =
        from.coords.allNext().filter { next ->
            next.first in freeBlocks
        }.map { nextFreeBlock ->
            CoordsPsecond(nextFreeBlock.first, 1)
        }.toSet()


    fun dijkstra(): DijkstraResult {
        val minPseconds = initMinPseconds()
        val previous = mutableMapOf<Coords, Set<Coords>>()
        val visited = mutableSetOf<Coords>()
        minPseconds[start] = 0
        val heap = PriorityQueue<CoordsPsecond>()
        heap.add(CoordsPsecond(start, 0))

        while (heap.isNotEmpty()) {
            val current = heap.poll()
            val currentCoords = current.coords
            if (currentCoords in visited) continue
            visited.add(currentCoords)
            getNeighborPseconds(current).forEach { neighbor ->
                val neighborCoords = neighbor.coords
                val newPsecond = minPseconds[currentCoords]!! + neighbor.psecond

                if (newPsecond <= minPseconds[neighborCoords]!!) {
                    previous[neighborCoords] = setOf(currentCoords).plus(
                        previous[neighborCoords].takeIf {
                            newPsecond == minPseconds[neighborCoords]
                        } ?: emptySet()
                    )
                    if (newPsecond < minPseconds[neighborCoords]!!) {
                        minPseconds[neighborCoords] = newPsecond
                        heap.add(CoordsPsecond(neighborCoords, newPsecond))
                    }
                }
            }
        }
        return DijkstraResult(minPseconds, previous)

    }


    fun lowestPsecond(): Psecond {
        val result = dijkstra().lowestPsecond(exit)
        return result
    }

    fun calculateNumberofCheatsToSavePseconds(): Map<Psecond, Int> {
        val baseResult = dijkstra()
        val baseTime = baseResult.lowestPsecond(exit)
        val mapCoordsAndTimeToExit = baseResult.getMapCoordsAndTime(baseTime)
        val coordsPsecondsAndCheatList: List<Pair<CoordsPsecond, CoordsPsecond>> =
            coordsPsecondsAndCheat(mapCoordsAndTimeToExit)
        val coordsPsecondsAndCheatWithLowestPsecondList: List<Triple<CoordsPsecond, CoordsPsecond, Psecond>> =
            coordsPsecondsAndCheatWithLowestPsecondList(mapCoordsAndTimeToExit, coordsPsecondsAndCheatList)
        val savingsList = coordsPsecondsAndCheatWithLowestPsecondList.map {
            it.second.psecond - it.third
        }.filter { it > 0 }
        val result = savingsList.groupBy { it }.mapValues { it.value.size }
        return result
    }

    private fun coordsPsecondsAndCheatWithLowestPsecondList(
        mapCoordsAndTimeToExit: Map<Coords, Int>,
        coordsPsecondsAndCheatList: List<Pair<CoordsPsecond, CoordsPsecond>>
    ): List<Triple<CoordsPsecond, CoordsPsecond, Psecond>> {
        return coordsPsecondsAndCheatList.mapNotNull() { pair ->
            val psecond = mapCoordsAndTimeToExit[pair.second.coords]
            psecond?.let { Triple(pair.first, pair.second, psecond) }
        }
    }

    private fun coordsPsecondsAndCheat(mapCoordsAndTimeToExit: Map<Coords, Int>): List<Pair<CoordsPsecond, CoordsPsecond>> {
        val result = mapCoordsAndTimeToExit
            .entries
            .map { CoordsPsecond(it.key, it.value) }
            .flatMap { coordsPsecond ->
                val coords = coordsPsecond.coords
                val next2Coords = coords.allNext()
                    .flatMap { next1 -> next1.first.allNext() }
                    .map { it.first }
                    .minus(coords)
                    .toSet()
                next2Coords.map { CoordsPsecond(it, coordsPsecond.psecond - 2) }.filter { it.psecond > 0 }
                    .map { Pair(coordsPsecond, it) }
            }
        return result
    }

}

