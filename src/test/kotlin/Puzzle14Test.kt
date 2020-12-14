import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class Puzzle14Test: PuzzleTest() {

    @Test
    fun `test sum used memoryAddresses`(){
        val input = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0"
        val program = Puzzle14.Program(input)
        program.run()
        val sumOfUsedMemoryAddresses = program.sumOfUsedMemoryAddresses()
        val expectedSumOfUsedMemoryAddresses = BigInteger.valueOf(165)
        Assertions.assertEquals(
            expectedSumOfUsedMemoryAddresses,
            sumOfUsedMemoryAddresses
        ) { "Sum of memory addresses should be $expectedSumOfUsedMemoryAddresses"}
    }

    @Test
    fun `test sum used memoryAddresses V2`(){
        val input = "mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1"
        val program = Puzzle14.Program(input)
        program.runV2()
        val sumOfUsedMemoryAddresses = program.sumOfUsedMemoryAddresses()
        val expectedSumOfUsedMemoryAddresses = BigInteger.valueOf(208)
        Assertions.assertEquals(
            expectedSumOfUsedMemoryAddresses,
            sumOfUsedMemoryAddresses
        ) { "Sum of memory addresses should be $expectedSumOfUsedMemoryAddresses"}
    }

    @Test
    fun `test main function`(){
        Puzzle14.main(arrayOf())
        Assertions.assertEquals("14862056079561\n3296185383161", getConsoleOutput())
    }
}