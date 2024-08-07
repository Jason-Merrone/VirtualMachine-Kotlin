package org.example

import org.example.instructionSet.InstructionFactory

object Computer {
    private var rom = Rom()
    private var ram = Ram()
    private var screen = Screen()
    val cpu = Cpu()

    // Facade for resetting the computer
    fun reset() {
        rom = Rom()
        ram = Ram()
        screen = Screen()
        cpu.reset()
    }

    fun getRom() = rom
    fun getRam() = ram
    fun getScreen() = screen
}

fun main() {
    // Using the Facade to load cartridge and start execution
    Computer.getRom().loadCartridge()
}