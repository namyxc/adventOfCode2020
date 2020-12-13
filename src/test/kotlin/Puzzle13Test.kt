import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

internal class Puzzle13Test: PuzzleTest() {

    private val input = "939\n" +
            "7,13,x,x,59,x,31,19"

    @Test
    fun `earliest bus numer multipled by wait time`(){
        val expectedProd = 295
        val calculatedProd = Puzzle13.earliestBusNumberMultipledByWaitTime(input)
        Assertions.assertEquals(
            expectedProd,
            calculatedProd
        ) { "Earliest bus numer multipled by wait time should be $expectedProd" }
    }


    @ParameterizedTest(name = "test for input: \"{0}\" firstTime {1} ")
    @CsvSource(
        delimiter = ';',
        value = [
            "7,13,x,x,59,x,31,19;1068781",
            "17,x,13,19;3417",
            "67,7,59,61;754018",
            "67,x,7,59,61;779210",
            "67,7,x,59,61;1261476",
            "1789,37,47,1889;1202161486"
        ]
    )
    fun `first time after every bus starts`(input: String, firstTime: BigInteger){
        val calculatedProd = Puzzle13.firstAfterOtherStarts("100\n$input")
        println(getConsoleOutput())
        Assertions.assertEquals(
            firstTime,
            calculatedProd
        ) { "First time after every bus starts $firstTime" }
    }


    @Test
    fun `test main function`(){
        Puzzle13.main(arrayOf())
        Assertions.assertEquals("3865\n415579909629976", getConsoleOutput())
    }
}