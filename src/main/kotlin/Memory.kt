package org.example

abstract class Memory {

    abstract fun storeByte(address:Int, value:UByte)

    abstract fun getByte(address:Int):UByte
}