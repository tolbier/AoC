package com.xing.aoc24.day15


data class Puzzle(val map: Map<Coords, Block> = mutableMapOf(), val robot: Robot) {
    constructor(puzzle: Puzzle = Puzzle(mutableMapOf(), Robot())) : this(puzzle.map, puzzle.robot)
    constructor(rawPuzzle: String) : this(buildPuzzle(rawPuzzle))

    companion object {
        private fun buildPuzzle(rawPuzzle: String): Puzzle {
            val puzzleMap = mutableMapOf<Coords, Block>()
            var robotCoords: Coords? = null
            rawPuzzle.trimIndent().split("\n")
                .forEachIndexed { y, line ->
                    line.forEachIndexed { x, c ->
                        val coords = Coords(x, y)
                        if (c == CellType.ROBOT.char) {
                            robotCoords = coords
                        } else {
                            val blockType = BlockType.fromChar(c)
                            blockType?.let {
                                val coordsList = mutableListOf(coords)
                                if (blockType == BlockType.BIG_BOX) {
                                    coordsList.add(coords.copy(x = x + 1))
                                }

                                val block = Block(blockType, coordsList)

                                coordsList.forEach { coords ->
                                    puzzleMap[coords] = block
                                }
                            }

                        }
                    }
                }
            return Puzzle(puzzleMap, Robot(robotCoords!!))
        }
    }

    val height = map.maxOf { it.key.y } + 1
    val width = map.maxOf { it.key.x } + 1

    fun sumAllBoxesGPSCoords(): Long =
        map.values.distinct().filter { it.type in listOf(BlockType.BOX, BlockType.BIG_BOX) }.sumOf {
            val coords = it.coordsList[0]
            coords.gpsCoords()
        }

    fun moveRobot(movement: Movement): Puzzle {
        val nextBlock = getBlock(robot.coords.next(movement))
        val puzzleAfterMoveNext: Puzzle? =
            if (nextBlock.isNotEmpty()) {
                nextBlock!!.move(this, movement)
            } else {
                this
            }

        val puzzleAfterMoveRobot =
            if (puzzleAfterMoveNext.allowsMovement()) {
                puzzleAfterMoveNext!!.copy(robot = robot.moveNext(movement))
            } else {
                this
            }
        return puzzleAfterMoveRobot
    }

    fun draw(): String {
        val listResult = MutableList(height) {
            MutableList(width) { CellType.EMPTY.char }
        }
        map.values.forEach { block ->
            block.coordsList.forEachIndexed { idx, (x, y) ->
                listResult[y][x] = block.type.glyph[idx]
            }

        }
        listResult[robot.coords.y][robot.coords.x] = CellType.ROBOT.char
        return listResult.joinToString(separator = "\n") { lineList ->
            lineList.joinToString("")
        }
    }

    fun getBlock(coords: Coords): Block? = map[coords]
    fun moveBlockFromTo(fromCoordsList: List<Coords>, toCoordsList: List<Coords>): Puzzle {
        val fromBlock = getBlock(fromCoordsList[0])!!

        val newBlock = Block(fromBlock.type, toCoordsList)
        return copy(map = map
            .filterKeys { it !in fromCoordsList }
            .plus(toCoordsList.map { it to newBlock })
        )
    }
}

fun Puzzle?.allowsMovement() = this != null

fun String.wider(): String =
    replace("#", "##")
        .replace("O", "[]")
        .replace(".", "..")
        .replace("@", "@.")
