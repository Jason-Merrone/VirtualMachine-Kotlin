package org.example.instructionSet
import org.example.Computer
import org.example.Cpu

class Draw(): Instruction() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val row = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val column = secondByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        Computer.getScreen().changeDisplayedCharacter(row*8+column,cpu.registers[registerIndex1])
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(2)
    }
}