package com.xing.aoc24.day24


fun main() {
    Day24().main()
}

class Day24 {
    fun main() {
        val devices = getDevices()
        println(devices)

        val resultP1 = p1(devices)
        println(resultP1)

        val resultP2 = p2(devices)
        println(resultP2)

        ///Find manualy: Solution is "ctg,dmh,dvq,rpb,rpv,z11,z31,z38"
    }

    private fun p1(devices: Devices): Long {

        return devices.getOutput()
    }

    private fun p2(devices: Devices): String {

        return devices.draw()
    }

    private fun getDevices(): Devices {
        val fileName = "/com/xing/aoc24/day24/input.txt"

        return try {
            val reader = Day24::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawFile = reader!!.readText()
            return buildDevices(rawFile)

        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            emptyMap()
        }
    }


}







