package org.example.instructionSet

import org.example.Cpu

class Store: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val byteToStore = secondByte.toUByte()
        cpu.registers[registerIndex1] = byteToStore
        cpu.incrementCount(2u)
    }
}