import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Puzzle5Test: PuzzleTest() {

    @ParameterizedTest(name = "test for input: \"{0}\" valid: {1}")
    @CsvSource(
            delimiter = ';',
            value = [
                "BFFFBBFRRR; 567",
                "FFFBBBFRRR; 119",
                "BBFFBBFRLL; 820",
            ]
    )
    fun `test get seatID`(input: String, seatID: Int) {
        val calculatedSeatID = Puzzle5.getSeatID(input)
        Assertions.assertEquals(seatID, calculatedSeatID) { "SeatId (${seatID}) should be ${calculatedSeatID} " }
    }


    @Test
    fun `test main function`(){
        Puzzle5.main(arrayOf())
        Assertions.assertEquals("926\n657", getConsoleOutput())
    }

}