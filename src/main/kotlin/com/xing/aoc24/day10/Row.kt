package com.xing.aoc24.day10

typealias Row = List<Cell?>

fun Row.getCell(x: Int): Cell? =
    this.getOrNull(x)