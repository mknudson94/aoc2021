import kotlin.text.Regex

fun main() {
    data class Vent(val x1: Int, val y1:Int, val x2:Int, val y2:Int) {

        fun isStraight(): Boolean {
            return x1 == x2 || y1 == y2
        }
    }

    fun parseVents(input: List<String>): List<Vent> {
        val ptrn = Regex("^(\\d*),(\\d*) -> (\\d*),(\\d*)$")
        return input.map { line ->
            ptrn.find(line)?.groupValues?.let {
                Vent(
                    it[1].toInt(),
                    it[2].toInt(),
                    it[3].toInt(),
                    it[4].toInt()
                )
            }!!
        }
    }

    fun part1(vents: List<Vent>): Int {
        val points = HashMap<Pair<Int, Int>, Int>()

        for (vent in vents) {
            if (vent.isStraight()) {
                for (x in if (vent.x1 <= vent.x2) vent.x1..vent.x2 else vent.x1 downTo vent.x2) {
                    for (y in if (vent.y1 <= vent.y2) vent.y1..vent.y2 else vent.y1 downTo vent.y2) {
                        val curr = Pair(x, y)
                        points[curr] = points.getOrDefault(curr, 0) + 1
                    }
                }
            }
        }

        return points.count {it.value >= 2}
    }

    fun part2(vents: List<Vent>): Int {
        val points = HashMap<Pair<Int, Int>, Int>()

        for (vent in vents) {
            if (vent.isStraight()) {
                for (x in if (vent.x1 <= vent.x2) vent.x1..vent.x2 else vent.x1 downTo vent.x2) {
                    for (y in if (vent.y1 <= vent.y2) vent.y1..vent.y2 else vent.y1 downTo vent.y2) {
                        val curr = Pair(x, y)
                        points[curr] = points.getOrDefault(curr, 0) + 1
                    }
                }
            } else {
                val xRange = if (vent.x2 >= vent.x1) vent.x1..vent.x2 else vent.x1 downTo vent.x2
                val yRange = if (vent.y2 >= vent.y1) vent.y1..vent.y2 else vent.y1 downTo vent.y2
                for ((x, y) in xRange zip yRange) {
                    val curr = Pair(x, y)
                    points[curr] = points.getOrDefault(curr, 0) + 1
                }
            }
        }

        return points.count {it.value >= 2}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_example")
    println(part1(parseVents(testInput)))
    check(part1(parseVents(testInput)) == 5)
    println(part2(parseVents(testInput)))
    check(part2(parseVents(testInput)) == 12)


    val input = readInput("Day05")
    println(part1(parseVents(input)))
//    6247 wrong
    println(part2(parseVents(input)))
//    946338 too high
}
