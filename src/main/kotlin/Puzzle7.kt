object Puzzle7 {


    @JvmStatic
    fun main(args: Array<String>) {
        val rules = Rules(Puzzle7::class.java.getResource("puzzle7.txt").readText().split("\n"))
        val sumOfAvailableOuterColors =  rules.countContainingBags("shiny gold")
        println(sumOfAvailableOuterColors)

        val sumOfAvailableInnreColors = rules.countAllInnerBags("shiny gold")
        println(sumOfAvailableInnreColors)

    }

    class Rules {
        val bagRules = HashMap<String, HashMap<String, Int>?>()

        constructor(input: List<String>){
            input.forEach { line ->

                val bagColorRegexp = "^([^ ]+ [^ ]+) bags contain (.+)\\.$".toRegex()
                val bagListRegexp = "^(\\d+) ([^ ]+ [^ ]+) bags?$".toRegex()

                val matchResult = bagColorRegexp.find(line)
                val bagColor = matchResult?.groups?.get(1)?.value!!
                val containingString = matchResult.groups[2]?.value!!

                if (containingString == "no other bags"){
                    bagRules[bagColor] = null
                }else{
                    val bagStringList = containingString.split(", ")
                    bagRules[bagColor] = HashMap()
                    bagStringList.forEach { bagCountString ->
                        val bagMatchResult = bagListRegexp.find(bagCountString)
                        val bagCount = bagMatchResult?.groups?.get(1)?.value?.toInt()!!
                        val innerBagColor = bagMatchResult.groups[2]?.value!!
                        bagRules[bagColor]!![innerBagColor] = bagCount
                    }
                }
            }
        }

        fun countContainingBags(color: String): Int {
            val foundParents = HashSet<String>()
            var count: Int
            var addedCount: Int
            var lookFor: HashSet<String> = hashSetOf(color)
            do {
                count = foundParents.count()
                val parents = bagRules.filter { rule ->
                    if (rule.value != null)
                        lookFor.any { rule.value!!.containsKey(it) }
                    else
                        false
                }
                lookFor = parents.keys.toHashSet()
                foundParents.addAll(lookFor)
                addedCount = foundParents.count() - count
            }while (addedCount > 0)
            return foundParents.count()
        }

        fun countAllInnerBags(color: String) = countInnerBags(color) - 1

        private fun countInnerBags(color: String): Int {
            return if (bagRules[color] == null){
                1
            }else{
                var retVal = 1
                bagRules[color]!!.forEach { c, cnt ->
                    retVal += cnt * countInnerBags(c)
                }
                retVal
            }
        }

    }

}