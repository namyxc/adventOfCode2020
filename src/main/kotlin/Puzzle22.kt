object Puzzle22 {


    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle22::class.java.getResource("puzzle22.txt").readText()
        val decksNormalPlay = Decks(input)
        println(decksNormalPlay.calculateWinnerScoreNormalPlay())
        val decksRecursivePlay = Decks(input)
        println(decksRecursivePlay.calculateWinnerScoreRecursivePlay())
    }

    class Decks(input: String) {
        private val deck1: MutableList<Int>
        private val deck2: MutableList<Int>

        init {
            val decks = input.split("\n\n")
            deck1 = decks.first().split("\n").drop(1).map { it.toInt() }.toMutableList()
            deck2 = decks.last().split("\n").drop(1).map { it.toInt() }.toMutableList()
        }

        fun calculateWinnerScoreNormalPlay(): Int {
            playDecksNormal()
            val winnerDeck = if (deck1.isEmpty()) deck2 else deck1
            return winnerDeck.reversed().mapIndexed { index, i -> i * (index+1) }.sum()
        }

        private fun playDecksNormal() {
            while (noEmptyDeck()){
                val card1 = deck1.first()
                deck1.removeAt(0)
                val card2 = deck2.first()
                deck2.removeAt(0)
                if (card1 > card2){
                    deck1.add(card1)
                    deck1.add(card2)
                }else{
                    deck2.add(card2)
                    deck2.add(card1)
                }
            }
        }

        fun calculateWinnerScoreRecursivePlay(): Int {
            val previousConfigs = mutableSetOf<String>()
            while (noEmptyDeck()){
                val key = deck1.joinToString(", ") + " | " + deck2.joinToString(", ")
                if (previousConfigs.contains(key)){
                    deck2.clear()
                } else {
                    previousConfigs.add(key)
                    playOneRound(deck1, deck2)
                }
            }
            val winnerDeck = if (deck1.isEmpty()) deck2 else deck1
            return winnerDeck.reversed().mapIndexed { index, i -> i * (index+1) }.sum()
        }

        private fun getWinnerRecursive(subDeck1: MutableList<Int>, subDeck2: MutableList<Int>): Int {
            val previousConfigs = mutableSetOf<String>()
            while (subDeck1.isNotEmpty() && subDeck2.isNotEmpty()) {
                val key = subDeck1.joinToString(", ") + " | " + subDeck2.joinToString(", ")
                if (previousConfigs.contains(key)) {
                    return 1
                } else {
                    previousConfigs.add(key)
                    playOneRound(subDeck1, subDeck2)
                }
            }
            return if (subDeck1.isEmpty()) 2 else 1

        }

        private fun playOneRound(subDeck1: MutableList<Int>,
                                 subDeck2: MutableList<Int>
        ) {
            val card1 = subDeck1.first()
            subDeck1.removeAt(0)
            val card2 = subDeck2.first()
            subDeck2.removeAt(0)

            val winner: Int = if (subDeck1.size >= card1 && subDeck2.size >= card2) {
                getWinnerRecursive(subDeck1.take(card1).toMutableList(), subDeck2.take(card2).toMutableList())
            } else {
                if (card1 > card2) 1 else 2
            }
            if (winner == 1) {
                subDeck1.add(card1)
                subDeck1.add(card2)
            } else {
                subDeck2.add(card2)
                subDeck2.add(card1)
            }
        }

        private fun noEmptyDeck() = deck1.isNotEmpty() && deck2.isNotEmpty()
    }
}