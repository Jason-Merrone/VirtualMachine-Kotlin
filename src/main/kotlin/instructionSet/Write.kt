package org.example.instructionSet

import org.example.Computer
import org.example.Cpu

class Write: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val memoryAddress = cpu.address.toInt()
        if(cpu.memoryFlag == 0)
            Computer.ram.storeByte(memoryAddress,cpu.registers[registerIndex].toUByte())
        else
            Computer.rom.storeByte(memoryAddress,cpu.registers[registerIndex].toUByte())
        cpu.incrementCount(2)
    }
}