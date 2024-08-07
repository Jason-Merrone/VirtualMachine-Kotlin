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
        assertEquals(2u, cpu.programCounter) // Verify program counter increment
    }

    @Test
    fun testSub() {
        val cpu = Computer.cpu
        cpu.registers[0] = 5u
        cpu.registers[1] = 4u
        InstructionFactory().createInstruction("2").execute(cpu, "20", "12")
        assertEquals(1u, cpu.registers[2])
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testConvertToBase10() {
        val ram = Computer.getRam()
        val cpu = Computer.cpu
        cpu.registers[0] = 127u
        cpu.address = 0u
        InstructionFactory().createInstruction("d").execute(cpu, "d0", "00")
        assertEquals(1u, ram.getByte(0))
        assertEquals(2u, ram.getByte(1))
        assertEquals(7u, ram.getByte(2))
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testSetA() {
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("a").execute(cpu, "a2", "55")
        assertEquals(597u, cpu.address)
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testStore() {
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("0").execute(cpu, "00", "A5")
        assertEquals(165u, cpu.registers[0])
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testReadRam() {
        val ram = Computer.getRam()
        val cpu = Computer.cpu
        cpu.memoryFlag = 0
        cpu.address = 0u
        ram.storeByte(0, 52u)
        InstructionFactory().createInstruction("3").execute(cpu, "30", "00")
        assertEquals(52u, cpu.registers[0])
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testReadRom() {
        val cpu = Computer.cpu
        cpu.memoryFlag = 1
        cpu.address = 0u
        InstructionFactory().createInstruction("3").execute(cpu, "30", "00")
        assertEquals(0u, cpu.registers[0]) // Assuming ROM is initialized with 0s
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testWriteRam() {
        val ram = Computer.getRam()
        val cpu = Computer.cpu
        cpu.memoryFlag = 0
        cpu.address = 0u
        cpu.registers[0] = 5u
        InstructionFactory().createInstruction("4").execute(cpu, "40", "00")
        assertEquals(5u, ram.getByte(0))
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testWriteRom() {
        val cpu = Computer.cpu
        cpu.memoryFlag = 1
        cpu.address = 0u
        cpu.registers[0] = 5u
        assertThrows<IllegalArgumentException>("Error this cartridge does not support write operations") {
            InstructionFactory().createInstruction("4").execute(cpu, "40", "00")
        }
        // Program counter should not increment on exception
        assertEquals(0u, cpu.programCounter)
    }

    @Test
    fun testJump() {
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("5").execute(cpu, "5A", "50")
        assertEquals(2640u, cpu.programCounter) // Jump should set PC directly
    }

    @Test
    fun testSwitchMemory() {
        val cpu = Computer.cpu
        // Test switching to RAM (memoryFlag = 0)
        cpu.memoryFlag = 1
        InstructionFactory().createInstruction("7").execute(cpu, "70", "00")
        assertEquals(0, cpu.memoryFlag)
        assertEquals(2u, cpu.programCounter)

        // Test switching back to ROM (memoryFlag = 1)
        InstructionFactory().createInstruction("7").execute(cpu, "70", "00")
        assertEquals(1, cpu.memoryFlag)
        assertEquals(4u, cpu.programCounter)
    }

    @Test
    fun testSkipEqual_Equal() {
        val cpu = Computer.cpu
        cpu.registers[0] = 10u
        cpu.registers[1] = 10u
        InstructionFactory().createInstruction("8").execute(cpu, "80", "10")
        assertEquals(4u, cpu.programCounter) // Should skip 4 bytes (2 instructions)
    }

    @Test
    fun testSkipEqual_NotEqual() {
        val cpu = Computer.cpu
        cpu.registers[0] = 10u
        cpu.registers[1] = 5u
        InstructionFactory().createInstruction("8").execute(cpu, "80", "10")
        assertEquals(2u, cpu.programCounter) // Should skip only 2 bytes
    }

    @Test
    fun testSkipNotEqual_Equal() {
        val cpu = Computer.cpu
        cpu.registers[2] = 15u
        cpu.registers[3] = 15u
        InstructionFactory().createInstruction("9").execute(cpu, "92", "30")
        assertEquals(2u, cpu.programCounter) // Should skip only 2 bytes
    }

    @Test
    fun testSkipNotEqual_NotEqual() {
        val cpu = Computer.cpu
        cpu.registers[2] = 15u
        cpu.registers[3] = 20u
        InstructionFactory().createInstruction("9").execute(cpu, "92", "30")
        assertEquals(4u, cpu.programCounter) // Should skip 4 bytes
    }

    @Test
    fun testSetT() {
        val cpu = Computer.cpu
        InstructionFactory().createInstruction("b").execute(cpu, "b5", "A0")
        assertEquals(0x5Au, cpu.timer)
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testReadT() {
        val cpu = Computer.cpu
        cpu.timer = 0xB4u
        InstructionFactory().createInstruction("c").execute(cpu, "c3", "00")
        assertEquals(0xB4u, cpu.registers[3])
        assertEquals(2u, cpu.programCounter)
    }

    @Test
    fun testConvertByteToAscii_InvalidHex() {
        val cpu = Computer.cpu
        cpu.registers[5] = 0xFFu // Invalid Hex for ASCII conversion
        InstructionFactory().createInstruction("e").execute(cpu, "e5", "20")
        assertEquals(0u, cpu.registers[2]) // Should default to NUL (ASCII 0)
        assertEquals(2u, cpu.programCounter)
    }
}