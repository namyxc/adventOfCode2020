import java.math.BigInteger

object Puzzle10 {



    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle10::class.java.getResource("puzzle10.txt").readText()
        val count1and3diffInInput =  count1and3diffInInput(input)
        println(count1and3diffInInput.first * count1and3diffInInput.second)
        val countAvailableConfigurations = countAvailableConfigurations(input)
        println(countAvailableConfigurations)

    }


    fun count1and3diffInInput(input: String): Pair<Int, Int> {
        val orderedNumbers = inputToSortedList(input)
        var diff1 = 0
        var diff3 = 0
        for ( i in 1 until orderedNumbers.count()){
            val diff = orderedNumbers[i] - orderedNumbers[i-1]
            if (diff == 1) diff1 ++
            if (diff == 3) diff3 ++
        }
        return Pair(diff1, diff3)
    }

    private fun inputToSortedList(input: String): List<Int> {
        val numbers = input.split("\n").map(String::toInt)
        val numbersWithStartAndEnd = numbers + 0 + (numbers.maxOrNull()!! + 3)
        return numbersWithStartAndEnd.sorted()
    }

    fun countAvailableConfigurations(input: String): BigInteger {
        val orderedNumbers = inputToSortedList(input)
        val parentsMap = HashMap<Int, MutableList<Int>>()
        for ( i in orderedNumbers) {
            for (j in 1..3) {
                val parent = i - j
                if (orderedNumbers.contains(parent)) {
                    if (!parentsMap.containsKey(i)) {
                        parentsMap[i] = ArrayList()
                    }
                    parentsMap[i]!!.add(parent)
                }
            }
        }

        val parentCount = HashMap<Int, BigInteger>()

        for (num in orderedNumbers){
            val parents = parentsMap[num]
            if (parents == null){
                parentCount[num] = BigInteger.ONE
            }else{
                parentCount[num] = parents.map{ parentCount[it]!!}.sumOf { it }
            }
        }

        val maxValue = orderedNumbers.maxOrNull()!!
        return parentCount[maxValue]!!
    }
}