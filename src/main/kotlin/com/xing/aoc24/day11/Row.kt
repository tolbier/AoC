package com.xing.aoc24.day11


typealias Row = List<Long>

fun buildRow(rawRow: String): Row {
    return rawRow.trimIndent().split(" ").map { stone ->
        stone.toLong()
    }
}

fun <T, R> memoize(function: (T) -> R): (T) -> R {
    val cache = mutableMapOf<T, R>()
    return { input: T ->
        cache.getOrPut(input) {
            function(input)
        }
    }
}

val memoizedBlink = memoize(::blink)

data class Request(val num: Long, val times: Int)


fun Row.blinkTimesAndSum(times: Int) =
    this.sumOf { blink(it, times) }

fun blink(num: Long, times: Int): Long =
    memoizedBlink(Request(num, times))

private fun blink(req: Request): Long {
    val next = req.times - 1
    val num = req.num
    if (req.times == 0) return 1
    return when (true) {
        (req.num == 0L) -> blink(1, next)
        (num.isDigitsEven()) ->
            blink(num.headHalf(), next) +
                    blink(num.tailHalf(), next)

        else -> blink(req.num * 2024, next)
    }
}

private fun Long.isDigitsEven() =
    digits.length % 2 == 0

private fun Long.headHalf() = digits.take(halfSize()).toLong()
private fun Long.tailHalf() = digits.drop(halfSize()).toLong()
private fun Long.halfSize() = digits.length / 2
private val Long.digits: String
    get() = this.toString()
