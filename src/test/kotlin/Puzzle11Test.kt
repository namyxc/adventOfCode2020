import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle11Test: PuzzleTest() {
    private val input = "L.LL.LL.LL\n" +
            "LLLLLLL.LL\n" +
            "L.L.L..L..\n" +
            "LLLL.LL.LL\n" +
            "L.LL.LL.LL\n" +
            "L.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLLL\n" +
            "L.LLLLLL.L\n" +
            "L.LLLLL.LL"

    @Test
    fun `test occupied seats after no change`(){
        val expectedCount = 37
        val calculatedCount = Puzzle11.calculateOccupiedSeats(input, Puzzle11::countImmediatelyAdjacentSeats, 4)

        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun `test occupied seats (in directions) after no change`(){
        val expectedCount = 26
        val calculatedCount = Puzzle11.calculateOccupiedSeats(input, Puzzle11::countVisibleAdjacentSeats, 5)

        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun `test main function`(){
        Puzzle11.main(arrayOf())
        Assertions.assertEquals("2093\n1862", getConsoleOutput())
    }
}