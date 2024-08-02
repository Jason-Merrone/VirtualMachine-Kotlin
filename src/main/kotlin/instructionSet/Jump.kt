package org.example.instructionSet

import org.example.Cpu

class Jump: Instruction {
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.programCounter = (firstByte[1].toString() + secondByte).toUShort()
        cpu.incrementCount(0u) // Do not increment count when jumping
    }
}