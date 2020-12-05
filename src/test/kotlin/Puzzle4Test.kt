import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Puzzle4Test: PuzzleTest() {

    @Test
    fun `test passports to validity`(){
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in"

        val exctedValidCount = 2
        val passports = Passports(input)
        val validCount =  passports.validCount()
        Assertions.assertEquals(
                exctedValidCount,
                validCount
        ) { "Valid passport count $validCount should be $exctedValidCount" }
    }



    @Test
    fun `test main function`(){
        Puzzle4.main(arrayOf())
        Assertions.assertEquals("192\n101", getConsoleOutput())
    }

}