package org.example.instructionSet

import org.example.Cpu

class ConvertByteToAscii: Instruction {
    private val hexToAsciiMap = mapOf(
        "0" to "48",
        "1" to "49",
        "2" to "50",
        "3" to "51",
        "4" to "52",
        "5" to "53",
        "6" to "54",
        "7" to "55",
        "8" to "56",
        "9" to "57",
        "A" to "65",
        "B" to "66",
        "C" to "67",
        "D" to "68",
        "E" to "69",
        "F" to "70",
    )

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex1 = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        val registerIndex2 = secondByte[0].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")
        cpu.registers[registerIndex2] = hexToAsciiMap[cpu.registers[registerIndex1].toString()]?.toUByte() ?: '\u0000'.code.toUByte()
        cpu.incrementCount(2)
    }
}