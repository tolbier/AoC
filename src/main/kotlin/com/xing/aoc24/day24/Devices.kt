package com.xing.aoc24.day24

typealias Devices = Map<String, Device>

fun Devices.getOutput(): Long {
    val zIds = this.keys.filter { it.first() == 'z' }.sorted().reversed()

    val result = zIds.map { id -> getDevice(id).getOutput(this) }.fold(0L) { acc: Long, output ->
        (acc shl 1) or (1L.takeIf { output } ?: 0b0)
    }

    return result
}

fun Devices.getDevice(id: String): Device {

    return getValue(id)

}

fun Devices.draw(): String {
    var result = ""
    val (bases, gates) = values.partition { it is Device.Base }
    val basesSorted = bases.sortedBy { it.id }.map { it as Device.Base }
    basesSorted.forEachIndexed { idx, base ->
        if (idx > 0) result += "\n"
        result += "${base.id}: ${"1".takeIf { base.value } ?: "0"}"
    }
    result += "\n\n"
    val gatesSorted = gates.sortedBy { it.id }.map { it as Device.Gate }
    gatesSorted.forEachIndexed { idx, gate ->
        result += "${gate.op1Id} ${gate.gateType} ${gate.op2Id} -> ${gate.id}\n"
    }

    return result
}

fun buildDevices(rawFile: String): Map<String, Device> {
    val (rawBases, rawGates) = rawFile.split("\n\n")
    val bases: List<Device.Base> = Device.Base.buildBases(rawBases)
    val gates: List<Device.Gate> = Device.Gate.buildGates(rawGates)
    return bases.plus(gates).associateBy { it.id }
}