package org.example.instructionSet

import org.example.Cpu

class SwitchMemory: Instruction {
    private val memoryState = mapOf(
        0 to 1,
        1 to 0
    )
    override fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        cpu.memoryFlag = memoryState[cpu.memoryFlag] ?: 0 // If memoryFlag is not 0 or 1, it is set to 0
        cpu.incrementCount(2)
    }
}