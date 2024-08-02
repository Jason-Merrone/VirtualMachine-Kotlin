package org.example

import org.example.instructionSet.InstructionFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

@OptIn(ExperimentalUnsignedTypes::class)
class Cpu() {

    val registers = UByteArray(8)
    var programCounter = UByteArray(2)
    var timer = ByteArray(1) {0}
    var address:UShort = 0u
    var memoryFlag = 0

    private var timerTicks = 0

    init{
        programCounter[0] = 0u
        programCounter[1] = 1u
    }

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

    fun incrementCount(amount:UInt){
        programCounter[0] = (programCounter[0] + amount).toUByte()
        programCounter[1] = (programCounter[1] + amount).toUByte()
    }
    fun decrementTimer() {
        timerTicks++
        if(timerTicks >= 8){
            timerTicks = 0
            if(timer[0] > 0){
                timer[0]--
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun executeInstruction(){
        // Gets binary from rom and converts to HexString
        val instructionText = listOf(Computer.rom.getByte(programCounter[0].toInt()).toHexString(),
                                     Computer.rom.getByte(programCounter[1].toInt()).toHexString())
        // Create the correct instruction from instruction factory via the first hex digit
        val instruction = InstructionFactory().createInstruction(instructionText[0].filterNot { it.isWhitespace() }.toList()[0].toString())

        instruction.execute(this,instructionText[0],instructionText[1])
        println("test ${registers[0]},${registers[1]}")
    }
}