package org.example

import java.io.File
@OptIn(ExperimentalUnsignedTypes::class)
class Rom(): Memory() {
    private val memorySize = 4000
    private val memory: UByteArray = UByteArray(memorySize)

    fun loadCartridge(){
        println("Type in the path to the ROM file")
        val filePath = readln()

        try {
            val bytes = File(filePath).readBytes()
            if (bytes.size > memorySize) {
                throw IllegalArgumentException("ROM image is too large (Max: $memorySize bytes)")
            }
            // Copy loaded bytes into memory
            bytes.toUByteArray().copyInto(memory)
        } catch (e: Exception) {
            throw IllegalArgumentException("Error loading ROM: ${e.message}")
        }
        Computer.cpu.startExecution()
    }


    override fun storeByte(address: Int, value: UByte) {
        throw IllegalArgumentException("Error this cartridge does not support write operations")
    }

    override fun getByte(address: Int): UByte = memory[address]

}