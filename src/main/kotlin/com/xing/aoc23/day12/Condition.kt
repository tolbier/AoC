package com.xing.aoc23.day12

typealias Condition = String
val regex = Regex("\\?")
fun Condition.options():List<Condition> {

    var positions =regex.findAll(this).map { it.range.first }.toList() + length


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

fun Condition.isFinal()= this.all{it != '?'}

fun Condition.multiply(repeats: Int): Condition =  List(repeats){this}.joinToString(separator = "?")
