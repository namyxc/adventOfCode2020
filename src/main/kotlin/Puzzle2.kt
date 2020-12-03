object Puzzle2 {

    class PasswordWithRules(line: String) {
        val min: Int
        val max: Int
        val char: Char
        val password: String

        init {
            val splitBeforePassword = line.split(": ")
            password = splitBeforePassword[1]
            val splitBeforeChar = splitBeforePassword.first().split(" ")
            char = splitBeforeChar[1].first()
            val splitMinMax = splitBeforeChar.first().split("-")
            min = splitMinMax.first().toInt()
            max = splitMinMax[1].toInt()
        }

        fun isValidByCount(): Boolean{
            val countOfChar = password.count { it == char }
            return countOfChar in min..max
        }

        fun isValidByPosition(): Boolean {
            return password.length >= max && ((password[min-1] == char) xor (password[max-1] == char))
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle2::class.java.getResource("puzzle2.txt").readText().split('\n').filter { it.isNotEmpty() }
            .map { PasswordWithRules(it) }
        val invalidPasswordsByCount = input.count { it.isValidByCount() }
        println(invalidPasswordsByCount)
        val invalidPasswordsByPosition = input.count { it.isValidByPosition() }
        println(invalidPasswordsByPosition)
    }
}