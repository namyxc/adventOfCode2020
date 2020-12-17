import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Puzzle15Test: PuzzleTest() {

    @ParameterizedTest(name = "test for input: \"{0}\" the {1}th number {2} ")
    @CsvSource(
        delimiter = ';',
        value = [
            "0,3,6;4;0",
            "0,3,6;5;3",
            "0,3,6;6;3",
            "0,3,6;7;1",
            "0,3,6;8;0",
            "0,3,6;9;4",
            "0,3,6;10;0",
            "0,3,6;2020;436",
        ]
    )
    fun `get Nth number`(input: String, round: Int, expected2020thNumber: Int){
        val calculated2020thNumber = Puzzle15.getNthNumber(input, round)

        Assertions.assertEquals(
            expected2020thNumber,
            calculated2020thNumber
        ) { "2020th number should be $expected2020thNumber"}
    }



    @Test
    fun `test main function`(){
        Puzzle15.main(arrayOf())
        Assertions.assertEquals("755\n11962", getConsoleOutput())
    }
}