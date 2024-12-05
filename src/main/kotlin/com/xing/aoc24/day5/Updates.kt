package com.xing.aoc24.day5

typealias Updates = List<Update>

fun Updates.getCorrectUpdates(rules: Rules): Updates {
    return  filter {update-> update.isCorrect(rules)}

}

fun Updates.getSumofMiddleCorrectUpdates(rules: Rules): Int {
    val correctUpdates = this.getCorrectUpdates(rules)
    return correctUpdates.map { update-> update.getMiddle() }.sum()
}