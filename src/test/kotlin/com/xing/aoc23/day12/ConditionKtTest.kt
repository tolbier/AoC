package com.xing.aoc23.day12

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ConditionKtTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun options() {
        val condition = ".??..??...?##."
        val result = condition.options()

        assertEquals("[.#?..??...?##., ..#..??...?##., .....#?...?##., ......#...?##., ..........###.]",result.toString())
    }
//    .*([#\?]{2}).*((#|\?){2}).*((#|\?){3}).*
}