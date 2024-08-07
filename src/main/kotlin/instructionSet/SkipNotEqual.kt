package org.example.instructionSet
import org.example.Cpu

class SkipNotEqual: Instruction() {
    private var notEqual:Boolean = false
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val registerIndex2 = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        if(cpu.registers[registerIndex1] != cpu.registers[registerIndex2])
            notEqual = true
    }
    override fun incrementCount(cpu: Cpu) {
        if(notEqual)
            cpu.incrementCount(4)
        else
            cpu.incrementCount(2)
    }
}