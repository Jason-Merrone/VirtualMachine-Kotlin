package org.example.instructionSet
import org.example.Cpu

class ReadT: Instruction() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        cpu.registers[registerIndex] = cpu.timer
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(2)
    }
}