package com.xing.aoc24.day2

import kotlin.math.abs

fun main(){
    Day2().main()
}
class Day2 {
   fun main(){
       val reports = getReports()
       println(reports)
       val result = reports.safeReports(::isSafeReportFunctionalMethod)
       println(result)
       val resultDampener = reports.safeReports(::isSafeDampenerReportFunctionalMethod)
       println(resultDampener)

   }
   private fun List<List<Int>>.safeReports(method: (Boolean, List<Int>) -> Boolean)=
       count {  report-> val isSafe=   isSafeReport(report,method)
//           if (isSafe) println(report)
           isSafe
       }

    private fun isSafeReport(report: List<Int>, method: (Boolean, List<Int>) -> Boolean)=
        method(
            report[0] > report[1],
            report
        )

    private fun isSafeReportFunctionalMethod(isIncreasing: Boolean, report: List<Int>) :Boolean {
        if (report.size<2) return true
        val first = report.first()
        val tail = report.drop(1)
        val second = tail.first()
        val isSafe = isIncreasingOrDecreasing(isIncreasing, first, second) && abs(first - second) <= 3
        if (!isSafe) return false
        return isSafeReportFunctionalMethod(isIncreasing, tail)
    }

    private fun isSafeDampenerReportFunctionalMethod(isIncreasing: Boolean, report: List<Int>) =
         isSafeReportFunctionalMethod(isIncreasing, report) ||
                report.indices.any{ idx->
                   isSafeReport( report.remove(idx), ::isSafeReportFunctionalMethod)
                }

    private fun List<Int>.remove(i: Int) =
        this.filterIndexed { index, _ -> index != i }

    private fun isIncreasingOrDecreasing(isIncreasing: Boolean, i: Int, j: Int)=
        if (isIncreasing){
             i>j
        }else{
             i<j
        }

    private fun getReports ():List<List<Int>> {
        val fileName = "/com/xing/aoc24/day2/input.txt"

       return  try {
            val a = Day2::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a?.useLines {  lines ->
                lines.map { line ->
                    val regex = "\\D+".toRegex()
                    val numbers = line.split(regex).map { it.toInt() }
                    if (numbers.size >1) {
                        numbers
                    } else {
                        throw RuntimeException("Invalid line format: $line")
                    }
                }.toList()
            }?: emptyList()
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
           emptyList()
        }
    }
}

