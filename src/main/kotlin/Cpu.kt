package org.example

class Cpu {
    val registers = mutableListOf(0.toByte(), 0.toByte(), 0.toByte(), 0.toByte(), 0.toByte(), 0.toByte(), 0.toByte(), 0.toByte())

    var programCounter = 0.toByte()
    var timer = 0.toByte()
    var address = 0.toByte()
    var memory = 0


}