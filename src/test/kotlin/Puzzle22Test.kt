import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle22Test: PuzzleTest() {
    private val input = "Player 1:\n" +
            "9\n" +
            "2\n" +
            "6\n" +
            "3\n" +
            "1\n" +
            "\n" +
            "Player 2:\n" +
            "5\n" +
            "8\n" +
            "4\n" +
            "7\n" +
            "10"

    @Test
    fun `test winner score normal play`(){
        val decks = Puzzle22.Decks(input)
        val expectedScore = 306
        val calculatedScore = decks.calculateWinnerScoreNormalPlay()
        Assertions.assertEquals(
            expectedScore,
            calculatedScore
        ) { "Winner score should be $expectedScore"}
    }


    @Test
    fun `test winner score recursive play`(){
        val decks = Puzzle22.Decks(input)
        val expectedScore = 291
        val calculatedScore = decks.calculateWinnerScoreRecursivePlay()
        Assertions.assertEquals(
            expectedScore,
            calculatedScore
        ) { "Winner score should be $expectedScore"}
    }

    @Test
    fun `test main function`(){
        Puzzle22.main(arrayOf())
        Assertions.assertEquals("31754\n35436", getConsoleOutput())
    }
}