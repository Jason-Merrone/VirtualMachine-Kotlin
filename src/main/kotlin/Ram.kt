package org.example
@OptIn(ExperimentalUnsignedTypes::class)
class Ram(): Memory(){
    private val memorySize = 4000
    private val memory: UByteArray = UByteArray(memorySize)

    override fun storeByte(address: Int, value: UByte) {
        memory[address] = value
    }

    override fun getByte(address: Int): UByte = memory[address]

}