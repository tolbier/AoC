package com.xing.aoc24.day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MachineTest {
    val rawMachines: List<String> = listOf(
//        * If register C contains 9, the program 2,6 would set register B to 1.
//        * If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
//        * If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
//        * If register B contains 29, the program 1,7 would set register B to 26.
//        * If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
        """
           Register A: 0
           Register B: 0
           Register C: 9

           Program: 2,6  
        """,
        """
           Register A: 10
           Register B: 0
           Register C: 0

           Program: 5,0,5,1,5,4 
        """,
        """
           Register A: 2024
           Register B: 0
           Register C: 0

           Program: 0,1,5,4,3,0
        """,
        """
           Register A: 0
           Register B: 29
           Register C: 0

           Program: 1,7
        """,
        """
           Register A: 2024
           Register B: 0
           Register C: 43690

           Program: 4,0
        """,
        """
           Register A: 729
           Register B: 0
           Register C: 0

           Program: 0,1,5,4,3,0
        """,
        """
            Register A: 117440
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
        """
    )
    val machines = rawMachines.map { Machine(it.trimIndent()) }
    val finalDrawMachines: List<String> = listOf(
//        * If register C contains 9, the program 2,6 would set register B to 1.
//        * If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
//        * If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
//        * If register B contains 29, the program 1,7 would set register B to 26.
//        * If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
        """
           Register A: 0
           Register B: 1
           Register C: 9

           Program: 2,6
           Output: 
        """,
        """
           Register A: 10
           Register B: 0
           Register C: 0

           Program: 5,0,5,1,5,4
           Output: 0,1,2 
        """,
        """
           Register A: 0
           Register B: 0
           Register C: 0

           Program: 0,1,5,4,3,0
           Output: 4,2,5,6,7,7,7,7,3,1,0 
        """,
        """
           Register A: 0
           Register B: 26
           Register C: 0

           Program: 1,7
           Output: 
        """,
        """
           Register A: 2024
           Register B: 43690
           Register C: 43690

           Program: 4,0
           Output:  
        """,
        """
           Register A: 0
           Register B: 0
           Register C: 0

           Program: 0,1,5,4,3,0
           Output: 4,6,3,5,6,3,5,2,1,0 
        """,
        """
            Register A: 0
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
            Output: 0,3,5,4,3,0
        """
    ).map { it.trimIndent().trim() }

    @Test
    fun draw() {
        machines.zip(finalDrawMachines) { machine, finalDrawMachine ->
            assertEquals(finalDrawMachine, machine.execProgram().draw())
        }
    }

}