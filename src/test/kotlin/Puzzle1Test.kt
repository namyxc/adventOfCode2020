import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Puzzle1Test: PuzzleTest() {

    @Test
    fun `find and multiply 2 entries that sum to 2020`() {
        val entries = arrayListOf(1721, 979, 366, 299, 675, 1456)
        val multiplicated = Puzzle1.find2elementsThatSumsUp2020AndMultiply(entries)
        val expectedResult = 514579
        assertEquals(expectedResult, multiplicated) {
            "$multiplicated should equal $expectedResult"
        }
    }

    @Test
    fun `no 2 entries that sum to 2020`() {
        val entries = arrayListOf(1, 2, 3)
        val multiplicated = Puzzle1.find2elementsThatSumsUp2020AndMultiply(entries)
        val expectedResult = -1
        assertEquals(expectedResult, multiplicated) {
            "$multiplicated should equal $expectedResult"
        }
    }

    @Test
    fun `find and multiply 3 entries that sum to 2020`() {
        val entries = arrayListOf(1721, 979, 366, 299, 675, 1456)
        val multiplicated = Puzzle1.find3elementsThatSumsUp2020AndMultiply(entries)
        val expectedResult = 241861950
        assertEquals(expectedResult, multiplicated) {
            "$multiplicated should equal $expectedResult"
        }
    }

    @Test
    fun `no 3 entries that sum to 2020`() {
        val entries = arrayListOf(1, 2, 3)
        val multiplicated = Puzzle1.find3elementsThatSumsUp2020AndMultiply(entries)
        val expectedResult = -1
        assertEquals(expectedResult, multiplicated) {
            "$multiplicated should equal $expectedResult"
        }
    }

    @Test
    fun `test main function`(){
        Puzzle1.main(arrayOf())
        assertEquals("982464\n162292410", getConsoleOutput())
    }

}
