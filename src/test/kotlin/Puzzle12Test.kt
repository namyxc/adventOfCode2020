import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle12Test: PuzzleTest() {

    private val input = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11"
    @Test
    fun `test distance`(){
        val expectedDistance = 25
        val calculatedDistance = Puzzle12.calculateDistance(input)
        Assertions.assertEquals(
            expectedDistance,
            calculatedDistance
        ) { "Distance should be $expectedDistance" }
    }

    @Test
    fun `test distance with waypoint`(){
        val expectedDistance = 286
        val calculatedDistance = Puzzle12.calculateDistanceWithWaypoint(input)
        Assertions.assertEquals(
            expectedDistance,
            calculatedDistance
        ) { "Distance should be $expectedDistance" }
    }

    @Test
    fun `test main function`(){
        Puzzle12.main(arrayOf())
        Assertions.assertEquals("420\n42073", getConsoleOutput())
    }
}