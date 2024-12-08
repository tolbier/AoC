package com.xing.aoc24.day7

import java.math.BigInteger

fun main() {
    Day7().main()
}

class Day7 {
    fun main() {
        val equations = getEquations()
        println(equations)
//        val resultP1 = p1(equations)
//        println(resultP1)
        val resultP2 = p2(equations)
        println(resultP2)
    }

    private fun p1(equations: List<Equation>): BigInteger {
//        val sumOfMiddleCorrectUpdates = updates.getSumofMiddleCorrectUpdates(rules)
//        return sumOfMiddleCorrectUpdates
        return equations.sumOf { equation ->
            equation.sumOfRightEquations(
                listOf(Operator.SUM, Operator.MULTIPLY),
                emptyList()
            )
        }
    }

    private fun p2(equations: List<Equation>): BigInteger {
//        val sumOfMiddleCorrectUpdates = updates.getSumofMiddleCorrectUpdates(rules)
//        return sumOfMiddleCorrectUpdates
        return equations.sumOf { equation ->
            equation.sumOfRightEquations(
                listOf(Operator.SUM, Operator.MULTIPLY, Operator.CONCAT),
                emptyList()
            )
        }
    }
//
//    private fun p2(updates: Updates, rules: Rules): Int {
//        val sumOfMiddleWrongAndSortedUpdates = updates.getSumofMiddleWrongAndSortedUpdates(rules)
//        return sumOfMiddleWrongAndSortedUpdates
//
//    }

    private fun getEquations(): List<Equation> {
        val fileName = "/com/xing/aoc24/day7/input.txt"

        return try {
            val a = Day7::class.java.getResourceAsStream(fileName)?.bufferedReader()
            a!!.useLines { lines ->
                val equations = lines.map { line ->
                    val regex = "(\\d+):\\s((\\s?(\\d+))+)".toRegex()
                    val match = regex.find(line)
                    val result = match!!.groups[1]!!.value.toLong()
                    val operands = match!!.groups[2]!!.value.split(" ").map { it.toLong() }
                    Equation(result, operands)
                }.toList()
                equations
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            emptyList()
        }
    }
}





