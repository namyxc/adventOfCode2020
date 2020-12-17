import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle17Test: PuzzleTest() {

    private val input = ".#.\n" +
            "..#\n" +
            "###"

    @Test
    fun `test active cells after 8 cycles in 3D`(){
        val calculatedActiveCount = Puzzle17.calculateActiveCountIn3D(input)
        val expectedCount = 112
        Assertions.assertEquals(
            expectedCount,
            calculatedActiveCount
        ) { "Count of active cells should be $expectedCount"}

    }

    @Test
    fun `test active cells after 8 cycles in 4D`(){
        val calculatedActiveCount = Puzzle17.calculateActiveCountIn4D(input)
        val expectedCount = 848
        Assertions.assertEquals(
            expectedCount,
            calculatedActiveCount
        ) { "Count of active cells should be $expectedCount"}

    }

    @Test
    fun `test main function`(){
        Puzzle17.main(arrayOf())
        Assertions.assertEquals("306\n2572", getConsoleOutput())
    }
}