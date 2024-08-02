package org.example.instructionSet

import org.example.Computer
import org.example.Cpu

class ConvertToBaseTen: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val base10Number = cpu.registers[registerIndex1].toInt()

        val hundredsDigit = (base10Number / 100) % 10
        val tensDigit = (base10Number / 10) % 10
        val onesDigit = base10Number % 10

        val addressRegisterValue = cpu.address.toInt()

        Computer.ram.storeByte(addressRegisterValue,hundredsDigit.toByte())
        Computer.ram.storeByte(addressRegisterValue+1,tensDigit.toByte())
        Computer.ram.storeByte(addressRegisterValue+2,onesDigit.toByte())

        cpu.incrementCount(2u)
    }
}