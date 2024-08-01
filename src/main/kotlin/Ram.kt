package org.example

class Ram(): Memory(){
    private val memorySize = 4000
    private val memory: ByteArray = ByteArray(memorySize)

    override fun storeByte(address: Int, value: Byte) {
        memory[address] = value
    }

    override fun getByte(address: Int): Byte = memory[address]

}