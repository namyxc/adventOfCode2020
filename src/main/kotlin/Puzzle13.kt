import java.math.BigInteger

object Puzzle13 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle13::class.java.getResource("puzzle13.txt").readText()
        val earliestBusNumberMultipledByWaitTime = earliestBusNumberMultipledByWaitTime(input)
        println(earliestBusNumberMultipledByWaitTime)
        println(firstAfterOtherStarts(input))
    }

    fun earliestBusNumberMultipledByWaitTime(input: String): Int {
        val lines = input.split("\n")
        val timeOfArrive = lines.first().toInt()
        val busNumbers = lines[1].split(",").filterNot { it == "x" }.map(String::toInt)
        val waitTimes = busNumbers.map { it to it - (timeOfArrive % it) }.minByOrNull { it.second }!!
        return waitTimes.first * waitTimes.second
    }

    fun firstAfterOtherStarts(input: String): BigInteger {
        val lines = input.split("\n")
        val busNumbers = lines[1].split(",").mapIndexed{ idx, value -> value to idx}.filterNot { it.first == "x" }.map { it.first.toBigInteger() to it.second.toBigInteger() }
        val M = busNumbers.fold(BigInteger.ONE) { prod, element -> prod * element.first}
        var x = BigInteger.ZERO
        busNumbers.forEach { pair ->
            val bNumer = pair.first
            val index = pair.second
            val m = M/bNumer
            val firstComponent = Eucledian(m, bNumer).calculate().first
            val d = (bNumer-index) % bNumer
            x += (d * firstComponent * m)
        }

        while (x < M) x += M
        return x%M
    }

    class Eucledian(a: BigInteger, b:BigInteger){
        private var q = mutableListOf(BigInteger.ZERO,BigInteger.ZERO)
        private var r = mutableListOf(a,b)
        private var x = mutableListOf(BigInteger.ONE,BigInteger.ZERO)
        private var y = mutableListOf(BigInteger.ZERO,BigInteger.ONE)
        private var i = 2

        private fun makeOneStep(): Boolean{
            q.add(r[i-2] / r[i-1])
            r.add(r[i-2] % r[i-1])
            x.add(x[i-2] - q[i] * x[i-1])
            y.add(y[i-2] - q[i] * y[i-1])
            i++
            return r.last() == BigInteger.ONE
        }

        fun calculate(): Pair<BigInteger, BigInteger>{
            while (!makeOneStep()) {}
            return  Pair(x.last(), y.last())
        }
    }


}