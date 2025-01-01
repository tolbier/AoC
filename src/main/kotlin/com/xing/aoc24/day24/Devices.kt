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

fun buildDevices(rawFile: String): Map<String, Device> {
    val (rawBases, rawGates) = rawFile.split("\n\n")
    val bases: List<Device.Base> = Device.Base.buildBases(rawBases)
    val gates: List<Device.Gate> = Device.Gate.buildGates(rawGates)
    return bases.plus(gates).associateBy { it.id }
}