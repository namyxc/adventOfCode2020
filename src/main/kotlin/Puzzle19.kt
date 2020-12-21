object Puzzle19 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle19::class.java.getResource("puzzle19.txt").readText()
        val calculatedMatchingStringCount = countMatchingStrings(input,Puzzle19::useSameRules)
        println(calculatedMatchingStringCount)
        val calculatedMatchingStringCountWithModifiedRules = countMatchingStrings(input,Puzzle19::useModifiedRules)
        println(calculatedMatchingStringCountWithModifiedRules)
    }

    fun countMatchingStrings(input: String, modify: (Int, String) -> String): Int {
        val rulesAndStrings = input.split("\n\n")
        val rules = Rules(rulesAndStrings.first().split("\n"), modify)
        val strings = rulesAndStrings.last().split("\n")
        return strings.count { string -> rules.accept(string) }
    }

    fun useSameRules(index: Int, rules: String) = rules
    fun useModifiedRules(index: Int, rules: String) =
        when (index) {
            8 -> "42 | 42 8"
            11 -> "42 31 | 42 11 31"
            else -> rules
        }

    class Rules(ruleStrings: List<String>, modify: (Int, String) -> String) {
        private val regexp: Regex?
        private val ruleCharMap: MutableMap<Int, String>


        init {
            val ruleMap = mutableMapOf<Int, List<List<Int>>>()
            ruleCharMap = mutableMapOf()
            ruleStrings.forEach{ ruleString -> //1: 2 3 | 3 2
                val indexAndRules = ruleString.split(": ")
                val index = indexAndRules.first().toInt() //1
                val originalRules = indexAndRules.last() //2 3 | 3 2
                val rules = modify(index, originalRules)
                if (rules.startsWith("\"")){
                  ruleCharMap[index] = rules[1].toString()
                }else {
                    val rulesArray = mutableListOf<List<Int>>()
                    rules.split(" | ").forEach { r ->
                        rulesArray.add(r.split(" ").map { it.toInt() })
                    }
                    ruleMap[index] = rulesArray
                }
            }
            var couldResolveAnyRules = true
            while (!ruleCharMap.containsKey(0) && couldResolveAnyRules) {
                val resolvableRules = ruleMap.filter { rule ->
                    !ruleCharMap.containsKey(rule.key) && rule.value.all { r ->
                        r.all { r2 ->
                            ruleCharMap.containsKey(r2)
                        }
                    }
                }
                resolvableRules.forEach { resolvableRule ->
                    val key = resolvableRule.key
                    val value = resolvableRule.value
                    val resolvedValue = value.joinToString("|") { l ->
                        "(" + l.map { ruleNumber -> ruleCharMap[ruleNumber] }.joinToString("") + ")"
                    }
                    ruleCharMap[key] = "($resolvedValue)"
                }
                couldResolveAnyRules = resolvableRules.isNotEmpty()
            }
            if (ruleCharMap.containsKey(0)) {
                regexp = ("^" + ruleCharMap[0] + "$").toRegex()
            }else{
                regexp = null
            }
        }

        fun accept(string: String): Boolean {
            if (regexp != null)
                return regexp.matches(string)
            else{
                //0 = 8 11
                //8 = (42)+
                //11 = (42)(31) | 42 42 31 31
                //0 = 42{k+}31{k}
                val regexp42 = ruleCharMap[42]
                val regexp31 = ruleCharMap[31]
                var remainingString = string
                val regexp4231 = "^$regexp42(.+)$regexp31$".toRegex()
                var matchResult = regexp4231.find(remainingString)
                val regexp42CapturingGroupCount = regexp42!!.count { it == '(' } + 1
                while (
                    matchResult?.groups?.get(regexp42CapturingGroupCount) != null
                    && remainingString != matchResult.groups[regexp42CapturingGroupCount]!!.value){
                    remainingString = matchResult.groups[regexp42CapturingGroupCount]!!.value
                    matchResult = regexp4231.find(remainingString)
                }
                val regexp42Plus = "^${ruleCharMap[42]}+$".toRegex()
                return regexp42Plus.matches(remainingString) && remainingString != string
            }
        }
    }
}