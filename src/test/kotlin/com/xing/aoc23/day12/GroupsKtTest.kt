package com.xing.aoc23.day12

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GroupsKtTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun pattern123() {
        val result = listOf(1,2,3).pattern()
        assertEquals("^[\\.\\?]*([#\\?]{1}))[\\.\\?]+([#\\?]{2}))[\\.\\?]+([#\\?]{3})[\\.\\?]*\$",result)
    }
}