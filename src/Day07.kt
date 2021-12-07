import kotlin.math.abs

fun main() {

    fun part01(input: List<Int>): Int {
        val midpoint = input.size / 2
        val median =
            if (input.size % 2 == 1) input[midpoint + 1] else (input[midpoint - 1] + input[midpoint]) / 2
        var fuel = 0
        input.forEach { println("$fuel: $it: ${abs(it - median)}"); fuel += abs(it - median) }
        return fuel
    }

    val testInput =
        readInput("Day07_test").toString().removeSurrounding("[", "]").split(",").map { it.toInt() }.sorted()

    println(part01(testInput))
    check(part01(testInput) == 37)

    val input = readInput("Day07").toString().removeSurrounding("[", "]").split(",").map { it.toInt() }.sorted()
    println(part01(input))

}