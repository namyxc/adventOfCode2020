object Puzzle4 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle4::class.java.getResource("puzzle4.txt").readText()
        val passports = Passports(input)
        println(passports.validCount())

        println(passports.validAndFilledCount())
    }
}

class Passports {
    fun count(function: (Passport) -> Boolean): Int {
        return items.count { function(it) }
    }

    fun validCount() = count { it.hasAllRequiredFields() }
    fun validAndFilledCount() = count { it.hasAllRequiredFieldsFilled() }

    var items: ArrayList<Passport>

    constructor(input: String){
        items = ArrayList()
        val passportData = input.split("\n\n")
        for (data in passportData){
            var passport = Passport()
            val keyVaules = data.split("\n| ".toRegex())
            for (keyValue in keyVaules){
                val key = keyValue.split(":")[0]
                val value = keyValue.split(":")[1]
                passport.setKeyValue(key, value)
            }
            items.add(passport)
        }
    }
}

class Passport {
    val requiredKeys = arrayListOf(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
    )

    abstract class PassportData(data: String)


    class Byr(data: String): PassportData(data) {
        val value: Int = data.toInt()

        init {
            require(value in 1920..2002)
        }
    }

    class Iyr(data: String): PassportData(data) {
        val value: Int = data.toInt()

        init {
            require(value in 2010..2020)
        }
    }

    class Eyr(data: String): PassportData(data) {
        val value: Int = data.toInt()

        init {
            require(value in 2020..2030)
        }
    }

    class Hgt: PassportData {
        val value: Int
        val unit: String


        constructor (data: String) : super(data) {
            require(data.length == 5 || data.length == 4)
            val valueString = if (data.length == 5) data.substring(0,3) else data.substring(0,2)
            unit = if (data.length == 5) data.substring(3,5) else data.substring(2,4)
            require(unit == "cm" || unit == "in")
            value = valueString.toInt()
            if (unit == "cm") require(value in 150..193)
            if (unit == "in") require(value in 59..76)
        }
    }

    class Hcl(data: String): PassportData(data) {
        val value: String = data

        init {
            require("^#[a-f0-9]{6}$".toRegex().matches(value))
        }
    }

    class Ecl(data: String): PassportData(data) {
        val value: String = data

        init {
            require("^amb|blu|brn|gry|grn|hzl|oth$".toRegex().matches(value))
        }
    }

    class Pid(data: String): PassportData(data) {
        val value: String = data

        init {
            require("^[0-9]{9}$".toRegex().matches(value))
        }
    }
    class Cid(data: String): PassportData(data) {
        val value: String = data
    }


    val keyvalues = HashMap<String,String>()
    val passportData = HashMap<String, PassportData>()

    fun setKeyValue(key: String, value: String) {
        keyvalues[key] = value
        try {
            passportData[key] = when (key) {
                "byr" -> Byr(value)
                "iyr" -> Iyr(value)
                "eyr" -> Eyr(value)
                "hgt" -> Hgt(value)
                "hcl" -> Hcl(value)
                "ecl" -> Ecl(value)
                "pid" -> Pid(value)
                "cid" -> Cid(value)
                else -> throw IllegalArgumentException(key)
            }
        }catch (e: IllegalArgumentException){
            //not valid
        }
    }

    fun hasAllRequiredFields(): Boolean {
        return requiredKeys.all { keyvalues.containsKey(it) }
    }

    fun hasAllRequiredFieldsFilled(): Boolean {
        return requiredKeys.all { passportData.containsKey(it) }
    }

}
