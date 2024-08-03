package org.example.instructionSet

import org.example.Cpu

class SkipNotEqual: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val registerIndex2 = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        if(cpu.registers[registerIndex1] != cpu.registers[registerIndex2]) {
            cpu.incrementCount(4)
        }
        else{
            cpu.incrementCount(2)
        }
    }
}