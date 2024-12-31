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

    fun getLanParty(): String {


        fun dfs(computer: Computer, acc: List<String>): Set<List<String>> {
            val id = computer.id
            if (!acc.all { computer.linkedWith(it) } || (acc.lastOrNull()?.compareTo(id) ?: -1) > 0) {
                return setOf(acc)
            }

            val possibleResults = computer.linkedComputers
                .minus(acc)
                .map { computers[it]!! }
                .flatMap { dfs(it, acc.plus(id)) }.toSet()
            val maxSize = possibleResults.maxOfOrNull { it.size } ?: 0

            val results = possibleResults.filter { it.size == maxSize }.toSet()

            return results
        }

        val result = computers.values.map { computer ->
            dfs(computer, emptyList())

        }
        val result2 = result.flatten().toSet()
        val maxSize = result2.maxOfOrNull { it.size } ?: 0
        val results = result2.filter { it.size == maxSize }
        return results.first().joinToString(",")
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
