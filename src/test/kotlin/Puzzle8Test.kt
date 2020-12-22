import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle8Test: PuzzleTest() {
    private val input = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6"

    @Test
    fun `test get acc value before loop`(){
        val program = Puzzle8.Program(input)
        val expectedAcc = 5
        val calculatedAcc = program.calculateAccBeforeLoop()
        Assertions.assertEquals(
            expectedAcc,
            calculatedAcc
        ) { "Acc should be $expectedAcc"}
    }

    @Test
    fun `test repair program and get acc`(){
        val program = Puzzle8.Program(input)
        val expectedAcc = 8
        val calculatedAcc = program.correctedProgramAcc()
        Assertions.assertEquals(
            expectedAcc,
            calculatedAcc
        ) { "Acc should be $expectedAcc"}
    }

    @Test
    fun `test main function`(){
        Puzzle8.main(arrayOf())
        Assertions.assertEquals("1766\n1639", getConsoleOutput())
    }
}
