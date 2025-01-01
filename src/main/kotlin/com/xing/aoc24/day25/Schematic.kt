package com.xing.aoc24.day25

sealed class Schematic(open val pins: List<Byte>) {
    data class Lock(override val pins: List<Byte> = emptyList()) : Schematic(pins) {
        fun fits(key: Key): Boolean {
            val result = pins.zip(key.pins).map { (a, b) -> a + b }.all { it <= 5 }
            return result
        }
    }

    data class Key(override val pins: List<Byte> = emptyList()) : Schematic(pins) {
        fun fits(lock: Lock): Boolean = lock.fits(this)
    }

    fun draw() = this::class.simpleName!!.first() + ":" + pins.joinToString(",")

    companion object {
        fun build(rawSchematic: String): Schematic {
            val lines = rawSchematic.split("\n")
            val pinLines = lines.drop(1).dropLast(1)
            return build(pinLines) {
                if (lines.first() == "#####") Lock(it) else Key(it)
            }
        }

        private fun <T : Schematic> build(pinLines: List<String>, f: (List<Byte>) -> T): T {
            val pinsList = MutableList<Byte>(5) { 0 }
            pinLines.forEach { line ->
                line.forEachIndexed { x, char ->
                    if (char == '#') {
                        pinsList[x]++
                    }
                }
            }
            return f(pinsList)
        }
    }
}