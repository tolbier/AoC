package com.xing.aoc24.day17


data class Machine(
    val regA: Long,
    val regB: Long,
    val regC: Long,
    val program: Program,
    val pointer: Int = 0,
    val output: List<Long> = emptyList()

) {

    fun instruction() = program[pointer]
    fun next() = this.copy(pointer = pointer + 1)
    fun jump(n: Int = this.literal().toInt()) = if (regA > 0) this.copy(pointer = n / 2) else this.next()
    fun setA(n: Long) = this.copy(regA = n)
    fun setB(n: Long) = this.copy(regB = n)
    fun setC(n: Long) = this.copy(regC = n)
    fun combo(): Long {
        val operand = instruction().operand
        return when (operand.toInt()) {
            0, 1, 2, 3 -> operand.toLong()
            4 -> regA
            5 -> regB
            6 -> regC
            else -> 0
        }
    }

    fun literal() = instruction().operand

    fun outputComboMod8() =
        this.copy(output = output.plus(combo() and 7))

    fun execProgram(): Machine {
        var current = this
        while (current.programNotFinished()) {
            current = current.instruction().exec(current)
        }
        return current
    }

    fun output() = output.joinToString(",")
    fun program() = program.flatMap { listOf(it.command.opcode, it.operand) }.joinToString(",")

    private fun programNotFinished() = pointer < program.size
    fun draw() =
        """
                       Register A: $regA
                       Register B: $regB
                       Register C: $regC

                       Program: ${program()}
                       Output: ${output()}
        """.trimIndent().trim()


    constructor(machine: Machine = Machine()) : this(machine.regA, machine.regB, machine.regC, machine.program)
    constructor(rawPuzzle: String) : this(buildMachine(rawPuzzle))

    companion object {

        fun buildMachine(rawMachine: String): Machine {
            val regex =
                "Register\\sA:\\s(\\d+)\\nRegister\\sB:\\s(\\d+)\\nRegister\\sC:\\s(\\d+)\\n\\nProgram:\\s((\\d+)(,(\\d+))*)".toRegex()
            val match = regex.find(rawMachine)!!

            val bytes = match.groups[4]!!.value.split(",").map { it.toByte() }
            val program = bytes.chunked(2).map {
                Instruction(Command.fromByte(it[0]), it[1])
            }
            val regs = (1..3).map { match.groups[it]!!.value.toLong() }
            return Machine(
                regA = regs[0],
                regB = regs[1],
                regC = regs[2],
                program = program
            )
        }
    }
}
