package com.xing.aoc24.day17


data class Machine(
    val regA: Long,
    val regB: Long,
    val regC: Long,
    val program: Program,
    val pointer: Int = 0,
    val output: List<Byte> = emptyList(),
    val noJump: Boolean = false
) {

    fun instruction() = program[pointer]
    fun next() = this.copy(pointer = pointer + 1)
    fun jump(n: Int = this.literal().toInt()) = if (regA > 0 && !noJump) this.copy(pointer = n / 2) else this.next()
    fun setA(n: Long) = this.copy(regA = n)
    fun setB(n: Long) = this.copy(regB = n)
    fun setC(n: Long) = this.copy(regC = n)
    fun noJump() = this.copy(noJump = true)
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
        this.copy(output = output.plus((combo() and 7).toByte()))

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

    fun firstOutput() =
        output.first()


    fun decrypt(): List<Long> {
        val decryptResult = (0b0L..0b111111111L).flatMap {
            setA(it).noJump().decryptStep(it, listBytesFromTribit(it))
        }
        val result = decryptResult.map { bytes ->
            val res = bytes.fold(0L) { acc, byte ->
                (acc shl 3) or byte.toLong()
            }
            res

        }.sorted()
        return result
    }

    private fun listBytesFromTribit(it: Long) = listOf(
        it shr 6,
        (it shr 3) and 7,
        it and 7
    ).map { it.toByte() }


    private fun decryptStep(previousRegA: Long, prevCodes: List<Byte>, outputPosition: Int = 0): List<List<Byte>> {
        if (outputPosition == program.size * 2) {
            return if (previousRegA == 0L) {
                listOf(prevCodes)
            } else emptyList()

        }

        val currentRegsA = (0..7).map { it.toByte() }.map { code ->
            code to ((code.toLong() shl 9) or (previousRegA))
        }
        val codesToMachines = currentRegsA.map { (code, regA) ->
            code to setA(regA).execProgram()
        }

        val filteredCodesToMachines = codesToMachines.filter { (_, machine) ->
            machine.firstOutput() == getProgramCodeByPosition(outputPosition)
        }

        val result = filteredCodesToMachines.fold(emptyList<List<Byte>>()) { acc, (code, machine) ->
            acc.plus(decryptStep(machine.regA, listOf(code).plus(prevCodes), outputPosition + 1))
        }
        return result
    }

    private fun getProgramCodeByPosition(codePosition: Int): Byte {
        val instruction = program[codePosition / 2]
        return if (codePosition % 2 == 0) {
            instruction.command.opcode
        } else {
            instruction.operand
        }
    }

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
