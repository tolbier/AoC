package com.xing.aoc24.day21

import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class KeyPadTest {

    @Test
    fun translateToNumericKeypad() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        assertEquals(
            setOf("<A^A>^^AvvvA", "<A^A^>^AvvvA", "<A^A^^>AvvvA"),
            numericKeyPad.translate("029A")
        )
    }

    @Test
    fun translateToDirectionalKeypad() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        val directionalKeyPad = KeyPad.DirectionalKeyPad(numericKeyPad)
        val list = directionalKeyPad.translate("029A").sorted()
        assertContains(
            list,
            "v<<A>>^A<A>AvA<^AA>A<vAAA>^A"
        )
    }

    @Test
    fun translateToDirectionalKeypad2() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        val directionalKeyPad1 = KeyPad.DirectionalKeyPad(numericKeyPad)
        val directionalKeyPad2 = KeyPad.DirectionalKeyPad(directionalKeyPad1)
        val testPairs: List<Pair<String, String>> = listOf(
            Pair("029A", "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"),
            Pair("980A", "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A"),
            Pair("179A", "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
            Pair("456A", "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A"),
            Pair("379A", "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
        )
        testPairs.forEach { (input, result) ->
            val list = directionalKeyPad2.translate(input).sorted()
            assertContains(
                list,
                result
            )
        }
    }

    @Test
    fun complexityOfString() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        val directionalKeyPad1 = KeyPad.DirectionalKeyPad(numericKeyPad)
        val directionalKeyPad2 = KeyPad.DirectionalKeyPad(directionalKeyPad1)

        val testPairs: List<Pair<String, Int>> = listOf(
            Pair("029A", 68 * 29),
            Pair("980A", 60 * 980),
            Pair("179A", 68 * 179),
            Pair("456A", 64 * 456),
            Pair("379A", 64 * 379),
        )
        testPairs.forEach { (input, result) ->
            val complexity: Int = directionalKeyPad2.complexity(input)
            assertEquals(
                result,
                complexity
            )
        }
    }

    @Test
    fun complexityOfListOfString() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        val directionalKeyPad1 = KeyPad.DirectionalKeyPad(numericKeyPad)
        val directionalKeyPad2 = KeyPad.DirectionalKeyPad(directionalKeyPad1)

        val testInputs: List<String> = listOf(
            "029A",
            "980A",
            "179A",
            "456A",
            "379A",
        )
        val complexity: Int = directionalKeyPad2.complexity(testInputs)
        assertEquals(
            126384,
            complexity
        )
    }

    @Test
    fun getActivateSequencesByChars() {
        val numericKeyPad = KeyPad.NumericKeyPad()
        assertEquals(setOf("<A"), numericKeyPad.getActivateSequencesByChars('A', '0'))
        assertEquals(setOf("^A"), numericKeyPad.getActivateSequencesByChars('0', '2'))
        assertEquals(setOf(">^^A", "^>^A", "^^>A"), numericKeyPad.getActivateSequencesByChars('2', '9'))
        assertEquals(setOf("vvvA"), numericKeyPad.getActivateSequencesByChars('9', 'A'))
    }
}