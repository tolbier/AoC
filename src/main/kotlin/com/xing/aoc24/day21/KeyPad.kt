package com.xing.aoc24.day21

import java.util.*

sealed class KeyPad(val charCoordsMap: Map<Char, Coords>) {
    class NumericKeyPad() : KeyPad(numericKeyPad) {

        override fun translate(input: String): Set<String> {
            val result = translateToKeypad(input)
            return result
        }
    }

    class DirectionalKeyPad(val auxKeyPad: KeyPad) : KeyPad(directionalKeyPad) {
        override fun translate(input: String): Set<String> {
            val auxTranslate = auxKeyPad.translate(input)
            val translate = auxTranslate.flatMap { this.translateToKeypad(it) }.toSet()
            val minLength = translate.minOf { it.length }
            val result = translate.filter { it.length == minLength }.toSet()
            return result
        }

        fun complexity(input: String): Int {
            val number = getNumber(input)
            val minLength = translate(input).toList().first().length
            return number * minLength
        }

        fun complexity(inputs: List<String>): Int {
            val result = inputs.sumOf { str -> complexity(str) }
            return result
        }

        private fun getNumber(input: String): Int {
            val result = input.dropLast(1).toInt()
            return result
        }
    }

    abstract fun translate(input: String): Set<String>
    internal fun translateToKeypad(input: String): Set<String> {
        var last = 'A'
        val listSetString = input.map { current ->
            val sequences = getActivateSequencesByChars(last, current)
            last = current
            sequences
        }

        val result = listSetString.fold(setOf<String>("")) { acc, it1 ->
            acc.flatMap { it2 ->
                val a = it1.map { it2 + it }.toSet()
                a
            }.toSet()
        }
        return result
    }

    val coordsCharMap = charCoordsMap.map { it.value to it.key }.toMap()
    val coordsSet = charCoordsMap.values.toSet()
    val charsSet = charCoordsMap.keys
    val coordsA = charCoordsMap['A']!!
    val directionsMapToFrom =
        coordsSet.associateWith { toCoords: Coords ->
            dijkstra(toCoords).pathMap.mapValues { (fromCoords: Coords, toCoordsSet) ->
                toCoordsSet.map { toCoords -> fromCoords.directionTo(toCoords) }.toSet()
            }
        }
    val sequencesMapFromTo = charsSet.map { from -> from to sequencesPerCharToByFrom(from) }.toMap()

    private fun sequencesPerCharToByFrom(fromChar: Char): Map<Char, Set<String>> {
        val fromCoords = charCoordsMap[fromChar]!!
        val directionsMap = directionsMapToFrom[fromCoords]!!
        fun nextDirectionChar(coords: Coords, previousSequence: String): Set<String> {
            if (coords == fromCoords) return setOf(previousSequence)
            val directions = (directionsMap[coords]!!)

            val result = directions.fold(emptySet<String>()) { acc, direction ->
                acc.plus(nextDirectionChar(coords.next(direction), previousSequence + direction.inverse().char))
            }
            return result
        }

        val result = charCoordsMap.mapValues { (_, coords) ->
            nextDirectionChar(coords, "").map { it.reversed() + "A" }.toSet()
        }.minus(fromChar)
        return result
    }

    fun getActivateSequencesByChars(from: Char, to: Char): Set<String> {
        val a = sequencesMapFromTo[from]
        val b = a!![to]
        return b ?: setOf("A")
    }


    fun dijkstra(start: Coords): DijkstraResult {
        fun initStepsPerCoords(): MutableMap<Coords, NumOfSteps> =
            coordsSet.associateWith { _ ->
                NumOfSteps.MAX_VALUE
            }.toMutableMap()

        fun getNeighborSteps(fromCoords: Coords): Set<CoordsNumOfSteps> =
            fromCoords.allNext().filter { it in coordsSet }.map { CoordsNumOfSteps(it, 1) }.toSet()

        val stepsPerCoords = initStepsPerCoords()
        val previous = mutableMapOf<Coords, Set<Coords>>()
        val visited = mutableSetOf<Coords>()
        stepsPerCoords[start] = 0
        val heap = PriorityQueue<CoordsNumOfSteps>()
        heap.add(CoordsNumOfSteps(start, 0))

        while (heap.isNotEmpty()) {
            val current = heap.poll()
            val currentCoords = current.coords
            if (currentCoords in visited) continue
            visited.add(currentCoords)
            getNeighborSteps(currentCoords).forEach { neighborStep ->
                val neighborCoords = neighborStep.coords
                val newNumOfSteps = stepsPerCoords[currentCoords]!! + neighborStep.numOfSteps

                if (newNumOfSteps <= stepsPerCoords[neighborCoords]!!) {
                    previous[neighborCoords] = setOf(currentCoords).plus(
                        previous[neighborCoords].takeIf { newNumOfSteps == stepsPerCoords[neighborCoords] }
                            ?: emptySet()
                    )

                    if (newNumOfSteps < stepsPerCoords[neighborCoords]!!) {
                        stepsPerCoords[neighborCoords] = newNumOfSteps
                        heap.add(CoordsNumOfSteps(neighborCoords, newNumOfSteps))
                    }
                }
            }
        }
        return DijkstraResult(stepsPerCoords, previous)
    }


    companion object {
        private val numericKeyPad =
            mapOf(
                '7' to Coords(0, 0),
                '8' to Coords(1, 0),
                '9' to Coords(2, 0),
                '4' to Coords(0, 1),
                '5' to Coords(1, 1),
                '6' to Coords(2, 1),
                '1' to Coords(0, 2),
                '2' to Coords(1, 2),
                '3' to Coords(2, 2),
                '0' to Coords(1, 3),
                'A' to Coords(2, 3),
            )


        private val directionalKeyPad =
            mapOf(
                '^' to Coords(1, 0),
                'A' to Coords(2, 0),
                '<' to Coords(0, 1),
                'v' to Coords(1, 1),
                '>' to Coords(2, 1),
            )
    }
}








