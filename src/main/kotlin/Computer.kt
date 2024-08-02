package org.example

object Computer {
    val rom = Rom()
    val ram = Ram()
    val screen = Screen()
    val cpu = Cpu(ram)

}