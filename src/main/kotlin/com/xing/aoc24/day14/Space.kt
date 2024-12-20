package com.xing.aoc24.day14

data class Space(val width: Int = 0, val height: Int = 0, val robots: List<Robot> = emptyList()) {
    fun safetyFactor() =
        robots
            .map { it.quadrant(width, height) }
            .filterNot { it == Coords.Quadrant.UNDETERMINED }
            .groupBy { it }
            .values
            .map { it.size.toLong() }
            .fold(1L) { acc, it -> acc * it }

    fun predict(seconds: Int) =
        copy(robots = robots.map { it.predict(seconds, width, height) })

}

