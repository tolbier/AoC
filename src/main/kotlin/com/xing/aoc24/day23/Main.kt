package com.xing.aoc24.day23


fun main() {
    Day23().main()
}

class Day23 {
    fun main() {
        val network = getNetwork()
        println(network)
        val resultP1 = p1(network)
        println(resultP1)
        val resultP2 = p2(network)
        println(resultP2)
    }

    private fun p1(network: Network): Int {
        val ttrios = network.findTTrios()
        println(ttrios)
        return ttrios.size
    }

    private fun p2(network: Network): String {
        val lanPartyPassword = network.getLanParty()
        return lanPartyPassword
    }


    private fun getNetwork(): Network {
        val fileName = "/com/xing/aoc24/day23/input.txt"
        try {
            val reader = Day23::class.java.getResourceAsStream(fileName)?.bufferedReader()
            val rawLinks = reader!!.readText()
            return Network.build(rawLinks)
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            return Network()
        }
    }
}



