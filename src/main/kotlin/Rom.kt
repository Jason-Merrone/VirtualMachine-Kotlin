package org.example

import java.io.File
import kotlin.system.exitProcess

class Rom(): Memory() {
    private val memorySize = 4000
    private val memory: ByteArray = ByteArray(memorySize)

    init{
        println("Type in the path to the ROM file")
        val filePath = readln()

        try {
            val bytes = File(filePath).readBytes()
            if (bytes.size > memorySize) {
                throw IllegalArgumentException("ROM image is too large (Max: $memorySize bytes)")
                exitProcess(403)
            }
            // Copy loaded bytes into memory
            bytes.copyInto(memory)
        } catch (e: Exception) {
            println("Error loading ROM: ${e.message}")
            exitProcess(404)
        }
    }

    override fun storeByte(address: Int, value: Byte) {
        memory[address] = value
    }

    override fun getByte(address: Int): Byte = memory[address]

}