package com.xing.aoc24.day17


fun main() {
    Day17().main()
}

class Day17 {
    fun main() {
        val machine = getMachine()
        println(machine)
        println(p1(machine))
        println(p2(machine))
    }

    private fun p1(machine: Machine): String =
        machine.execProgram().output()

    private fun p2(machine: Machine): String {
        val result = machine.decrypt().toString()
        return result
    }
//        machine.execProgram().output()
//    private fun p2(machines: List<Machine>): Long {
//        val extra = 10000000000000L
//        return machines.map { it.copy(prizeX = it.prizeX + extra, prizeY = it.prizeY + extra) }.mapNotNull { it.cost() }
//            .sum()
//    }

    private fun getMachine(): Machine {
        val fileName = "/com/xing/aoc24/day17/input.txt"
        try {
            val reader = Day17::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawMachine = reader!!.readText()
            return Machine(rawMachine)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            return Machine()
        }
    }
}



