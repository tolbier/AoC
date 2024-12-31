package com.xing.aoc24.day23

data class Network(val computers: MutableMap<String, Computer> = mutableMapOf()) {


    fun findTTrios(): Set<Set<String>> =
        findTrios().filter { trio ->
            trio.any { it.first() == 't' }
        }.toSet()

    fun findTrios(): Set<Set<String>> {
        return computers.values.fold(emptySet()) { acc, computer ->
            val computerTrios: Set<Set<String>> = computer.findTrios(this).map { it.toSet() }.toSet()
            acc.plus(computerTrios)
        }
    }

    companion object {
        fun build(rawLinks: String): Network {
            val regex = "([a-z]{2})-([a-z]{2})".toRegex()
            val matchResults = regex.findAll(rawLinks)
            val result = Network()
            matchResults.forEach { match ->
                val id1 = match.groups[1]!!.value
                val id2 = match.groups[2]!!.value
                val c1 = result.computers.getOrPut(id1) { Computer(id1) }
                val c2 = result.computers.getOrPut(id2) { Computer(id2) }
                c1.addLinkedComputer(c2)
            }
            return result
        }

    }
}
