object Puzzle17 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle17::class.java.getResource("puzzle17.txt").readText()
        val calculatedActiveCountIn3D = calculateActiveCountIn3D(input)
        println(calculatedActiveCountIn3D)
        val calculatedActiveCountIn4D = calculateActiveCountIn4D(input)
        println(calculatedActiveCountIn4D)
    }

    fun calculateActiveCountIn3D(input: String): Int {
        var state = State3D(input)
        for (i in 1..6){
            state = state.getNextState()
        }
        return state.countActiveCells()
    }

    fun calculateActiveCountIn4D(input: String): Int {
        var state = State4D(input)
        for (i in 1..6){
            state = state.getNextState()
        }
        return state.countActiveCells()
    }

    class State3D{
        private val grid: List<List<CharArray>>

        constructor(input: String){
            val initialState = input.split("\n").map { line -> line.toCharArray() }
            grid = listOf(initialState)
        }

        constructor(grid: List<List<CharArray>>){
            this.grid = grid
        }

        private fun zDimension() = grid.size
        private fun yDimension() = grid.first().size
        private fun xDimension() = grid.first().first().size

        private fun getGridAt(z: Int, y: Int, x: Int): Char{
            return if (z in 0 until zDimension()
                && y in 0 until yDimension()
                && x in 0 until xDimension()){
                grid[z][y][x]
            }else '.'
        }

        fun getNextState(): State3D {
            val nextState = mutableListOf<List<CharArray>>()
            val nextXDimension = xDimension() + 2
            val nextYDimension = yDimension() + 2
            val nextZDimension = zDimension() + 2
            for (z in 0 until nextZDimension) {
                val rows = mutableListOf<CharArray>()
                for (y in 0 until nextYDimension) {
                    val row = CharArray(nextXDimension)
                    for (x in 0 until nextXDimension) {
                        val currentStateZ = z - 1
                        val currentStateY = y - 1
                        val currentStateX = x - 1
                        val activeNeighbourCount = activeNeighbourCount(currentStateZ, currentStateY, currentStateX)
                        row[x] =
                            if (getGridAt(currentStateZ, currentStateY, currentStateX) == '#') {
                                if (activeNeighbourCount == 2 || activeNeighbourCount == 3) '#'
                                else '.'
                            } else if (activeNeighbourCount == 3) '#'
                            else '.'
                    }
                    rows.add(row)
                }
                nextState.add(rows)
            }
            return State3D(nextState)
        }

        private fun activeNeighbourCount(z: Int, y: Int, x: Int): Int {
            var count = 0
            for (i in -1..1)
                for (j in -1..1)
                    for (k in -1..1){
                        if (
                            (i != 0 || j != 0 || k != 0)
                            && getGridAt(z+k, y+j, x+i) == '#'
                        ){
                            count++
                        }
                    }
            return count
        }

        fun countActiveCells(): Int {
            return grid.sumOf { z -> z.sumOf { y -> y.count { x -> x == '#' } }}
        }
    }

    class State4D{
        private val grid: List<List<List<CharArray>>>

        constructor(input: String){
            val initialState = input.split("\n").map { line -> line.toCharArray() }
            grid = listOf(listOf(initialState))
        }

        constructor(grid: List<List<List<CharArray>>>){
            this.grid = grid
        }

        private fun wDimension() = grid.size
        private fun zDimension() = grid.first().size
        private fun yDimension() = grid.first().first().size
        private fun xDimension() = grid.first().first().first().size

        private fun getGridAt(w: Int, z: Int, y: Int, x: Int): Char{
            return if ( w in 0 until wDimension()
                && z in 0 until zDimension()
                && y in 0 until yDimension()
                && x in 0 until xDimension()){
                grid[w][z][y][x]
            }else '.'
        }

        fun getNextState(): State4D {
            val nextState = mutableListOf<List<List<CharArray>>>()
            val nextXDimension = xDimension() + 2
            val nextYDimension = yDimension() + 2
            val nextZDimension = zDimension() + 2
            val nextWDimension = wDimension() + 2
            for (w in 0 until nextWDimension) {
                val layers = mutableListOf<List<CharArray>>()
                for (z in 0 until nextZDimension) {
                    val rows = mutableListOf<CharArray>()
                    for (y in 0 until nextYDimension) {
                        val row = CharArray(nextXDimension)
                        for (x in 0 until nextXDimension) {
                            val currentStateW = w - 1
                            val currentStateZ = z - 1
                            val currentStateY = y - 1
                            val currentStateX = x - 1
                            val activeNeighbourCount = activeNeighbourCount(currentStateW, currentStateZ, currentStateY, currentStateX)
                            row[x] =
                                if (getGridAt(currentStateW, currentStateZ, currentStateY, currentStateX) == '#') {
                                    if (activeNeighbourCount == 2 || activeNeighbourCount == 3) '#'
                                    else '.'
                                } else if (activeNeighbourCount == 3) '#'
                                else '.'
                        }
                        rows.add(row)
                    }
                    layers.add(rows)
                }
                nextState.add(layers)
            }
            return State4D(nextState)
        }

        private fun activeNeighbourCount(w: Int, z: Int, y: Int, x: Int): Int {
            var count = 0
            for (i in -1..1)
                for (j in -1..1)
                    for (k in -1..1)
                        for (l in -1..1){
                            if (
                                (i != 0 || j != 0 || k != 0 || l != 0)
                                && getGridAt(w+l,z+k, y+j, x+i) == '#'
                            ){
                                count++
                            }
                    }
            return count
        }

        fun countActiveCells(): Int {
            return grid.sumOf { w -> w.sumOf { z -> z.sumOf { y -> y.count { x -> x == '#' } }}}
        }
    }
}