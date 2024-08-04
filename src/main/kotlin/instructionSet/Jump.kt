package org.example.instructionSet

import org.example.Cpu

class Jump: Instruction {
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.programCounter = (firstByte[1].toString() + secondByte).toUShort(16)
        cpu.incrementCount(0) // Do not increment count when jumping
    }
}