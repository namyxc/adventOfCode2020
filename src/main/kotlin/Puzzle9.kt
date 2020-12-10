import java.math.BigInteger

object Puzzle9 {



    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle9::class.java.getResource("puzzle9.txt").readText()
        val firstWrongNumber =  firstWrongNumber(input, 25)
        println(firstWrongNumber)


        val sumOfMinAndMaxThatSumsUp =  sumOfMinAndMaxThatSumsUp(input, firstWrongNumber)
        print(sumOfMinAndMaxThatSumsUp)
    }

    fun calculateSums(numbers: List<BigInteger>, index: Int, preamble: Int): ArrayList<BigInteger> {
        var sums = ArrayList<BigInteger>()
        for (i in index - preamble..index-1){
            for (j in i+1..index-1){
                val sum = numbers[i] + numbers[j]
                sums.add(sum)
            }
        }
        return sums
    }

    fun firstWrongNumber(input: String, preamble: Int): BigInteger {
        val numbers = input.split("\n").map(String::toBigInteger)
        var index = preamble

        while (index < numbers.size){
            val sums = calculateSums(numbers, index, preamble)
            if (!sums.contains(numbers[index])){
                return numbers[index]
            }else{
                index++
            }
        }
        throw Exception("No such a number")
    }

    fun sumOfMinAndMaxThatSumsUp(input: String, sum: BigInteger): BigInteger {
        val numbers = input.split("\n").map(String::toBigInteger)
        var firstIndex = 0
        var lastIndex = 1
        var actualSum = numbers[firstIndex] + numbers[lastIndex]

        while (actualSum != sum){
            if (actualSum < sum){
                lastIndex++
                actualSum += numbers[lastIndex]
            }else{
                actualSum -= numbers[firstIndex]
                firstIndex++
            }
        }
        val arrayOfSum = numbers.subList(firstIndex, lastIndex)
        return  arrayOfSum.minOrNull()!! + arrayOfSum.maxOrNull()!!

    }
}