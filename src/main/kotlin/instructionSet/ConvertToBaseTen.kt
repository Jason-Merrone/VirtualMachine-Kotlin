package org.example.instructionSet
import org.example.Computer
import org.example.Cpu

class ConvertToBaseTen: Instruction() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun performOperation(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val base10Number = cpu.registers[registerIndex].toInt()

        val hundredsDigit = (base10Number / 100) % 10
        val tensDigit = (base10Number / 10) % 10
        val onesDigit = base10Number % 10

        val addressRegisterValue = cpu.address.toInt()

        Computer.getRam().storeByte(addressRegisterValue,hundredsDigit.toUByte())
        Computer.getRam().storeByte(addressRegisterValue+1,tensDigit.toUByte())
        Computer.getRam().storeByte(addressRegisterValue+2,onesDigit.toUByte())
    }
    override fun incrementCount(cpu: Cpu) {
        cpu.incrementCount(2)
    }
}