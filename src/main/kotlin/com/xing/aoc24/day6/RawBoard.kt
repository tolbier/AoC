package com.xing.aoc24.day6

typealias RawBoard = String

fun RawBoard.rows() = this.trimIndent().split("\n")