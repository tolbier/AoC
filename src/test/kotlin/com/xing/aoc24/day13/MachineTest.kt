package com.xing.aoc24.day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MachineTest {
    val rawMachines: String =
        """
Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279
        """

    val machines = buildMachines(rawMachines)

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun cost() {

        assertEquals(280, machines[0].cost())
        assertEquals(null, machines[1].cost())
        assertEquals(200, machines[2].cost())
        assertEquals(null, machines[3].cost())
    }
}