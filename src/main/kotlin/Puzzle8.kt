object Puzzle8 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle8::class.java.getResource("puzzle8.txt").readText()
        val program = Program(input)
        println(program.calculateAccBeforeLoop())
        println(program.correctedProgramAcc())
    }

    class Program{
        private val programLines: MutableList<ProgramLine>
        private var operationAddress = 0
        private var acc = 0

        data class ProgramLine(val instruction: Instruction, val number: Int)

        enum class Instruction{
            Acc, Jmp, Nop
        }

        constructor(input: String) {
            val lines = input.split("\n")
            programLines = mutableListOf()
            lines.forEach { line ->
                val instructionAndNumber = line.split(" ")
                val instruction = Instruction.valueOf(instructionAndNumber.first().capitalize())
                val number = instructionAndNumber.last().toInt()
                val programLine = ProgramLine(instruction, number)
                programLines.add(programLine)
            }
        }

        constructor(programLines: MutableList<ProgramLine>){
            this.programLines = programLines
        }

        private fun createProgramsDiffOnlyNopOrJmp(): List<Program>{
            val programs = mutableListOf<Program>()
            programLines.forEachIndexed { index, programLine ->
                if (programLine.instruction == Instruction.Nop){
                    val newProgram = programLines.toMutableList()
                    newProgram[index] = ProgramLine(Instruction.Jmp, programLine.number)
                    programs.add(Program(newProgram))
                }else if (programLine.instruction == Instruction.Jmp){
                    val newProgram = programLines.toMutableList()
                    newProgram[index] = ProgramLine(Instruction.Nop, programLine.number)
                    programs.add(Program(newProgram))
                }
            }
            return programs
        }


        fun calculateAccBeforeLoop(): Int {
            val executedLineNumbers = mutableSetOf<Int>()
            while (!executedLineNumbers.contains(operationAddress)){
                runInstruction(executedLineNumbers)
            }
            return acc
        }

        private fun calculateAccOrReturn0OnLoop(): Int{
            val executedLineNumbers = mutableSetOf<Int>()
            while (!executedLineNumbers.contains(operationAddress) && programLines.indices.contains(operationAddress)){
                runInstruction(executedLineNumbers)
            }
            return if (operationAddress == programLines.count()) acc else 0

        }

        private fun runInstruction(executedLineNumbers: MutableSet<Int>) {
            executedLineNumbers.add(operationAddress)
            val programLine = programLines[operationAddress]
            when (programLine.instruction) {
                Instruction.Acc -> {
                    acc += programLine.number
                    operationAddress++
                }
                Instruction.Jmp -> {
                    operationAddress += programLine.number
                }
                Instruction.Nop -> {
                    operationAddress++
                }
            }
        }

        fun correctedProgramAcc() = createProgramsDiffOnlyNopOrJmp().sumBy { program -> program.calculateAccOrReturn0OnLoop() }


    }
}