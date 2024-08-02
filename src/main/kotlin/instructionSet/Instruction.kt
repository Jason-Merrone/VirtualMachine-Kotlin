package org.example.instructionSet

import org.example.Cpu

interface Instruction {
    fun execute(cpu: Cpu, firstByte:String, secondByte:String)
}