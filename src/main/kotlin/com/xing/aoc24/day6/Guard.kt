package com.xing.aoc24.day6

data class Guard(val direction: Direction, val coords: Coords) {
    fun nextCoords(): Coords {
        return coords.goStep(direction)
    }

    fun turn() = Guard(direction.turn(), coords)


    fun move(coords: Coords): Guard = copy(coords = coords)

    fun moveNext(): Guard = move(nextCoords())

    companion object {
        fun fromRawBoard(rawBoard: RawBoard): Guard? {
            val rows = rawBoard.rows()
            rows.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    val guardDirection = Direction.fromCell(cell)
                    if (guardDirection != null) {
                        return Guard(guardDirection, Coords(x, y))
                    }

                }
            }
            return null
        }
    }
}
