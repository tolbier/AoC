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

fun Groups.reduceFirstGroup(): Groups {
    val first =this.first()-1
    val rest = this.drop(1)
    if (first==0) return rest
    return listOf( first) +  rest
}

fun Groups.reduceLastGroup(): Groups {
    val last =this.last()-1
    val rest = this.dropLast(1)
    if (last==0) return rest
    return rest + listOf(last)
}