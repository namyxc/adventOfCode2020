import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


internal class Puzzle2Test: PuzzleTest() {

    @ParameterizedTest(name = "test for input: \"{0}\" min {1} max {2} char {3} password {4}")
    @CsvSource(
        delimiter = ';',
        value = [
            "1-3 a: abcde; 1; 3; a; abcde",
            "1-3 b: cdefg; 1; 3; b; cdefg",
            "2-9 c: ccccccccc; 2; 9; c; ccccccccc",
        ]
    )
    fun `passwordWithRules constructor`(input: String, min: Int, max: Int, char: Char, password: String) {
        val passwordWithRules = Puzzle2.PasswordWithRules(input)
        assertEquals(min, passwordWithRules.min) {"Min (${passwordWithRules.min}) should equal $min"}
        assertEquals(max, passwordWithRules.max) {"Max (${passwordWithRules.max}) should equal $max"}
        assertEquals(char, passwordWithRules.char) {"Char (${passwordWithRules.char}) should equal $char"}
        assertEquals(password, passwordWithRules.password) {"Password (${passwordWithRules.password}) should equal $password"}
    }

    @ParameterizedTest(name = "test for input: \"{0}\" valid: {1}")
    @CsvSource(
        delimiter = ';',
        value = [
            "1-3 a: abcde; true",
            "1-3 b: cdefg; false",
            "2-9 c: ccccccccc; true",
        ]
    )
    fun `password is valid by count`(input: String, isValid: Boolean) {
        val passwordWithRules = Puzzle2.PasswordWithRules(input)
        assertEquals(isValid, passwordWithRules.isValidByCount()) {"Password (${passwordWithRules.password}) should ${if (isValid)  "" else "not" } be valid"}
    }

    @ParameterizedTest(name = "test for input: \"{0}\" valid: {1}")
    @CsvSource(
        delimiter = ';',
        value = [
            "1-3 a: abcde; true",
            "1-3 b: cdefg; false",
            "2-9 c: ccccccccc; false",
        ]
    )
    fun `password is valid by position`(input: String, isValid: Boolean) {
        val passwordWithRules = Puzzle2.PasswordWithRules(input)
        assertEquals(isValid, passwordWithRules.isValidByPosition()) {"Password (${passwordWithRules.password}) should ${if (isValid)  "" else "not" } be valid"}
    }

    @Test
    fun `test main function`(){
        Puzzle2.main(arrayOf<String>())
        assertEquals("396\n428", getConsoleOutput())
    }
}