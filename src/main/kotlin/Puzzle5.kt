object Puzzle5 {


    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle5::class.java.getResource("puzzle5.txt").readText().split('\n')
        val seatIDs = input.map { getSeatID(it) }
        val maxSeatID = seatIDs.maxOrNull()!!
        println(maxSeatID)
        val minSeatID = seatIDs.minOrNull()!!
        val expectedSum = (maxSeatID + minSeatID)/2 * (maxSeatID - minSeatID + 1)
        val sum = seatIDs.sum()
        print(expectedSum-sum)

    }

    fun getSeatID(input: String): Int {
        val replacedString = input
                .replace('F', '0')
                .replace('B', '1')
                .replace('L', '0')
                .replace('R', '1')

        return replacedString.toInt(2)
    }
}