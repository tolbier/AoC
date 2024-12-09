package com.xing.aoc24.day9

import java.math.BigInteger


fun main() {
    Day9().main()
}

class Day9 {
    fun main() {
        val diskMap = getDiskMap()
        println(diskMap)
        val resultP1 = p1(diskMap)
        println(resultP1)
//        val resultP2 = p2(antennas, size)
//        println(resultP2)
    }

    private fun p1(diskMap: List<Int>): BigInteger {
        val file = generateFile(diskMap)
        val compactedFile = compactFile(file)
        val checksum = calculateCheckSum(compactedFile)
        return checksum
    }

    private fun calculateCheckSum(compactedFile: List<Int?>): BigInteger {
        var result: BigInteger = 0.toBigInteger()
        var idx = 0
        while (idx < compactedFile.size && compactedFile[idx] != null) {
            val value = compactedFile[idx] ?: 0
            result += value.toBigInteger() * idx.toBigInteger()
//            println(result)
            idx++
        }
        return result
    }

    private fun compactFile(file: List<Int?>): List<Int?> {
        var pointer1: Int? = 0
        var pointer2: Int? = file.size - 1
        var result = file
        pointer1 = nextSpace(result, pointer1!!)
        pointer2 = previousNumber(result, pointer2!!)
        while (pointer1 != null && pointer2 != null && pointer1 < pointer2) {
            result = moveNumber(result, pointer1, pointer2)
            pointer1 = nextSpace(result, pointer1)
            pointer2 = previousNumber(result, pointer2)
        }
        return result
    }

    private fun moveNumber(file: List<Int?>, pointer1: Int, pointer2: Int): List<Int?> {
        val number = file[pointer2]
        val result = file.replaceAt(pointer2, null).replaceAt(pointer1, number)
        return result
    }

    private fun List<Int?>.replaceAt(offset: Int, value: Int?): List<Int?> =
        this.take(offset) + value + drop(offset + 1)

    private fun previousNumber(file: List<Int?>, pointer: Int): Int? {
        var idx = pointer
        while (idx > 0 && file[idx] == null) {
            idx--
        }
        if (file[idx] != null) {
            return idx
        }
        return null
    }

    private fun nextSpace(file: List<Int?>, pointer: Int): Int? {
        var idx = pointer
        while (idx < file.size - 1 && file[idx] != null) {
            idx++
        }
        if (file[idx] == null) {
            return idx
        }
        return null

    }

    private fun generateFile(diskMap: List<Int>): List<Int?> {
        var id = 0
        var idx = 0
        val result = mutableListOf<Int?>()
        while (idx < diskMap.size) {
            val fileSize = diskMap[idx]
            val spaceSize = if (idx + 1 < diskMap.size) {
                diskMap[idx + 1]
            } else 0
            result.addAll(List(fileSize) { id })
            result.addAll(List(spaceSize) { null })
            idx += 2
            id++
        }
        return result
    }


    private fun getDiskMap(): List<Int> {
        val fileName = "/com/xing/aoc24/day9/input.txt"
        return try {
            val reader = Day9::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val result = reader!!.use { it.readLine() }.map { it.toString().toInt() }
            result
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            listOf()
        }
    }
}





