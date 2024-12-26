package com.xing.aoc24.day15


enum class BlockType(val glyph: String) {
    WALL("#") {
        override fun move(puzzle: Puzzle, movement: Movement, coordsList: List<Coords>): Puzzle? = null
    },
    BOX("O") {
        override fun move(puzzle: Puzzle, movement: Movement, coordsList: List<Coords>): Puzzle? {
            val blockToMove = puzzle.getBlock(coordsList[0].next(movement))
            val moveRestPuzzle = if (blockToMove.isNotEmpty()) {
                blockToMove!!.move(puzzle, movement)
            } else {
                puzzle
            }
            val result = if (moveRestPuzzle.allowsMovement()) {
                moveRestPuzzle!!.moveBlockFromTo(coordsList, coordsList.map { it.next(movement) })
            } else {
                null
            }
            return result
        }
    },
    BIG_BOX("[]") {
        override fun move(puzzle: Puzzle, movement: Movement, coordsList: List<Coords>): Puzzle? {
            val moveRestPuzzle = if (movement.isVertical()) {
                coordsList.fold(puzzle) { accPuzzle: Puzzle?, coords ->
                    accPuzzle?.let {
                        val blockToMove = accPuzzle.getBlock(coords.next(movement))
                        val moveRestPuzzlePartial = if (blockToMove.isNotEmpty()) {
                            blockToMove!!.move(accPuzzle, movement)
                        } else {
                            accPuzzle
                        }
                        moveRestPuzzlePartial
                    }
                }
            } else {
                val blockToMove = if (movement == Movement.EAST) {
                    puzzle.getBlock(coordsList[0].next(movement).next(movement))
                } else {
                    puzzle.getBlock(coordsList[0].next(movement))
                }
                if (blockToMove.isNotEmpty()) {
                    blockToMove!!.move(puzzle, movement)
                } else {
                    puzzle
                }
            }
            return if (moveRestPuzzle.allowsMovement()) {
                moveRestPuzzle!!.moveBlockFromTo(coordsList, coordsList.map { it.next(movement) })
            } else {
                null
            }
        }

    };

    abstract fun move(puzzle: Puzzle, movement: Movement, coordsList: List<Coords>): Puzzle?

    companion object {
        fun fromChar(char: Char): BlockType? {
            return BlockType.entries.firstOrNull { it.glyph.first() == char }
        }
    }
}