package com.xing.aoc24.day7

import java.math.BigInteger

enum class Operator() {
    MULTIPLY {
        override fun calculate(n1: BigInteger, n2: BigInteger): BigInteger = n1 * n2
    },
    SUM {
        override fun calculate(n1: BigInteger, n2: BigInteger): BigInteger = n1 + n2
    },
    CONCAT {
        override fun calculate(n1: BigInteger, n2: BigInteger): BigInteger =
            (n1.toString() + n2.toString()).toBigInteger()
    };

    abstract fun calculate(n1: BigInteger, n2: BigInteger): BigInteger


}