object Puzzle15 {


    @JvmStatic
    fun main(args: Array<String>) {
        val input = "8,13,1,0,18,9"
        val the2020thNumber = getNthNumber(input, 2020)
        println(the2020thNumber)
        val the30000000thNumber = getNthNumber(input, 30000000)
        println(the30000000thNumber)
    }

    data class Seen(private var _last: Int) {
        var beforeLast: Int? = null
        var last: Int
            get() = _last
            set(value) {
                beforeLast = _last
                _last = value
            }
    }

    fun getNthNumber(input: String, round: Int): Int {
        val oldNumbers = HashMap<Int, Seen>()
        val numbers = input.split(",").map(String::toInt)
        numbers.forEachIndexed { index, number ->
            oldNumbers[number] = Seen(index)
        }
        var index = numbers.count()
        var lastNumber = numbers.last()
        while (index < round) {
            val lastSeen = oldNumbers[lastNumber]!!
            if (lastSeen.beforeLast == null) {
                if (!oldNumbers.containsKey(0))
                    oldNumbers[0] = Seen(index)
                else
                    oldNumbers[0]!!.last = index
                lastNumber = 0
            } else {
                val diff = index - 1 - lastSeen.beforeLast!!
                if (!oldNumbers.containsKey(diff))
                    oldNumbers[diff] = Seen(index)
                else
                    oldNumbers[diff]!!.last = index
                lastNumber = diff
            }
            index++
        }
        return lastNumber
    }
}