package org.example.instructionSet
import org.example.Computer
import org.example.Cpu

class Read: Instruction() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val memoryAddress = cpu.address.toInt()
        if(cpu.memoryFlag == 0)
            cpu.registers[registerIndex] = Computer.getRam().getByte( memoryAddress ).toUByte()
        else
            cpu.registers[registerIndex] = Computer.getRom().getByte( memoryAddress ).toUByte()
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(2)
    }
}