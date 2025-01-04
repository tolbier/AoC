package com.xing.aoc24.day20


data class Puzzle(
    val blockCells: List<Coords> = emptyList(),
    val start: Coords = Coords(0, 0),
    val exit: Coords = Coords(0, 0)
) {
    val height = blockCells.maxOfOrNull { it.y }?.plus(1) ?: 0
    val width = blockCells.maxOfOrNull { it.x }?.plus(1) ?: 0
    val freeCells: Set<Coords> =
        (0..<height).flatMap { y ->
            (0..<width).map { x ->
                Coords(x, y)
            }
        }.toSet().minus(blockCells)
    val pSecondsMap = getPsecondsMap()
    fun inPuzzle(coords: Coords): Boolean =
        coords.x in (0..<width) &&
                coords.y in (0..<height)


    private fun getPsecondsMap(): Map<Coords, Psecond> {
        val result = mutableMapOf<Coords, Psecond>()
        var last: Coords? = null
        var current: Coords = start
        var psecond = 0
        do {
            result[current] = psecond++
            val next = current.allNext().firstOrNull { next -> next in freeCells && next != last }
            last = current
            current = next ?: current

        } while (next != null)

        return result
    }

    constructor(puzzle: Puzzle) : this(puzzle.blockCells, puzzle.start, puzzle.exit)
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
        blockCells.forEach { block ->
            listResult[block.y][block.x] = CellType.BLOCK
        }
        listResult[start.y][start.x] = CellType.START
        listResult[exit.y][exit.x] = CellType.EXIT

        val result = listResult.map { it.map(CellType::char) }.joinToString(separator = "\n") { lineList ->
            lineList.joinToString("")
        }
        return result
    }


    fun getPsecondsFromStartToExit(): Int =
        pSecondsMap[exit]!!

    fun getPseconds2ExitMap(): Map<Coords, Psecond> {
        val psecondsFromStartToExit = getPsecondsFromStartToExit()
        val result = pSecondsMap.map { (coords, pSecond) ->
            coords to psecondsFromStartToExit - pSecond
        }.toMap()
        return result
    }

    fun getCheats(): Set<Cheat> {
        val pseconds2Exit = getPseconds2ExitMap()
        val result = pseconds2Exit.flatMap { (coords, _) ->
            getNext2StepsFrom(coords).map { to -> Cheat(coords, to) }
        }.toSet()
        return result
    }

    fun getNext2StepsFrom(coords: Coords): Set<Coords> {
        return Direction.entries.flatMap { firstDir ->
            Direction.entries.map { secondDir ->
                coords.next(firstDir).next(secondDir)
            }
        }.filter { it != coords }.filter { inPuzzle(it) }.toSet()
    }

    fun getCheatSavings(): Set<CheatSaving> {
        val pseconds2Exit = getPseconds2ExitMap()
        val cheats = getCheats()
        val result = cheats.mapNotNull { cheat ->
            val fromTime2Exit = pseconds2Exit[cheat.from]!!
            val toTime2Exit = pseconds2Exit[cheat.to]
            toTime2Exit?.let {
                val saving = fromTime2Exit - (cheat.pseconds() + toTime2Exit)
                CheatSaving(cheat, saving)
            }
        }.filter { it.saving > 0 }.toSet()
        return result
    }

    fun getSavings(): List<Int> =
        getCheatSavings().map { (_, saving) ->
            saving
        }

    fun getSavingsCount(geThanPseconds: Psecond = 0): Map<Psecond, Int> {
        val savings = getSavings()
        val result = savings.filter { it >= geThanPseconds }.groupBy { it as Psecond }.mapValues { it.value.size }
        return result
    }


}

