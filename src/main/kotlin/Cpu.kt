package org.example

import org.example.instructionSet.InstructionFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

@OptIn(ExperimentalUnsignedTypes::class)
class Cpu() {

    val registers = UByteArray(8)
    var programCounter:UShort = 0u
    var timer:UByte = 0u
    var address:UShort = 0u
    var memoryFlag = 0

    private var timerTicks = 0

    private val executor = Executors.newSingleThreadScheduledExecutor()

    val runnable = Runnable {
        executeInstruction()
        decrementTimer()
    }

    fun startExecution(){
        var clockFuture = executor.scheduleAtFixedRate(
            runnable,
            0,
            2, // 2ms for 500Hz
            TimeUnit.MILLISECONDS
        )
    }

    fun incrementCount(amount:Int){
        programCounter = (programCounter + amount.toUShort()).toUShort()
    }

    private fun decrementTimer() {
        timerTicks++
        if(timerTicks >= 8){
            timerTicks = 0
            if(timer > 0u){
                timer--
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun executeInstruction(){
        // Gets binary from rom and converts to HexString
        val instructionText = listOf(Computer.rom.getByte(programCounter.toInt()).toHexString(),
                                     Computer.rom.getByte(programCounter.toInt()+1).toHexString())

        // Terminates the program if instruction is 0000
        if(instructionText[0] == "00" &&instructionText[1] == "00") {
            exitProcess(0)
        }

        // Create the correct instruction from instruction factory via the first hex digit
        val instruction = InstructionFactory().createInstruction(instructionText[0].filterNot { it.isWhitespace() }.toList()[0].toString())

        instruction.execute(this,instructionText[0],instructionText[1])
    }
}