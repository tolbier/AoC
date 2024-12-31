package com.xing.aoc24.day23

data class Computer(val id: String, val linkedComputers: MutableSet<String> = mutableSetOf()) {

    fun addLinkedComputer(other: Computer) {
        addLinkedComputerOnly(other)
        other.addLinkedComputerOnly(this)
    }

    private fun addLinkedComputerOnly(other: Computer) {
        linkedComputers.add(other.id)
    }

    fun linkedWith(otherId: String) =
        linkedComputers.contains(otherId)


    fun findTrios(network: Network, previousList: List<String> = emptyList()): Set<List<String>> {
        if (previousList.size == 3) {
            return if (previousList.firstOrNull() == id) {
                setOf(previousList)
            } else {
                emptySet()
            }
        }
        val result: Set<List<String>> = linkedComputers.fold(emptySet()) { acc: Set<List<String>>, linkedId ->
            val nextComputer = network.computers[linkedId]!!
            if (previousList.lastOrNull() == nextComputer.id) {
                acc
            } else {
                acc.plus(nextComputer.findTrios(network, previousList.plus(id)))
            }

        }
        return result
    }
}

