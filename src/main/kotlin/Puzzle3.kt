object Puzzle3 {

    val slopeList = arrayOf(
        arrayOf(1, 1),
        arrayOf(3, 1),
        arrayOf(5, 1),
        arrayOf(7, 1),
        arrayOf(1, 2)
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle3::class.java.getResource("puzzle3.txt").readText()
        val treeCount = treeCount(input, 3, 1)
        println(treeCount)

        val result = slopeList.map { treeCount(input, it[0], it[1]) }.reduce(Long::times)
        println(result)
    }

    fun treeCount(input: String, slopeX: Int, slopeY: Int): Long {
        val lines = input.split("\n")
        val map = lines.map { it.toCharArray() }
        var x = 0
        var y = 0
        val height = map.size
        val width = map.first().size
        var treeCount: Long = 0
        while ( y < height ){
            if (map[y][x] == '#') treeCount++
            x = (x + slopeX) % width
            y += slopeY
        }
        return  treeCount
    }
}