import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

internal class Puzzle18Test: PuzzleTest() {


    @ParameterizedTest(name = "test for input: \"{0}\" RNP {1} ")
    @CsvSource(
        delimiter = ';',
        value = [
            "3 + 4;3 4 +"
        ]
    )
    fun `test polish notation`(input: String, expectedOutput: String){
        val calculatedOutput = Puzzle18.toRPN(input, Puzzle18::equalPrecedence).joinToString(" ")
        Assertions.assertEquals(
            expectedOutput,
            calculatedOutput
        ) { "RNP should be $expectedOutput"}
    }

    @ParameterizedTest(name = "test for input: \"{0}\" RNP {1} ")
    @CsvSource(
        delimiter = ';',
        value = [
            "3 + 4;7",
            "1 + 2 * 3 + 4 * 5 + 6;71",
            "2 * 3 + (4 * 5);26",
            "5 + (8 * 3 + 9 + 3 * 4 * 3);437",
            "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4));12240",
            "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2;13632"
        ]
    )
    fun `test eval polish notation with equal precedence`(input: String, expectedOutput: BigInteger){
        val rpn = Puzzle18.toRPN(input, Puzzle18::equalPrecedence)
        val calculatedOutput = Puzzle18.evalRNP(rpn)
        Assertions.assertEquals(
            expectedOutput,
            calculatedOutput
        ) { "Eval should be $expectedOutput"}
    }
    @ParameterizedTest(name = "test for input: \"{0}\" RNP {1} ")
    @CsvSource(
        delimiter = ';',
        value = [
            "3 + 4;7",
            "1 + 2 * 3 + 4 * 5 + 6;231",
            "2 * 3 + (4 * 5);46",
            "5 + (8 * 3 + 9 + 3 * 4 * 3);1445",
            "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4));669060",
            "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2;23340"
        ]
    )

    fun `test eval polish notation with reversed precedence`(input: String, expectedOutput: BigInteger){
        val rpn = Puzzle18.toRPN(input, Puzzle18::additionBeforeMultiplicationPrecedence)
        val calculatedOutput = Puzzle18.evalRNP(rpn)
        Assertions.assertEquals(
            expectedOutput,
            calculatedOutput
        ) { "Eval should be $expectedOutput"}
    }

    @Test
    fun `test main function`(){
        Puzzle18.main(arrayOf())
        Assertions.assertEquals("29839238838303\n201376568795521", getConsoleOutput())
    }
}