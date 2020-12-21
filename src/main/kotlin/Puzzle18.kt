import java.math.BigInteger
import java.util.*

object Puzzle18 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle18::class.java.getResource("puzzle18.txt").readText().split("\n")
        val sum = input.sumOf { evalRNP(toRPN(it, Puzzle18::equalPrecedence)) }
        println(sum)
        val sumAdvanced = input.sumOf { evalRNP(toRPN(it, Puzzle18::additionBeforeMultiplicationPrecedence)) }
        println(sumAdvanced)
    }

    fun equalPrecedence(operator: String) = 1
    fun additionBeforeMultiplicationPrecedence(operator: String) = if (operator == "+")  1 else 0

    fun toRPN(input: String, precedence: (String)-> Int): List<String> {
        val output = mutableListOf<String>()
        val stack = Stack<String>()
        input.forEach { char ->
            if (char != ' ') {
                val token = char.toString()
                if (token.isOperator()) {
                    while (stack.isNotEmpty() && stack.peek().isOperator()
                        && precedence(stack.peek()) >= precedence(token)
                    ) {
                        val operator = stack.pop()
                        output.add(operator)
                    }
                    stack.push(token)
                } else if (token == "(") {
                    stack.push(token)
                } else if (token == ")") {
                    while (stack.peek() != "(") {
                        val operator = stack.pop()
                        output.add(operator)
                    }
                    if (stack.peek() == "(") stack.pop()
                } else {
                    output.add(token)
                }
            }
        }
        while (stack.isNotEmpty()){
            val operator = stack.pop()
            output.add(operator)
        }
        return output
    }

    private fun String.isOperator(): Boolean{
        return (this == "+" || this == "*")
    }

    private fun String.doCalculation(a: BigInteger, b:BigInteger)=if (this == "+") a + b else a * b

    private fun String.isNumber(): Boolean{
        return try{
            this.toInt()
            true
        }catch (e:NumberFormatException){
            false
        }
    }

    fun evalRNP(input: List<String>): BigInteger {
        val stack = Stack<BigInteger>()
        input.forEach { token ->
            if (token.isNumber()){
                stack.push(token.toBigInteger())
            }else{
                val firstOp = stack.pop()
                val secondOp = stack.pop()
                stack.push(token.doCalculation(firstOp, secondOp))
            }
        }
        return stack.pop()
    }
}