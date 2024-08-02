package org.example.instructionSet

import org.example.Cpu
import java.util.*

class ReadKeyboard: Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        val registerIndex = firstByte[1].digitToIntOrNull(16) ?: throw IllegalArgumentException("Invalid register index in instruction")

        println("Please enter a hexadecimal value (0-F):")
        val userInput = readlnOrNull()?.uppercase(Locale.getDefault()) ?: ""

        val validInput = userInput.take(2).filter { it in '0'..'9' || it in 'A'..'F' }

        cpu.registers[registerIndex] = if (validInput.isNotEmpty()) {
            validInput.toInt(16).toUByte()
        } else {
            0u
        }
        cpu.incrementCount(2u)
    }
}