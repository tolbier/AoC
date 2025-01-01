package com.xing.aoc24.day24


sealed class Device(open val id: String) {
    abstract fun getOutput(devices: Devices): Boolean

    data class Base(override val id: String, val value: Boolean) : Device(id) {
        companion object {
            fun buildBases(rawBases: String): List<Base> {
                val regex = "([xy]\\d{2}):\\s([01])".toRegex()
                val matchResults = regex.findAll(rawBases)
                val result = matchResults.map { match ->
                    val id = match.groups[1]!!.value
                    val value = match.groups[2]!!.value == "1"
                    Base(id, value)
                }.toList()
                return result
            }
        }

        override fun getOutput(devices: Devices) = value
    }

    data class Gate(override val id: String, val op1Id: String, val op2Id: String, val gateType: GateType) :
        Device(id) {
        override fun getOutput(devices: Devices): Boolean {
            val op1 = devices.getDevice(op1Id)
            val op2 = devices.getDevice(op2Id)
            return gateType.getOutput(op1.getOutput(devices), op2.getOutput(devices))

        }

        companion object {
            fun buildGates(rawGates: String): List<Gate> {
                val regex = "([a-z0-9]{3})\\s(XOR|AND|OR)\\s([a-z0-9]{3})\\s->\\s([a-z0-9]{3})".toRegex()
                val matchResults = regex.findAll(rawGates)
                val result = matchResults.map { match ->
                    val op1 = match.groups[1]!!.value
                    val gateType = match.groups[2]!!.value

                    val op2 = match.groups[3]!!.value
                    val id = match.groups[4]!!.value
                    Gate(id, op1, op2, GateType.valueOf(gateType))
                }.toList()
                return result
            }
        }
    }
}