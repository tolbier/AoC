package com.xing.aoc23.day12

typealias Condition = String

fun Condition.options():List<Condition> {
    val regex = Regex("\\?")
    val positions =regex.findAll(this).map { it.range.first }.toList()

    return positions.map{ position->
        this.mapIndexed { index, c ->
            when(c){
                '.','#'-> c
                else ->
                    if (index<position) '.'
                    else if(index==position)'#'
                    else '?'
            }
        }.joinToString (separator = "")
    }
}