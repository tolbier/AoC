package com.xing.aoc24.day20

data class CoordsPsecond(val coords: Coords = Coords(), val psecond: Psecond = 0) : Comparable<CoordsPsecond> {
    override fun compareTo(other: CoordsPsecond): Int =
        this.psecond.compareTo(other.psecond)


}

