import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle7Test: PuzzleTest() {

    @Test
    fun `test rule constructor 2 bags in one`(){
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags."
        val rules = Puzzle7.Rules(arrayListOf(input))
        Assertions.assertEquals(1, rules.bagRules.count())

        val ruleColor = rules.bagRules.keys.first()
        val expectedRuleColor = "light red"
        Assertions.assertEquals(
            expectedRuleColor,
            ruleColor
        ) { "Rule color shuld be $expectedRuleColor" }

        val lightRedRules = rules.bagRules[expectedRuleColor]!!
        Assertions.assertEquals(2, lightRedRules.count())
        val brightWhiteCount = lightRedRules["bright white"]
        Assertions.assertEquals(1, brightWhiteCount)
        val mutedYellowCount = lightRedRules["muted yellow"]
        Assertions.assertEquals(2, mutedYellowCount)
    }

    @Test
    fun `test rule constructor no in one`(){
        val input = "faded blue bags contain no other bags."
        val rules = Puzzle7.Rules(arrayListOf(input))
        Assertions.assertEquals(1, rules.bagRules.count())

        val ruleColor = rules.bagRules.keys.first()
        val expectedRuleColor = "faded blue"
        Assertions.assertEquals(
            expectedRuleColor,
            ruleColor
        ) { "Rule color shuld be $expectedRuleColor" }
        val fadedBlueRules = rules.bagRules[expectedRuleColor]
        Assertions.assertNull(fadedBlueRules)
    }

    @Test
    fun `test count containing colors`(){
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags."
        val rules = Puzzle7.Rules(input.split("\n"))
        val expectedCount = 4
        val count = rules.countContainingBags("shiny gold")
        Assertions.assertEquals(
            expectedCount,
            count
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun `test count inner colors`(){
        val input = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags."
        val rules = Puzzle7.Rules(input.split("\n"))
        val expectedCount = 32
        val count = rules.countAllInnerBags("shiny gold")
        Assertions.assertEquals(
            expectedCount,
            count
        ) { "Count should be $expectedCount" }
    }

    @Test
    fun `test count inner colors2`(){
        val input = "shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags."
        val rules = Puzzle7.Rules(input.split("\n"))
        val expectedCount = 126
        val count = rules.countAllInnerBags("shiny gold")
        Assertions.assertEquals(
            expectedCount,
            count
        ) { "Count should be $expectedCount" }
    }


    @Test
    fun `test main function`(){
        Puzzle7.main(arrayOf())
        Assertions.assertEquals("128\n20189", getConsoleOutput())
    }
}