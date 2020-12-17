import java.math.BigInteger

object Puzzle16 {


    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle16::class.java.getResource("puzzle16.txt").readText()
        val ticketInfo = TicketInfo(input)
        println(ticketInfo.sumInvalidNumbers())
        println(ticketInfo.getProdOfDepartmentFields())
        //641948393 too low
    }


    class TicketInfo {
        private val rules: Rules
        private val myTicket: List<Int>
        private val otherTickets : List<List<Int>>

        constructor(input: String){
            val parts = input.split("\n\n")
            rules = Rules(parts.first().split("\n"))
            myTicket = parts[1].split("\n")[1].split(",").map(String::toInt)
            val ticketLines = parts.last().split("\n").drop(1)
            otherTickets = mutableListOf<List<Int>>()
            ticketLines.forEach { line ->
                otherTickets.add(line.split(",").map(String::toInt))
            }
        }
        fun sumInvalidNumbers(): Int{
            val invalidFields = rules.getInvalidData(otherTickets)
            return invalidFields.sum()
        }

        fun getProdOfDepartmentFields(): BigInteger{
            val validTickets = rules.getValidTickets(otherTickets)
            rules.findRuleFields(validTickets)
            return rules.getValuesForFields(myTicket, "departure").fold(BigInteger.ONE) { prod, element -> prod * element.toBigInteger() }
        }
    }


    private class Rules{

        fun getValidTickets(ticketData: List<List<Int>>): List<List<Int>> {
            return ticketData.filterNot { ticket -> ticket.any { i -> inValid(i) } }
        }

        fun findRuleFields(ticketData: List<List<Int>>){
            val maxIndex = ticketData.first().lastIndex
            for (rule in rules) {
                for (fieldNumber in 0..maxIndex) {
                    if (ticketData.none { ticket -> rule.inValid(ticket[fieldNumber]) }){
                        rule.fieldNumbers.add(fieldNumber)
                    }
                }
            }

            while (rules.any { rule -> rule.fieldNumbers.size > 1 }){
                rules.filter { rule -> rule.fieldNumbers.size == 1 }.forEach { onlyRule ->
                    val fieldNumberToRemove = onlyRule.fieldNumbers.first()
                    rules.filter { rule -> rule.fieldNumbers.size > 1 && rule.fieldNumbers.contains(fieldNumberToRemove) }.forEach { removeSameFieldRule ->
                        removeSameFieldRule.fieldNumbers.remove(fieldNumberToRemove)
                    }
                }
            }
        }

        fun getInvalidData(ticketData: List<List<Int>>): List<Int> {
            val invalidData = mutableListOf<Int>()
            ticketData.forEach { ticket ->
                ticket.forEach { value ->
                    if (inValid(value)){
                        invalidData.add(value)
                    }
                }
            }

            return invalidData
        }

        private fun inValid(value: Int): Boolean {
            return rules.all { rule -> rule.inValid(value) }
        }

        fun getValuesForFields(myTicket: List<Int>, fieldStartsWith: String): List<Int> {
            return rules.filter { rule -> rule.name.startsWith(fieldStartsWith) }.map { rule -> myTicket[rule.fieldNumbers.first()] }
        }

        private data class Rule(val name: String, val ranges: List<Pair<Int, Int>>, var fieldNumbers: MutableList<Int>) {
            fun inValid(value: Int): Boolean {
                return ranges.all { range -> value < range.first || value > range.second }
            }
        }

        private val rules: MutableList<Rule>

        constructor(lines: List<String>){
            rules = mutableListOf()
            lines.forEach { line ->
                val nameAndRules = line.split(": ")
                val name = nameAndRules.first()
                val rulesString = nameAndRules.last().split(" or ")
                val rulesArray = rulesString.map { it -> Pair(it.split("-").first().toInt(), it.split("-").last().toInt()) }
                rules.add(Rule(name, rulesArray, mutableListOf()))
            }
        }

    }
}