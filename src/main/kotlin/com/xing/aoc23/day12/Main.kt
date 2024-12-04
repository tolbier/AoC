package com.xing.aoc23.day12


fun main(){
    Day12().main()
}

class Day12 {
   fun main(){
       val records = getRecords()
//       println(records)

//       val result = records.p1()
//       println(result)
       val resultP2 = records.p2()
       println(resultP2)
   }

    private fun getRecords ():List<Record> {
        val fileName = "/com/xing/aoc23/day12/input.txt"

       return  try {
            val a = Day12::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a?.useLines {  lines ->
                lines.map { line ->
                    val regex = "\\s".toRegex()
                    val tokens = line.split(regex).map {it}
                    if (tokens.size == 2) {
                        val (condition, groupsStr) = tokens
                        val groups = groupsStr.split(",").map { it.toInt() }
                        Record(condition, groups)
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

