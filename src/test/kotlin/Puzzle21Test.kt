import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle21Test: PuzzleTest() {
    private val input = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
            "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
            "sqjhc fvjkl (contains soy)\n" +
            "sqjhc mxmxvkd sbzzf (contains fish)"

    @Test
    fun `test count of non-allergen ingredients`(){
        val allergicData = Puzzle21.AllergicData(input)
        val expectedNonAllergenCount = 5
        val calculatedNonAllergenCount = allergicData.countNonAllergicIngredients()
        Assertions.assertEquals(
            expectedNonAllergenCount,
            calculatedNonAllergenCount
        ) { "Number of non-allergen ingredients should be $expectedNonAllergenCount"}
    }

    @Test
    fun `test sort ingredients by allergne`(){
        val allergicData = Puzzle21.AllergicData(input)
        val expectedList = "mxmxvkd,sqjhc,fvjkl"
        val calculatedList = allergicData.listIngredientsByAllergens()
        Assertions.assertEquals(
            expectedList,
            calculatedList
        ) { "Sorted ingredients should be $expectedList"}
    }

    @Test
    fun `test main function`(){
        Puzzle21.main(arrayOf())
        Assertions.assertEquals("2542\nhkflr,ctmcqjf,bfrq,srxphcm,snmxl,zvx,bd,mqvk", getConsoleOutput())
    }
}