object Puzzle1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle1::class.java.getResource("puzzle1.txt").readText().split('\n').filter { it.isNotEmpty() }
            .map { it.toInt() }
        val result2 = find2elementsThatSumsUp2020AndMultiply(input)
        println(result2)
        val result3 = find3elementsThatSumsUp2020AndMultiply(input)
        println(result3)
    }

    fun find2elementsThatSumsUp2020AndMultiply(entries: List<Int>): Int {
        for (i in 0 until entries.size) {
            for (j in i + 1 until entries.size) {
                if (entries[i] + entries[j] == 2020) {
                    return entries[i] * entries[j]
                }
            }
        }
        return -1
    }

    fun find3elementsThatSumsUp2020AndMultiply(entries: List<Int>): Int {
        for (i in 0 until entries.size) {
            for (j in i + 1 until entries.size) {
                for (k in j + 1 until entries.size) {
                    if (entries[i] + entries[j] + entries[k] == 2020) {
                        return entries[i] * entries[j] * entries[k]
                    }
                }
            }
        }
        return -1

    }
}