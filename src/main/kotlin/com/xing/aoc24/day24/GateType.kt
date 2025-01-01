package com.xing.aoc24.day24

enum class GateType {
    AND {
        override fun getOutput(op1: Boolean, op2: Boolean) = op1 and op2
    },
    OR {
        override fun getOutput(op1: Boolean, op2: Boolean) = op1 or op2
    },
    XOR {
        override fun getOutput(op1: Boolean, op2: Boolean) = op1 xor op2
    };

    abstract fun getOutput(op1: Boolean, op2: Boolean): Boolean
}

