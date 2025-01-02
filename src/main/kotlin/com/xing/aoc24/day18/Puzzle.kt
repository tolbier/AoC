package com.xing.aoc24.day18

import com.xing.aoc24.day18.CellType.CORRUPTED
import com.xing.aoc24.day18.CellType.EMPTY
import java.util.*

data class Puzzle(
    val allCorrupted: List<Coords> = emptyList(),
    val fallenBytes: Int = 0
) {
    private val corrupted = allCorrupted.take(fallenBytes)
    val height = allCorrupted.maxOfOrNull { it.y }?.plus(1) ?: 0
    val width = allCorrupted.maxOfOrNull { it.x }?.plus(1) ?: 0
    val start = Coords(0, 0)
    val exit = Coords(width - 1, height - 1)
    val freeBlocks: Set<Coords> =
        (0..<height).flatMap { y ->
            (0..<width).map { x ->
                Coords(x, y)
            }
        }.toSet().minus(corrupted)

    constructor(puzzle: Puzzle) : this(puzzle.allCorrupted, puzzle.fallenBytes)
    constructor(rawPuzzle: String, fallenBytes: Int) : this(buildPuzzle(rawPuzzle, fallenBytes))

    companion object {
        private fun buildPuzzle(rawPuzzle: String, fallenBytes: Int): Puzzle {
            val allCorrupted = mutableListOf<Coords>()
            rawPuzzle.trimIndent().split("\n")
                .forEach { line ->
                    val (x, y) = line.split(",").map { it.toInt() }
                    val coords = Coords(x, y)
                    allCorrupted.add(coords)

                }
            return Puzzle(allCorrupted, fallenBytes)
        }
    }

    fun draw(): String {
        val listResult = MutableList(height) {
            MutableList(width) { EMPTY }
        }
        corrupted.forEach { block ->
            listResult[block.y][block.x] = CORRUPTED
        }

        val result = listResult.map { it.map(CellType::char) }.joinToString(separator = "\n") { lineList ->
            lineList.joinToString("")
        }
        return result
    }

    data class DijkstraResult(val scores: Map<Coords, Score>, val pathMap: Map<Coords, Set<Coords>>)

    private fun initScores(): MutableMap<Coords, Score> =
        freeBlocks.associateWith {
            Score.MAX_VALUE
        }.toMutableMap()

    fun getNeighborScores(fromCoords: Coords) =
        fromCoords.allNext().filter { next ->
            next in freeBlocks
        }.map { nextFreeBlock ->
            CoordsScore(nextFreeBlock, 1)
        }.toSet()

    fun dijkstra(): DijkstraResult {
        val scores = initScores()
        val previous = mutableMapOf<Coords, Set<Coords>>()
        val visited = mutableSetOf<Coords>()
        scores[start] = 0L
        val heap = PriorityQueue<CoordsScore>()
        heap.add(CoordsScore(start, 0))

        while (heap.isNotEmpty()) {
            val current = heap.poll()
            val currentCoords = current.coords
            if (currentCoords in visited) continue
            visited.add(currentCoords)
            getNeighborScores(currentCoords).forEach { neighbor ->
                val neighborCoords = neighbor.coords
                val newScore = scores[currentCoords]!! + neighbor.score

                if (newScore <= scores[neighborCoords]!!) {
                    previous[neighborCoords] = setOf(currentCoords).plus(
                        previous[neighborCoords].takeIf {
                            newScore == scores[neighborCoords]
                        } ?: emptySet()
                    )
                    if (newScore < scores[neighborCoords]!!) {
                        scores[neighborCoords] = newScore
                        heap.add(CoordsScore(neighborCoords, newScore))
                    }
                }
            }
        }
        return DijkstraResult(scores, previous)

    }

    private fun DijkstraResult.lowestExitScore(): Score {
        val scores = this.scores
        val exitScore = scores.get(exit)!!
        return exitScore
    }

    fun lowestDijkstraScore(): Score {
        val result = dijkstra().lowestExitScore()
        return result
    }

    fun firstCorruptedByteThatBlockedTheWay(): Coords? {
        allCorrupted.forEachIndexed() { idx, coords ->
            val currentPuzzle = copy(fallenBytes = idx + 1)
            if (currentPuzzle.lowestDijkstraScore() == Score.MAX_VALUE) return coords
        }
        return null
    }
}
