package com.xing.aoc24.day5

typealias Updates = List<Update>

fun Updates.getCorrectUpdates(rules: Rules): Updates {
    return  filter {update-> update.isCorrect(rules)}

}
fun Updates.getWrongUpdates(rules: Rules): Updates {
    return  filterNot {update-> update.isCorrect(rules)}

}
fun Updates.getSumofMiddleCorrectUpdates(rules: Rules): Int {
    val correctUpdates = this.getCorrectUpdates(rules)
    return correctUpdates.map { update-> update.getMiddle() }.sum()
}
fun Updates.getSumofMiddleWrongAndSortedUpdates(rules: Rules): Int {
    val wrongUpdates = this.getWrongUpdates(rules)
    return wrongUpdates.map { update->
        val updateSorted = update.sortByRules(rules)
        updateSorted.getMiddle()
    }.sum()
}

private fun Update.sortByRules(rules: Rules):Update =
    this.sortedWith <Page> (object : Comparator <Page> {
        override fun compare (p0: Page, p1: Page)  =
                rules.compare(p0,p1)
    })

