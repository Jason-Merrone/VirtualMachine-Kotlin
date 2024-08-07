package org.example.instructionSet
import kotlin.system.exitProcess


class InstructionFactory {
    val instructionSet = mapOf(
        "0" to Store(),
        "1" to Add(),
        "2" to Sub(),
        "3" to Read(),
        "4" to Write(),
        "5" to Jump(),
        "6" to ReadKeyboard(),
        "7" to SwitchMemory(),
        "8" to SkipEqual(),
        "9" to SkipNotEqual(),
        "a" to SetA(),
        "b" to SetT(),
        "c" to ReadT(),
        "d" to ConvertToBaseTen(),
        "e" to ConvertByteToAscii(),
        "f" to Draw()
    )

    fun createInstruction(instructionAddress:String): Instruction {
        return instructionSet[instructionAddress]?: exitProcess(0)
    }
}