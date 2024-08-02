package org.example

import java.io.File
import kotlin.system.exitProcess

class Rom(): Memory() {
    private val memorySize = 4000
    private val memory: ByteArray = ByteArray(memorySize)

    fun loadCartridge(){
        println("Type in the path to the ROM file")
        val filePath = readln()

        try {
            val bytes = File(filePath).readBytes()
            if (bytes.size > memorySize) {
                throw IllegalArgumentException("ROM image is too large (Max: $memorySize bytes)")
            }
            // Copy loaded bytes into memory
            bytes.copyInto(memory)
        } catch (e: Exception) {
            throw IllegalArgumentException("Error loading ROM: ${e.message}")
        }
        Computer.cpu.startExecution()
    }


    override fun storeByte(address: Int, value: Byte) {
        throw IllegalArgumentException("Error this cartridge does not support write operations")
    }

    override fun getByte(address: Int): Byte = memory[address]

}