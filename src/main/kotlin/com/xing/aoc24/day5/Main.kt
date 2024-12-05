package com.xing.aoc24.day5

fun main(){
    Day5().main()
}
class Day5 {
   fun main(){
       val (rules, updates) = getRulesAndUpdates()
       println(rules)
       println(updates)
       val resultP1 = p1(updates,rules)
        println(resultP1)
       val resultP2 = p2(updates,rules)
       println(resultP2)
   }

    private fun p1(updates: Updates, rules: Rules): Int {
        val sumOfMiddleCorrectUpdates= updates.getSumofMiddleCorrectUpdates(rules)
        return sumOfMiddleCorrectUpdates
    }
    private fun p2(updates: Updates, rules: Rules): Int {
        val sumOfMiddleWrongAndSortedUpdates= updates.getSumofMiddleWrongAndSortedUpdates(rules)
        return sumOfMiddleWrongAndSortedUpdates

    }

    private fun getRulesAndUpdates ():Pair<Rules ,Updates> {
        val fileName = "/com/xing/aoc24/day5/input.txt"

       return  try {
            val a = Day5::class.java.getResourceAsStream(fileName)?.bufferedReader()
            var rules= mutableListOf<Pair<Int,Int>>()
           var updates= mutableListOf<List<Int>>()
           var readingRules = true
            a?.useLines {  lines ->
                lines.forEach {  line ->
                    if  (readingRules) {
                        val regex = "(\\d+)\\|(\\d+)".toRegex()
                        val match = regex.find(line)
                        if (match==null) {
                            readingRules = false
                        }else{
                            val first = match.groups[1]!!.value.toInt()
                            val second = match.groups[2]!!.value.toInt()
                            rules.add(Pair(first,second))
                        }
                    }else{
                        val update = line.split(",").map{it.toInt()}
                        updates.add(update)
                    }
                }
            }
            Pair( rules.groupBy({it.first},{it.second}),updates)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
           Pair(emptyMap(),emptyList())
        }
    }
}





