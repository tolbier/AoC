package com.xing.aoc24.day3

import java.io.BufferedReader

fun main(){
    Day3().main()
}
class Day3 {
   fun main(){
       val text = getText()
       println(text)
       val resultP1= p1(text)
       println(resultP1)

       val resultP2= p2(text)
       println(resultP2)
   }

    private fun p1(text: String):Int {
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        val matchResults = regex.findAll(text)
        var result = 0
        for (match in matchResults){
            println(match.value)
            val g0 = match.groups[1]!!.value.toInt()
            val g1 = match.groups[2]!!.value.toInt()
            result=result + g0*g1
        }
        return result
    }
    private fun p2(text: String):Int {
        val regex = "(mul|do|don't)\\(((\\d{1,3}),(\\d{1,3}))?\\)".toRegex()
        val matchResults = regex.findAll(text)
        var result = 0
        var enabled = true
        for (match in matchResults){
            println(match.value)
            val g1 = match.groups[1]!!.value
            when (g1) {
                "mul" ->  if (enabled){
                    val g3 = match.groups[3]!!.value.toInt()
                    val g4 = match.groups[4]!!.value.toInt()
                    result += g3 * g4
                }
                "do" -> enabled=true
                "don't" -> enabled=false
            }

        }
        return result
    }

    private fun getText ():String {
        val fileName = "/com/xing/aoc24/day3/input.txt"

            val reader = Day3::class.java.getResourceAsStream(fileName)!!.bufferedReader()
           val text= reader.use(BufferedReader::readText)
        return text
    }
}

