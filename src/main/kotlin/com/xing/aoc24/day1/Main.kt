package com.xing.aoc24.day1

import kotlin.math.abs

fun main(){
    Day1().main()
}
class Day1 {
   fun main(){
       val (list1, list2) = lists()

       println("Part1: " + getPartOneTotal(list1,list2))
       println("Part2: " + getPartTwoTotal(list1,list2))
   }
    private fun getPartOneTotal(list1: List<Int>, list2: List<Int>):Int{
        var total =0
        list1.sorted().zip(list2.sorted()){ num1,num2 ->
            total+=abs(num1-num2)
        }
        return total
    }
    private fun getPartTwoTotal(list1: List<Int>, list2: List<Int>):Int{
        val map = list2.groupingBy { it }.eachCount()

        return list1.fold(0){ acc,it ->
            acc + it * (map[it]?:0)
        }
    }
    private fun lists ():Pair<List<Int>,List<Int>> {
        val fileName = "/com/xing/aoc24/day1/input.txt"
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        try {
            val a = Day1::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a?.useLines {  lines ->
                lines.forEach { line ->
                    val regex = "\\D+".toRegex()
                    val numbers = line.split(regex).map { it.toInt() }
                    if (numbers.size == 2) {
                        val (num1, num2) = numbers
                        list1.add(num1)
                        list2.add(num2)
                    } else {
                        throw RuntimeException("Invalid line format: $line")
                    }
                }
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
        }
        return Pair(list1, list2)
    }
}