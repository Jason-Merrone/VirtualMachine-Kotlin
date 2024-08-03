import org.example.Computer
import org.example.instructionSet.InstructionFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class InstructionTests {

    @BeforeEach
    fun setUp() {
        Computer.reset()
    }

    @Test
    fun testAdd() {
        val cpu = Computer.cpu
        cpu.registers[0] = 5u
        cpu.registers[1] = 4u
        InstructionFactory().createInstruction("1").execute(cpu, "10", "12")
        assertEquals(9u, cpu.registers[2])
    }

    @Test
    fun testSub() {
        val cpu = Computer.cpu
        cpu.registers[0] = 5u
        cpu.registers[1] = 4u
        InstructionFactory().createInstruction("2").execute(cpu, "20", "12")
        assertEquals(1u, cpu.registers[2])
    }

    @Test
    fun testConvertToBase10(){
        val ram = Computer.ram
        val cpu = Computer.cpu
        cpu.registers[0] = 127.toUByte()
        cpu.address = 0u
        InstructionFactory().createInstruction("d").execute(cpu, "d0", "00")
        assertEquals(1u, ram.getByte(0))
        assertEquals(2u, ram.getByte(1))
        assertEquals(7u, ram.getByte(2))
    }

    @Test
    fun testSetA(){
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("a").execute(cpu, "a2", "55")
        assertEquals(597u, cpu.address)
    }

    @Test
    fun testStore(){
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("0").execute(cpu, "00", "A5")
        assertEquals(165u, cpu.registers[0])
    }

    @Test
    fun testReadRam(){
        val ram = Computer.ram
        val cpu = Computer.cpu
        cpu.memoryFlag = 0
        cpu.address = 0u
        ram.storeByte(0,52u)
        InstructionFactory().createInstruction("3").execute(cpu, "30", "00")
        assertEquals(52u, cpu.registers[0])
    }

    @Test
    fun testReadRom(){
        val rom = Computer.rom
        val cpu = Computer.cpu
        cpu.memoryFlag = 1
        cpu.address = 0u
        InstructionFactory().createInstruction("3").execute(cpu, "30", "00")
        assertEquals(0u, cpu.registers[0])
    }

    @Test
    fun testWriteRam(){
        val ram = Computer.ram
        val cpu = Computer.cpu
        cpu.memoryFlag = 0
        cpu.address = 0u
        cpu.registers[0] = 5u
        InstructionFactory().createInstruction("4").execute(cpu, "40", "00")
        assertEquals(5u, ram.getByte(0))
    }

    @Test
    fun testWriteRom(){
        val cpu = Computer.cpu
        cpu.memoryFlag = 1
        cpu.address = 0u
        cpu.registers[0] = 5u
        assertThrows<IllegalArgumentException>("Error this cartridge does not support write operations") {
            InstructionFactory().createInstruction("4").execute(cpu, "40", "00")
        }
    }

    @Test
    fun testJump(){
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("5").execute(cpu, "51", "50")
        assertEquals(150u,cpu.programCounter)
    }

    @Test
    fun testSwitchMemory(){
        val cpu = Computer.cpu
        cpu.memoryFlag = 2
        InstructionFactory().createInstruction("7").execute(cpu, "70", "00")
        assertEquals(0,cpu.memoryFlag)
        InstructionFactory().createInstruction("7").execute(cpu, "70", "00")
        assertEquals(1,cpu.memoryFlag)
    }
}