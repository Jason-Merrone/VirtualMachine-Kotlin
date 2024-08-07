package org.example.instructionSet
import org.example.Cpu

class SetA: Instruction() {
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.address = (firstByte[1].toString() + secondByte).toUShort(16)
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(2)
    }
}