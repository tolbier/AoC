package com.xing.aoc24.day5

typealias Update = List<Page>

fun Update.isCorrect(rules: Rules): Boolean {
    forEachIndexed { idx, page ->
        val rest = this.pagesAfter(idx)
        val isOk= rest.all{page2-> rules.check(page,page2)}
        if (!isOk) return false
    }
    return true

}

private fun Update.pagesAfter(idx: Int): Update =
    this.drop(idx+1)

fun Update.getMiddle():Page = get((this.size-1)/2)
