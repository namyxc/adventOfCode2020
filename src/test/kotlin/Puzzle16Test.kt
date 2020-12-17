import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle16Test: PuzzleTest() {

    @Test
    fun `sum invalid numbers on tickets`(){
        val input = "class: 1-3 or 5-7\n" +
                "row: 6-11 or 33-44\n" +
                "seat: 13-40 or 45-50\n" +
                "\n" +
                "your ticket:\n" +
                "7,1,14\n" +
                "\n" +
                "nearby tickets:\n" +
                "7,3,47\n" +
                "40,4,50\n" +
                "55,2,20\n" +
                "38,6,12"

        val sumOfInvalidNumbers = Puzzle16.TicketInfo(input).sumInvalidNumbers()
        val expectedSum = 71
        Assertions.assertEquals(
            expectedSum,
            sumOfInvalidNumbers
        ) { "Sum of invalid numbers should be $expectedSum"}
    }

    @Test
    fun `product of department fileds on my ticket`(){
        val input = "class: 0-1 or 4-19\n" +
                "row: 0-5 or 8-19\n" +
                "seat: 0-13 or 16-19\n" +
                "\n" +
                "your ticket:\n" +
                "11,12,13\n" +
                "\n" +
                "nearby tickets:\n" +
                "3,9,18\n" +
                "15,1,5\n" +
                "5,14,9"
        val prodOfDepartmentFields = Puzzle16.TicketInfo(input).getProdOfDepartmentFields()

    }
}