package org.example.instructionSet

import org.example.Cpu

abstract class Instruction {
    abstract fun performOperation(cpu: Cpu, firstByte: String, secondByte: String)
    abstract fun incrementCount(cpu: Cpu)

    // This is the template method
    fun execute(cpu: Cpu, firstByte: String, secondByte: String) {
        performOperation(cpu, firstByte, secondByte)
        incrementCount(cpu)
    }
}