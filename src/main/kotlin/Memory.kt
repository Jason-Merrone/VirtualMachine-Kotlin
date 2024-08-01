package org.example

abstract class Memory {

    abstract fun storeByte(address:Int, value:Byte)

    abstract fun getByte(address:Int):Byte
}