import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class Puzzle10Test: PuzzleTest() {

    private val shortInput = "16\n" +
            "10\n" +
            "15\n" +
            "5\n" +
            "1\n" +
            "11\n" +
            "7\n" +
            "19\n" +
            "6\n" +
            "12\n" +
            "4"

    private val longInput = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3"

    @Test
    fun count1and3diffInInput(){
        val expectedCount = Pair(7,5)
        val calculatedCount = Puzzle10.count1and3diffInInput(shortInput)
        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun count1and3diffInLargerInput(){
        val expectedCount = Pair(22,10)
        val calculatedCount = Puzzle10.count1and3diffInInput(longInput)
        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun countAvailableConfigurations(){
        val expectedCount = BigInteger.valueOf(8)
        val calculatedCount = Puzzle10.countAvailableConfigurations(shortInput)
        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun countAvailableConfigurationsLargeInput(){
        val expectedCount = BigInteger.valueOf(19208)
        val calculatedCount = Puzzle10.countAvailableConfigurations(longInput)
        Assertions.assertEquals(
            expectedCount,
            calculatedCount
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun `test main function`(){
        Puzzle10.main(arrayOf())
        Assertions.assertEquals("2232\n173625106649344", getConsoleOutput())
    }
}