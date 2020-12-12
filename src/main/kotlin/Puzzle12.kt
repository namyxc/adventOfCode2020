import kotlin.math.absoluteValue

object Puzzle12 {



    @JvmStatic
    fun main(args: Array<String>) {
        val input = Puzzle12::class.java.getResource("puzzle12.txt").readText()
        val calculatedDistance = calculateDistance(input)
        println(calculatedDistance)
        val calculateDistanceWithWaypoint = calculateDistanceWithWaypoint(input)
        println(calculateDistanceWithWaypoint)

    }

    enum class Command {
        N, S, E, W, L, R, F
    }
    enum class Direction {
        N {
            override fun toCommand() = Command.N
        },
        S {
            override fun toCommand() = Command.S
        },
        E {
            override fun toCommand() = Command.E
        },
        W {
            override fun toCommand() = Command.W
        };

        abstract fun toCommand() : Command
    }

    class ShipData(
        private var headTo: Direction,
        var posEast: Int,
        var posNorth: Int,
        private var waypointPosEast: Int = 0,
        private var waypointPosNorth: Int = 0){

        fun getDistance() = posEast.absoluteValue + posNorth.absoluteValue

        fun move(command: Command, i: Int){
            when (command) {
                Command.N -> { posNorth += i }
                Command.S -> { posNorth -= i }
                Command.E -> { posEast += i }
                Command.W -> { posEast -= i }
                Command.L -> {
                    val cnt = (i / 90)
                    val newHeadTo = (headTo.ordinal + cnt ) % 4
                    headTo = Direction.values()[newHeadTo]
                }
                Command.R -> {
                    val cnt = (i / 90)
                    val newHeadTo = (headTo.ordinal - cnt + 4 * cnt ) % 4
                    headTo = Direction.values()[newHeadTo]
                }
                Command.F -> {
                    move(headTo.toCommand(), i)
                }
            }
        }

        fun moveWithWaypoint(command: Command, i: Int){
            when (command) {
                Command.N -> { waypointPosNorth += i }
                Command.S -> { waypointPosNorth -= i }
                Command.E -> { waypointPosEast += i }
                Command.W -> { waypointPosEast -= i }
                Command.L -> {
                    moveWithWaypoint(Command.R, -i)
                }
                Command.R -> {
                    var num = i
                    if (i < 0) num = i + 360
                    when ((num / 90)){
                        1 -> {
                            val oldWaypointPosNorth = waypointPosNorth
                            val oldWaypointPosEast = waypointPosEast
                            waypointPosNorth = -oldWaypointPosEast
                            waypointPosEast = oldWaypointPosNorth
                        }
                        2 -> {
                            val oldWaypointPosNorth = waypointPosNorth
                            val oldWaypointPosEast = waypointPosEast
                            waypointPosNorth = -oldWaypointPosNorth
                            waypointPosEast = -oldWaypointPosEast
                        }
                        3 -> {
                            val oldWaypointPosNorth = waypointPosNorth
                            val oldWaypointPosEast = waypointPosEast
                            waypointPosNorth = oldWaypointPosEast
                            waypointPosEast = -oldWaypointPosNorth
                        }
                    }
                }
                Command.F -> {
                    posNorth += i * waypointPosNorth
                    posEast += i * waypointPosEast
                }
            }
        }
    }

    fun calculateDistance(input: String): Int {
        val commands = input.split("\n")
        val shipData = ShipData(Direction.E, 0, 0)
        commands.forEach { commandString ->
            val command = commandString.first().toString()
            val number = commandString.substring(1).toInt()
            shipData.move(Command.valueOf(command), number)
        }
        return shipData.getDistance()
    }

    fun calculateDistanceWithWaypoint(input: String): Int {
        val commands = input.split("\n")
        val shipData = ShipData(Direction.E, 0, 0, 10, 1)
        commands.forEach { commandString ->
            val command = commandString.first().toString()
            val number = commandString.substring(1).toInt()
            shipData.moveWithWaypoint(Command.valueOf(command), number)
        }
        return shipData.getDistance()
    }
}