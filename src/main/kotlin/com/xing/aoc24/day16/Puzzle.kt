package com.xing.aoc24.day16

import com.xing.aoc24.day16.CellType.BLOCK
import com.xing.aoc24.day16.CellType.EMPTY
import com.xing.aoc24.day16.CellType.EXIT
import com.xing.aoc24.day16.CellType.START
import java.util.*

data class Puzzle(
    val blocks: Set<Coords> = emptySet(),
    val start: Reindeer = Reindeer(),
    val exit: Coords = Coords()
) {
    val height = blocks.maxOf { it.y } + 1
    val width = blocks.maxOf { it.x } + 1

    val nonBlocks: Set<Coords> =
        (0..<height).flatMap { y ->
            (0..<width).map { x ->
                Coords(x, y)
            }
        }.toSet().minus(blocks)

    constructor(puzzle: Puzzle = Puzzle()) : this(puzzle.blocks, puzzle.start, puzzle.exit)
    constructor(rawPuzzle: String) : this(buildPuzzle(rawPuzzle))

    companion object {
        private fun buildPuzzle(rawPuzzle: String): Puzzle {
            val blocks = mutableSetOf<Coords>()
            var reindeerCoords = Coords()
            var exitCoords = Coords()
            rawPuzzle.trimIndent().split("\n")
                .forEachIndexed { y, line ->
                    line.forEachIndexed { x, c ->
                        val coords = Coords(x, y)
                        when (CellType.fromChar(c)) {
                            START -> reindeerCoords = coords
                            EXIT -> exitCoords = coords
                            BLOCK -> blocks.add(coords)
                            else -> {}
                        }
                    }
                }
            return Puzzle(blocks, Reindeer(reindeerCoords), exitCoords)
        }
    }

    fun draw(): String {
        val listResult = MutableList(height) {
            MutableList(width) { EMPTY }
        }
        blocks.forEach { block ->
            listResult[block.y][block.x] = BLOCK
        }

        listResult[start.coords.y][start.coords.x] = START
        listResult[exit.y][exit.x] = EXIT
        return listResult.map { it.map(CellType::char) }.joinToString(separator = "\n") { lineList ->
            lineList.joinToString("")
        }
    }

    private fun initScores(): MutableMap<Reindeer, Score> =
        nonBlocks.flatMap { coords ->
            Direction.entries.map { direction ->
                Reindeer(coords, direction) to Score.MAX_VALUE
            }
        }.toMap().toMutableMap()

    fun lowestScore(): Long {
        val dijkstraResult = dijkstra()
        val scoresMap = dijkstraResult.first
        return Direction.entries.minOf { direction ->
            scoresMap[Reindeer(exit, direction)] ?: Score.MAX_VALUE
        }
    }

    fun dijkstra(): Pair<Map<Reindeer, Score>, Map<Reindeer, Reindeer>> {
        val scores = initScores()
        val previous = mutableMapOf<Reindeer, Reindeer>()
        val visited = mutableSetOf<Reindeer>()
        scores[start] = 0L
        val heap = PriorityQueue<ReindeerScore>()
        heap.add(ReindeerScore(start, 0))

        while (heap.isNotEmpty()) {
            val current = heap.poll()
            val currentReindeer = current.reindeer
            if (currentReindeer in visited) continue
            visited.add(currentReindeer)
            getNeighborScores(currentReindeer).forEach { neighborScore ->
                val neighborReindeer = neighborScore.reindeer
                val newScore = scores[currentReindeer]!! + neighborScore.score
                if (newScore < scores[neighborReindeer]!!) {
                    scores[neighborReindeer] = newScore
                    previous[neighborReindeer] = currentReindeer
                    heap.add(ReindeerScore(neighborReindeer, newScore))
                }
            }
        }
        return Pair(scores, previous)
    }

    fun getNeighborScores(fromReindeer: Reindeer): Set<ReindeerScore> {
        val result = mutableSetOf<ReindeerScore>()
        if (fromReindeer.coords in nonBlocks) {
            result.add(ReindeerScore(fromReindeer.copy(direction = fromReindeer.direction.clockwise()), 1000))
            result.add(ReindeerScore(fromReindeer.copy(direction = fromReindeer.direction.counterclockwise()), 1000))
        }
        val next = fromReindeer.coords.next(fromReindeer.direction)
        if (next in nonBlocks) {
            result.add(ReindeerScore(fromReindeer.copy(coords = next), 1))
        }
        return result
    }
}


