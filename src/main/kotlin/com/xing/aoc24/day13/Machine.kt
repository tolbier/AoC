package com.xing.aoc24.day13

data class Machine(val aX: Int, val aY: Int, val bX: Int, val bY: Int, val prizeX: Int, val prizeY: Int) {
    fun cost(): Int? {
        val b1 = prizeY * aX - aY * prizeX
        val b2 = aX * bY - aY * bX
        if (b1 % b2 != 0) return null
        val b = b1 / b2
        val a1 = prizeX - b * bX
        val a2 = aX
        if (a1 % a2 != 0) return null
        val a = a1 / a2
        return a * 3 + b
    }
}
