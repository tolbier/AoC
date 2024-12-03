package com.xing.aoc23.day12

data class Record (
    val condition: Condition,
    val groups: Groups,
    private val regEx: Regex = patternRegex(groups)
){
    fun getArrangements():Long {
        if (condition.isFinal()) return  1
        val options = condition.options()
        val matchedRecords =
            options
                .map{ Record(it,groups,regEx)}
                .filter { it.matches() }
        val result = matchedRecords
                .fold(0L) { acc, record ->
                    acc + record.getArrangements()
                }
        return result
    }

    private fun matches() = regEx.matches(condition)
    fun multiply(repeats: Int)=Record(
        condition.multiply(repeats),
        groups.multiply(repeats)
    )

}

fun patternRegex(groups: Groups): Regex =
   groups.pattern().toRegex()


