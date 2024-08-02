package org.example

object Computer {
    var rom = Rom()
        private set
    var ram = Ram()
        private set
    var screen = Screen()
        private set
    var cpu = Cpu()
        private set

    fun reset(){
        rom = Rom()
        ram = Ram()
        screen = Screen()
        cpu = Cpu()
    }
}