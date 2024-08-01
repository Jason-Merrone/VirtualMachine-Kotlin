package org.example.instructionSet


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
        "A" to SetA(),
        "B" to SetT(),
        "C" to ReadT(),
        "D" to ConvertToBaseTen(),
        "E" to ConvertByteToAscii(),
        "F" to Draw()
    )

    fun createInstruction(){

    }
}