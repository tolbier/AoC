package com.xing.aoc24.day8

import kotlin.reflect.KFunction3


fun main() {
    Day8().main()
}

class Day8 {
    fun main() {
        val (antennas, size) = getAntennasAndSize()
        println(antennas)
        val resultP1 = p1(antennas, size)
        println(resultP1)
        val resultP2 = p2(antennas, size)
        println(resultP2)
    }

    private fun p1(antennas: List<Antenna>, size: Int): Int {
        val frecuencyMap = createFrecuencyMap(antennas)
        val antinodes: List<Antinode> = createAntinodes(antennas, frecuencyMap, size, ::createOneAntiNode)


        return antinodes.size
    }

    private fun p2(antennas: List<Antenna>, size: Int): Int {
        val frecuencyMap = createFrecuencyMap(antennas)
        val antinodes: List<Antinode> = createAntinodes(antennas, frecuencyMap, size, ::createMultiAntiNode)


        return antinodes.size
    }

    private fun createAntinodes(
        antennas: List<Antenna>,
        frecuencyMap: Map<Frecuency, List<Coords>>,
        size: Int,
        createAntinodesFunction: KFunction3<Coords, Coords, Int, List<Antinode>>
    ): List<Antinode> {


        val result = antennas.flatMap { ant1 ->
            val otherCoordsOnFrecuency =
                frecuencyMap.get(ant1.frecuency)!!.filterNot { coords2 -> coords2.equals(ant1.coords) }
            val otherCoords = otherCoordsOnFrecuency.flatMap { coords2 ->
                val antinodes = createAntinodesFunction(ant1.coords, coords2, size)
                antinodes
            }
            otherCoords
        }.toSet().toList().sortedWith(compareBy<Antinode> { it.coords.y }.thenBy { it.coords.x })

        return result
    }

    private fun createOneAntiNode(coords: Coords, coords2: Coords, size: Int): List<Antinode> {
        val offsetX = coords2.x - coords.x
        val offsetY = coords2.y - coords.y
        val antinode = Antinode(Coords(coords2.x + offsetX, coords2.y + offsetY))
        return listOfNotNull(antinode.takeIf {
            antinode.coords.x >= 0 && antinode.coords.y >= 0 &&
                    antinode.coords.x < size && antinode.coords.y < size
        })
    }

    private fun createMultiAntiNode(coords: Coords, coords2: Coords, size: Int): List<Antinode> {
        val offsetX = coords2.x - coords.x
        val offsetY = coords2.y - coords.y

        val result = mutableListOf<Antinode>()
        var antinodeX = coords.x + offsetX
        var antinodeY = coords.y + offsetY
        while (antinodeX >= 0 && antinodeY >= 0 &&
            antinodeX < size && antinodeY < size
        ) {
            val antinode = Antinode(Coords(antinodeX, antinodeY))
            result.add(antinode)
            antinodeX += offsetX
            antinodeY += offsetY
        }
        return result
    }

    private fun createFrecuencyMap(antennas: List<Antenna>): Map<Frecuency, List<Coords>> =
        antennas.groupBy { it.frecuency }.mapValues { (_, antennas) -> antennas.map { it.coords } }


    private fun getAntennasAndSize(): Pair<List<Antenna>, Int> {
        val fileName = "/com/xing/aoc24/day8/input.txt"
        var size = 0
        return try {
            val a = Day8::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val antennas = a!!.useLines { linesSeq ->
                val lines = linesSeq.toList()
                lines.flatMapIndexed { y, line ->
                    size = line.length
                    line.mapIndexedNotNull { x, frecuency ->
                        Antenna(frecuency, Coords(x, y)).takeIf { frecuency != '.' }
                    }
                }
            }.toList()
            Pair(antennas, size)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            Pair(emptyList(), 0)
        }
    }
}





