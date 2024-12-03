package com.xing.aoc23.day12
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

typealias Records = List<Record>

fun Records.getArrangements(repeats:Int =1 ):Long {
    var count =1
    return sumOf {
        println(count++)
         it.multiply(repeats).getArrangements()
    }
}

fun Records.p1() =getArrangements()
fun Records.p2() =getArrangements(5)