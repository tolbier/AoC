package com.xing.aoc24.day8


fun main() {
    Day8().main()
}

class Day8 {
    fun main() {
        val (antennas, size) = getAntennasAndSize()
        println(antennas)
        val resultP1 = p1(antennas, size)
        println(resultP1)
//        val resultP2 = p2(antennas)
//        println(resultP2)
    }

    private fun p1(antennas: List<Antenna>, size: Int): Int {
        val frecuencyMap = createFrecuencyMap(antennas)
        val antinodes: Set<Antinode> = createAntinodesSet(antennas, frecuencyMap, size)


        return antinodes.size
    }

    private fun createAntinodesSet(
        antennas: List<Antenna>,
        frecuencyMap: Map<Frecuency, List<Coords>>,
        size: Int
    ): Set<Antinode> {
        /*
              recorrer la lista de Antennas(ant1) y buscar el resto de coordenadas de  antenas en la misma frecuencia
                  para cada una (coord2) de las coordenadas de antenas en misma frecuencia (salvo ant1) crear un antinodo

              Devolver el conjunto de Antinodes
               */

        val result = antennas.flatMap { ant1 ->
            val otherCoordsOnFrecuency =
                frecuencyMap.get(ant1.frecuency)!!.filterNot { coords2 -> coords2.equals(ant1.coords) }
            otherCoordsOnFrecuency.mapNotNull { coords2 ->
                createAntiNode(ant1.coords, coords2, size)
            }
        }.toSet()
        return result
    }

    private fun createAntiNode(coords: Coords, coords2: Coords, size: Int): Antinode? {
        val offsetX = coords2.x - coords.x
        val offsetY = coords2.y - coords.y
        val antinode = Antinode(Coords(coords2.x + offsetX, coords2.y + offsetY))
        return antinode.takeIf {
            antinode.coords.x >= 0 && antinode.coords.y >= 0 &&
                    antinode.coords.x < size && antinode.coords.y < size
        }
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





