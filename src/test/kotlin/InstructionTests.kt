import org.example.Computer
import org.example.Cpu
import org.example.Ram
import org.example.Rom
import org.example.instructionSet.InstructionFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InstructionTests {

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testAdd() {
        val ram = Ram()
        val cpu = Cpu(ram)
        cpu.registers[0] = 5u
        cpu.registers[1] = 4u
        InstructionFactory().createInstruction("1")?.execute(cpu, "10", "12")
        assertEquals(9u, cpu.registers[2])
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testSub() {
        val ram = Ram()
        val cpu = Cpu(ram)
        cpu.registers[0] = 5u
        cpu.registers[1] = 4u
        InstructionFactory().createInstruction("2")?.execute(cpu, "21", "02")
        assertEquals(1u, cpu.registers[2])
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testConvertToBase10(){
        val ram = Ram()
        val cpu = Cpu(ram)
        cpu.registers[0] = 128u
        cpu.address = 0u
        InstructionFactory().createInstruction("d")?.execute(cpu, "d0", "00")
        assertEquals(1, cpu.ram.getByte(0))
        assertEquals(2, cpu.ram.getByte(1))
        assertEquals(8, cpu.ram.getByte(2))
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testSetA(){
        val ram = Ram()
        val cpu = Cpu(ram)
        InstructionFactory().createInstruction("a")?.execute(cpu, "a2", "55")
        assertEquals(255u, cpu.address)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testStore(){
        val ram = Ram()
        val cpu = Cpu(ram)
        InstructionFactory().createInstruction("0")?.execute(cpu, "00", "55")
        assertEquals(55u, cpu.registers[0])
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testRead(){
        val ram = Ram()
        val cpu = Cpu(ram)
        cpu.memoryFlag = 0
        cpu.address = 0u
        ram.storeByte(0,52)
        InstructionFactory().createInstruction("3")?.execute(cpu, "30", "00")
        assertEquals(52u, cpu.registers[0])
    }
}