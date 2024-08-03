package org.example.instructionSet

import org.example.Cpu

class Store: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val byteToStore1 = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid byte format")
        val byteToStore2 = secondByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid byte format")
        cpu.registers[registerIndex1] = (byteToStore1.toString(16) + byteToStore2.toString(16)).toInt(16).toUByte()
        cpu.incrementCount(2)
    }
}