package com.xing.aoc24.day6

typealias Row = String

fun Row.replaceAt(offset: Int, cell: Cell): Row =
    this.take(offset) + cell + drop(offset + 1)
