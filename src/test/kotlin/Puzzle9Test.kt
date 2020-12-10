import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger

internal class Puzzle9Test: PuzzleTest() {

    @Test
    fun `find first wrong numer with 5 as preamble`(){
        val input = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"
        val preamble = 5;
        val expectedOutput = BigInteger.valueOf(127)
        val calculatedWrongNumber = Puzzle9.firstWrongNumber(input, preamble)

        Assertions.assertEquals(
            expectedOutput,
            calculatedWrongNumber
        ) { "First wrong number should be $expectedOutput" }
    }

    @Test
    fun `find numbers that sum up to wrong number, then sum min and max`(){
        val input = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"
        val sum = BigInteger.valueOf(127);
        val expectedOutput = BigInteger.valueOf(62)
        val sumOfMinAndMaxThatSumsUp = Puzzle9.sumOfMinAndMaxThatSumsUp(input, sum)

        Assertions.assertEquals(
            expectedOutput,
            sumOfMinAndMaxThatSumsUp
        ) { "Sum should be $expectedOutput" }
    }

    @Test
    fun `test main function`(){
        Puzzle9.main(arrayOf())
        Assertions.assertEquals("1398413738\n169521051", getConsoleOutput())
    }
}