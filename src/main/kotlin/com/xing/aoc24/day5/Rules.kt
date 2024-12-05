package com.xing.aoc24.day5

typealias Rules = Map<Page,List<Page>>

fun Rules.check(a:Page, b:Page):Boolean {
    val result1 = this[a]?.contains(b)?:true
    val result2 = this[b]?.contains(a)?:false

    return result1 && !result2
}
fun Rules.compare(a: Page,b:Page)=
    if (a == b) {
        0
    }else if (check(a,b)) {
        -1
    } else
        1