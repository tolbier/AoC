package com.xing.aoc23.day12
val memoizedGetArrangements = memoize(::getArrangements)
val memoizedToRegEx = memoize(::matches)
data class Record (
    val condition: Condition,
    val groups: Groups,
    val regExStr: String = patternRegexString(groups)
){
    fun getArrangements():Long = memoizedGetArrangements(this)

    fun matches() =  memoizedToRegEx(regExStr).matches(condition)
    fun multiply(repeats: Int)=Record(
        condition.multiply(repeats),
        groups.multiply(repeats)
    )

}
fun matches(regExStr: String) =
    regExStr.toRegex()

fun <T, R> memoize(function: (T) -> R): (T) -> R {
    val cache = mutableMapOf<T, R>()
    return { input: T ->
        cache.getOrPut(input) { function(input) }
    }
}
fun getArrangements(record: Record):Long {
    if (record.condition.isFinal()) return  1
    val options = record.condition.options()
    val matchedRecords =
        options
            .map{ Record(it,record.groups,record.regExStr)}
            .filter { it.matches() }
    val result = matchedRecords
        .fold(0L) { acc, record ->
            acc + getArrangements(record)
        }
    return result
}
fun patternRegex(groups: Groups): Regex =
   groups.pattern().toRegex()
fun patternRegexString(groups: Groups): String =
    groups.pattern()


