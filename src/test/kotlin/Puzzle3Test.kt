import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle3Test: PuzzleTest() {
    private val input = "..##.......\n" +
            "#...#...#..\n" +
            ".#....#..#.\n" +
            "..#.#...#.#\n" +
            ".#...##..#.\n" +
            "..#.##.....\n" +
            ".#.#.#....#\n" +
            ".#........#\n" +
            "#.##...#...\n" +
            "#...##....#\n" +
            ".#..#...#.#"

    @Test
    fun `count trees in sample input`(){
        val expectedTreeCount: Long = 7
        val treeCount = Puzzle3.treeCount(input, 3,1)
        Assertions.assertEquals(
            expectedTreeCount,
            treeCount
        ) { "Tree count $treeCount should be $expectedTreeCount" }
    }

    @Test
    fun `test main function`(){
        Puzzle3.main(arrayOf())
        Assertions.assertEquals("205\n3952146825", getConsoleOutput())
    }

    @Test
    fun `count trees on multiple slopes`(){
        val expectedTreeCount: Long = 336

        val treeCount = Puzzle3.slopeList.map { Puzzle3.treeCount(input, it[0], it[1]) }.reduce(Long::times)
        Assertions.assertEquals(
            expectedTreeCount,
            treeCount
        ) { "Tree count $treeCount should be $expectedTreeCount" }
    }
}