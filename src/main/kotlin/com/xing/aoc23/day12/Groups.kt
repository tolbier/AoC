package com.xing.aoc23.day12


typealias Groups = List<Int>

fun Groups.pattern():String {
    var result = "^[\\.\\?]*"
    this.forEachIndexed() {idx,it->
        if(idx>0) result+=")[\\.\\?]+"
        result+="([#\\?]{$it})"
    }
    result+="[\\.\\?]*\$"
    return result
}