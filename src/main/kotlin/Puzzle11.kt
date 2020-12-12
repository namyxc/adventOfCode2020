object Puzzle11 {



    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle11::class.java.getResource("puzzle11.txt").readText()
        val calculateOccupiedSeats =  calculateOccupiedSeats(input, Puzzle11::countImmediatelyAdjacentSeats, 4)
        println(calculateOccupiedSeats)
        val calculateOccupiedSeatsVisible =  calculateOccupiedSeats(input, Puzzle11::countVisibleAdjacentSeats, 5)
        println(calculateOccupiedSeatsVisible)

    }

    fun calculateOccupiedSeats(input: String,
                               counter: (i: Int, rows: Int, j: Int, cols: Int, input: List<CharArray>) -> Int,
                               minOccupiedToLeave: Int): Int {
        var arrayWithResult = Pair(inputToArrayLists(input), true)
        while (arrayWithResult.second){
            arrayWithResult = applyRules(arrayWithResult.first, counter, minOccupiedToLeave)
        }
        return arrayWithResult.first.sumOf { line -> line.count { it == '#' } }
    }

    private fun applyRules(
        input: List<CharArray>,
        counter: (i: Int, rows: Int, j: Int, cols: Int, input: List<CharArray>) -> Int,
        minOccupiedToLeave: Int
    ): Pair<List<CharArray>, Boolean> {
        val rows = input.size
        val cols = input.first().size
        val output = List(rows) {CharArray(cols)}
        var changed = false

        for (row in 0 until rows){
            for (col in 0 until cols){
                val countOccupied = counter(row, rows, col, cols, input)
                if (input[row][col] == 'L'){
                    if (countOccupied == 0){
                        output[row][col] = '#'
                        changed = true
                    }else{
                        output[row][col] = 'L'
                    }

                }else if (input[row][col] == '#'){
                    if (countOccupied >= minOccupiedToLeave){
                        output[row][col] = 'L'
                        changed = true
                    }else{
                        output[row][col] = '#'
                    }
                }else{
                    output[row][col] = '.'
                }
            }
        }

        return Pair(output, changed)
    }

    fun countImmediatelyAdjacentSeats(i: Int, rows: Int, j: Int, cols: Int, input: List<CharArray>): Int {
        var countOccupied = 0
        for (x in -1..1)
            for (y in -1..1) {
                if ( (x != 0 || y != 0)
                    && i + x >= 0 && i + x <= rows - 1
                    && j + y >= 0 && j + y <= cols - 1
                    && input[i + x][j + y] == '#'
                ) {
                    countOccupied++
                }
            }
        return countOccupied
    }

    fun countVisibleAdjacentSeats(row: Int, rows: Int, col: Int, cols: Int, input: List<CharArray>): Int {
        var countOccupied = 0

        for (dCol in -1..1) {
            var currentCol = col + dCol
            if (currentCol in 0 until cols) {
                for (dRow in -1..1) {
                    if (dCol != 0 || dRow != 0) {
                        var currentRow = row + dRow
                        currentCol = col + dCol
                        while (currentCol in 0 until cols && currentRow in 0 until rows && input[currentRow][currentCol] == '.') {
                            currentCol += dCol
                            currentRow += dRow
                        }
                        if (currentCol in 0 until cols && currentRow in 0 until rows && input[currentRow][currentCol] == '#') {
                            countOccupied++
                        }
                    }
                }
            }
        }

        return countOccupied
    }

    private fun inputToArrayLists(input: String): List<CharArray> {
        return input.split("\n").map { it.toCharArray() }
    }
}