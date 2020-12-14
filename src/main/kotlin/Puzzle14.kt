import java.math.BigInteger
import kotlin.math.pow

object Puzzle14 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle14::class.java.getResource("puzzle14.txt").readText()
        val program1 = Program(input)
        program1.run()
        println(program1.sumOfUsedMemoryAddresses())
        val program2 = Program(input)
        program2.runV2()
        println(program2.sumOfUsedMemoryAddresses())
    }

    class Program(input: String) {
        private val subPrograms = mutableListOf<SubProgram>()
        private val memory = HashMap<BigInteger, BigInteger>()

        private val memorySetterRegex = "^mem\\[(\\d+)\\] = (\\d+)$".toRegex()

        init {
            input.split("\n").forEach { line ->
                if (line.startsWith("mask = ")){
                    val mask = line.split(" = ")[1]
                    subPrograms.add(SubProgram(mask))
                }else{
                    val matchResult = memorySetterRegex.find(line)
                    val memAddress = matchResult?.groups?.get(1)?.value!!.toInt()
                    val memValue = matchResult.groups[2]?.value!!.toInt()
                    subPrograms.last().addStatement(memAddress, memValue)
                }
            }
        }

        class SubProgram(private val mask: String) {
            private val statements = mutableListOf<Pair<Int, Int>>()
            private val memory = HashMap<BigInteger, BigInteger>()

            fun addStatement(memAddress: Int, memValue: Int){
                statements.add(Pair(memAddress, memValue))
            }

            fun run(): HashMap<BigInteger, BigInteger>{
                statements.forEach { s ->
                    val address = s.first.toBigInteger()
                    val originalValue = s.second.toString(2).padStart(mask.length, '0')
                    val maskedValue = mutableListOf<Char>()
                    for (i in mask.length-1 downTo 0){
                        val maskValue = mask[i]
                        val value =  originalValue[i]
                        maskedValue.add(0, if (maskValue == 'X') value else maskValue)
                    }

                    memory[address] = maskedValue.joinToString("").toBigInteger(2)
                }
                return memory
            }

            fun runV2(): HashMap<BigInteger, BigInteger>{
                statements.forEach { s ->
                    val originalAddress = s.first.toString(2).padStart(mask.length, '0')
                    val value = s.second.toBigInteger()
                    val maskedAddress = mutableListOf<Char>()
                    for (i in mask.length-1 downTo 0){
                        val maskValue = mask[i]
                        val address =  originalAddress[i]
                        maskedAddress.add(0,
                            when (maskValue) {
                                '0' -> address
                                '1' -> '1'
                                else -> 'F'
                            }
                        )
                    }

                    val floatingBitCount = maskedAddress.count { it == 'F' }
                    for (i in 0..(2.toDouble().pow(floatingBitCount.toDouble()).toInt())){
                        val number = i.toString(2).padStart(floatingBitCount, '0')
                        val floatedAddress = mutableListOf<Char>()
                        var numberIndex = 0
                        for (char in maskedAddress){
                            if (char == 'F'){
                                floatedAddress.add(number[numberIndex])
                                numberIndex++
                            }else{
                                floatedAddress.add(char)
                            }
                        }
                        memory[floatedAddress.joinToString("").toBigInteger(2)] = value
                    }

                }
                return memory
            }
        }

        fun run(){
            subPrograms.forEach { subProgram ->
                val subProgramMemory = subProgram.run()
                subProgramMemory.forEach { (address, value) ->
                    memory[address] = value
                }
            }
        }

        fun runV2(){
            subPrograms.forEach { subProgram ->
                val subProgramMemory = subProgram.runV2()
                subProgramMemory.forEach { (address, value) ->
                    memory[address] = value
                }
            }
        }

        fun sumOfUsedMemoryAddresses() = memory.filter { it.value != BigInteger.ZERO }.values.sumOf { it }
    }
}