package org.example.instructionSet
import org.example.Cpu

class Jump: Instruction() {
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.programCounter = (firstByte[1].toString() + secondByte).toUShort(16)
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(0) // Does not increment count when jumping
    }
}