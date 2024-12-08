package com.xing.aoc24.day7

import java.math.BigInteger

data class Equation(val expectedResult: Long, val operands: List<Long>)

fun Equation.sumOfRightEquations(possibleOperators: List<Operator>, operators: List<Operator>): BigInteger {
    return if (operators.size == operands.size - 1) {
        // we can calculate the result and compare it with expected
        // we'll return the result if equals expected, otherwise 0
        if (expectedResult.toBigInteger() == calculateResult(operands.map { it.toBigInteger() }, operators)) {
            println(expectedResult)
            expectedResult.toBigInteger()
        } else 0.toBigInteger()

    } else {
        var result = 0.toBigInteger()
        possibleOperators.map { operator ->
            sumOfRightEquations(possibleOperators, operators + operator)
        }.toSet().forEach {
            result += it
        }
        result
    }
}

fun calculateResult(operands: List<BigInteger>, operators: List<Operator>): BigInteger {
    return operands.reduceIndexed { index, acc, operand ->
        operators[index - 1].calculate(acc, operand)
    }


}
