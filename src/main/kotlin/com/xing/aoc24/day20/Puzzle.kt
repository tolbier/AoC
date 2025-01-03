package com.xing.aoc24.day20

import java.util.*


data class Puzzle(
    val blocks: List<Coords> = emptyList(),
    val start: Coords,
    val exit: Coords
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
        allCoords.associateWith {
            Psecond.MAX_VALUE
        }.toMutableMap()

    data class DijkstraResult(val pseconds: Map<Coords, Psecond>, val pathMap: Map<Coords, Set<Coords>>)

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

    private fun DijkstraResult.lowestExitPsecond(): Psecond {
        val scores = this.pseconds
        val exitScore = scores.get(exit)!!
        return exitScore
    }

    fun lowestPsecond(): Psecond {
        val result = dijkstra().lowestExitPsecond()
        return result
    }


}
