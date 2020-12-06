object Puzzle6 {
    fun getDistinctAnswers(input: String, delimiter: String): Int {
        return input.replace(delimiter, "").toCharArray().distinct().count()
    }

    fun getAllYesAnswers(input: String, delimiter: String): Int {
        val answers = input.split(delimiter)
        return 'a'.rangeTo('z').count { char -> answers.all { answer -> answer.contains(char) } }
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle6::class.java.getResource("puzzle6.txt").readText().split("\n\n")
        val sumOfDistinctCounts = input.map { getDistinctAnswers(it, "\n") }.sum()
        println(sumOfDistinctCounts)
        val sumOfAllCounts = input.map { getAllYesAnswers(it, "\n") }.sum()
        println(sumOfAllCounts)

    }
}