package com.xing.aoc23.day12


typealias Groups = List<Int>

fun Groups.multiply(repeats: Int): Groups =
    if (repeats==0) emptyList() else  this + multiply(repeats-1)

fun Groups.pattern():String {
    var result = "^[\\.\\?]*"
    this.forEachIndexed() {idx,it->
        if(idx>0) result+="[\\.\\?]+"
        result+="([#\\?]{$it})"
    }
    result+="[\\.\\?]*\$"
    return result
}

