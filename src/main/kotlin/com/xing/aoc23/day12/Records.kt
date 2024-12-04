package com.xing.aoc23.day12
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

typealias Records = List<Record>

fun Records.getArrangements(repeats:Int =1 ):Long {
    val records = this
    val result = runBlocking {
        withContext(Dispatchers.Default) {
            records.map { record ->
                async {
                    record.getMultipliedArrangements(repeats)
                }
            }.awaitAll().sum()
        }
    }
    return result
}
suspend fun Record.getMultipliedArrangements(repeats:Int):Long{
    val result= this.multiply(repeats).getArrangements()
    println(result)
    return result
}
fun Records.p1() =getArrangements()
fun Records.p2() =getArrangements(5)