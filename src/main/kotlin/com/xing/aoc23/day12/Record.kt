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
    fun canBeReduced():Boolean{
        if (condition.isEmpty() ||
            groups.isEmpty() || groups== listOf(1,1) || (groups.size==1 && listOf(1,2).contains(groups[0]))) return false

        val cannotBeReduced = condition.first()=='?' && condition.last()=='?'
        return !cannotBeReduced
    }

    fun reduceDot(): Record {
        var result = condition
        if (result.first() =='.') result=result.drop(1)
        if (result.last() =='.') result=result.dropLast(1)
        return this.copy(condition=result)
    }
    fun reduceHash()=
        this.reduceFirstHash().reduceLastHash()

    private fun reduceFirstHash(): Record {
        if (this.condition.first() !='#') return this
        return Record(condition.drop(1),groups.reduceFirstGroup())
    }

    private fun reduceLastHash(): Record {
        if (this.condition.last() !='#') return this
        return Record(condition.dropLast(1),groups.reduceLastGroup())
    }
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
    if (record.canBeReduced()){
        return reduceAndGetArrangements(record)
    }

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

fun reduceAndGetArrangements(record: Record): Long {
    val recordReduced = record.reduceDot().reduceHash()
    return getArrangements(recordReduced)
}


fun patternRegex(groups: Groups): Regex =
   groups.pattern().toRegex()
fun patternRegexString(groups: Groups): String =
    groups.pattern()


