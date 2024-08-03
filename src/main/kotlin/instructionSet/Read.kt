package org.example.instructionSet

import org.example.Computer
import org.example.Cpu

class Read: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val memoryAddress = cpu.address.toInt()
        if(cpu.memoryFlag == 0)
            cpu.registers[registerIndex] = Computer.ram.getByte( memoryAddress ).toUByte()
        else
            cpu.registers[registerIndex] = Computer.rom.getByte( memoryAddress ).toUByte()
        cpu.incrementCount(2)
    }
}