object Puzzle21 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle21::class.java.getResource("puzzle21.txt").readText()
        val allergicData = AllergicData(input)
        println(allergicData.countNonAllergicIngredients())
        println(allergicData.listIngredientsByAllergens())
    }

    class AllergicData(input: String){
        private val allergenCanBeInFoods = mutableMapOf<String, Set<String>>()
        private val allIngredients = mutableListOf<String>()

        init {
            input.split("\n").forEach { line ->
                val ingredientsAndAllergens = line
                    .replace("(", "")
                    .replace(")", "")
                    .replace(",", "")
                    .split(" contains ")
                val ingredients = ingredientsAndAllergens.first().split(" ")
                allIngredients.addAll(ingredients)
                val ingredientsSet = ingredients.toSet()
                val allergens = ingredientsAndAllergens.last().split(" ")
                allergens.forEach { allergen ->
                    if (allergenCanBeInFoods.containsKey(allergen)){
                        allergenCanBeInFoods[allergen] = allergenCanBeInFoods[allergen]!!.intersect(ingredientsSet)
                    }else{
                        allergenCanBeInFoods[allergen] = ingredientsSet
                    }
                }
            }
            while (allergenCanBeInFoods.any { it.value.size > 1 }){
                val knownAllergens = allergenCanBeInFoods.filter { it.value.size == 1 }
                val unknownAllergenKeys = allergenCanBeInFoods.filter { allergenWithFoodList ->
                    allergenWithFoodList.value.size > 1
                }.keys
                unknownAllergenKeys.forEach { unknownAllergen ->
                    knownAllergens.forEach { knownAllergen->
                        allergenCanBeInFoods[unknownAllergen] = allergenCanBeInFoods[unknownAllergen]!!.minus(knownAllergen.value.first())
                    }
                }
            }
        }

        fun countNonAllergicIngredients() = allIngredients.count { ingredient -> allergenCanBeInFoods.none { allergen -> allergen.value.contains(ingredient) } }

        fun listIngredientsByAllergens() = allergenCanBeInFoods.toList().sortedBy { (key, _) -> key }
            .joinToString(",") { it.second.first() }
    }
}