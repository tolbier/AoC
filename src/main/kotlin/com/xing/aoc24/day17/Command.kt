package com.xing.aoc24.day17

import kotlin.reflect.KFunction2

enum class Command(val opcode: Byte) {
    /*
        The **adv** instruction (opcode 0) performs division. The numerator is the value in the A register. The denominator is
        found
        by raising 2 to the power of the instruction's combo operand. (So, an operand of 2 would divide A by 4 (2^2); an operand
        of 5 would divide A by 2^B.) The result of the division operation is truncated to an integer and then written to the A
        register.
    */
    ADV(0) {
        override fun exec(machine: Machine) =
            dv(machine, Machine::setA).next()
    },

    /*
        The **bxl** instruction (opcode 1) calculates the bitwise XOR of register B and the instruction's literal operand, then
        stores the result in register B.
    */
    BXL(1) {
        override fun exec(machine: Machine): Machine {
            val res = machine.regB xor machine.literal().toLong()
            return machine.setB(res).next()
        }
    },

    /*
        The **bst** instruction (opcode 2) calculates the value of its combo operand modulo 8 (thereby keeping only its lowest 3
        bits), then writes that value to the B register.

    */
    BST(2) {
        override fun exec(machine: Machine): Machine {
            val res = machine.combo() and 7
            return machine.setB(res).next()
        }
    },

    /*
        The **jnz** instruction (opcode 3) does nothing if the A register is 0. However, if the A register is not zero, it jumps
        by
        setting the instruction pointer to the value of its literal operand; if this instruction jumps, the instruction pointer
        is not increased by 2 after this instruction.

    */
    JNZ(3) {
        override fun exec(machine: Machine) = machine.jump()
    },

    /*
        The **bxc** instruction (opcode 4) calculates the bitwise XOR of register B and register C, then stores the result in
        register B. (For legacy reasons, this instruction reads an operand but ignores it.)

    */
    BXC(4) {
        override fun exec(machine: Machine) =
            machine.setB(machine.regB xor machine.regC).next()
    },

    /*
        The **out** instruction (opcode 5) calculates the value of its combo operand modulo 8, then outputs that value. (If a
        program outputs multiple values, they are separated by commas.)

    */
    OUT(5) {
        override fun exec(machine: Machine) = machine.outputComboMod8().next()
    },

    /*
        The **bdv** instruction (opcode 6) works exactly like the adv instruction except that the result is stored in the B
        register. (The numerator is still read from the A register.)

    */
    BDV(6) {
        override fun exec(machine: Machine) = dv(machine, Machine::setB).next()
    },

    /*
        The **cdv** instruction (opcode 7) works exactly like the adv instruction except that the result is stored in the C
        register. (The numerator is still read from the A register.)
    */
    CDV(7) {
        override fun exec(machine: Machine) = dv(machine, Machine::setC).next()
    };

    abstract fun exec(machine: Machine): Machine

    internal fun dv(machine: Machine, f: KFunction2<Machine, Long, Machine>): Machine {
        val num = machine.regA
        val den = 0b1 shl machine.combo().toInt()
        val res: Long = num / den
        return f(machine, res)
    }

    companion object {
        fun fromByte(byte: Byte): Command {
            return Command.entries.find { it.opcode == byte }!!
        }
    }
}