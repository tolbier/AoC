package com.xing.aoc24.day25

typealias Floor = List<Schematic>

fun buildFloor(rawFile: String): Floor {
    val rawSchematics = rawFile.split("\n\n")
    return rawSchematics.map { Schematic.build(it) }
}


fun Floor.getFitsNumber(): Int {
    val (locks, keys) = this.partition { it is Schematic.Lock }
    val result = locks
        .map { it as Schematic.Lock }
        .sumOf { lock ->
            keys.map { it as Schematic.Key }
                .count { key ->
                    key.fits(lock)
                }
        }
    return result
}