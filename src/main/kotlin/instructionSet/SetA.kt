package org.example.instructionSet

import org.example.Cpu

class SetA: Instruction {
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.address = (firstByte[1].toString() + secondByte).toUShort(16)
        cpu.incrementCount(2)
    }
}