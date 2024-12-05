package com.xing.aoc24.day5

typealias Rules = Map<Int,List<Int>>

fun Rules.check(a:Int, b:Int):Boolean {
    val result1 = this[a]?.contains(b)?:true
    val result2 = this[b]?.contains(a)?:false

    return result1 && !result2
}
