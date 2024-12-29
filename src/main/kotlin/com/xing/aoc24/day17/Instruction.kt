package com.xing.aoc24.day17

data class Instruction(val command: Command, val operand: Byte) {
    fun exec(machine: Machine) = command.exec(machine)
}
