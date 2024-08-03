package org.example.instructionSet

import org.example.Cpu

class SetT: Instruction {
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val byteToStore1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid byte format")
        val byteToStore2 = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid byte format")
        cpu.timer = (byteToStore1.toString(16) + byteToStore2.toString(16)).toInt(16).toUByte()
        cpu.incrementCount(2)
    }
}