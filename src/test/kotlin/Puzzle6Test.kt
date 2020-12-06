import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class Puzzle6Test: PuzzleTest() {
    companion object {

        @JvmStatic
        fun testInput(): Stream<Arguments?>? {
            return Stream.of(
                    Arguments.of("abc", 3, 3),
                    Arguments.of("a,b,c", 3, 0),
                    Arguments.of("ab,ac", 3, 1),
                    Arguments.of("a,a,a,a", 1, 1),
                    Arguments.of("b", 1, 1)
            )
        }
    }

    @ParameterizedTest(name = "test for input: \"{0}\" distinct yes: {1}")
    @MethodSource("testInput")
    fun `test count distinct yes answers in group`(input: String, expectedDistinctCount: Int){
        val distinctAnswers = Puzzle6.getDistinctAnswers(input, ",")
        Assertions.assertEquals(
                expectedDistinctCount,
                distinctAnswers
        ) { "Distinct count $distinctAnswers should be $expectedDistinctCount with input: $input" }
    }

    @ParameterizedTest(name = "test for input: \"{0}\" all yes: {2}")
    @MethodSource("testInput")
    fun `test count all yes answers in group`(input: String, expectedDistinctCount: Int, expectedAllYesCount: Int){
        val distinctAnswers = Puzzle6.getAllYesAnswers(input, ",")
        Assertions.assertEquals(
                expectedAllYesCount,
                distinctAnswers
        ) { "Distinct count $distinctAnswers should be $expectedAllYesCount with input: $input" }
    }


    @Test
    fun `test main function`(){
        Puzzle6.main(arrayOf())
        Assertions.assertEquals("6686\n3476", getConsoleOutput())
    }
}