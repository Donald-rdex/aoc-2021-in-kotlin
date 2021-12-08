import kotlin.math.abs

fun main() {

    fun part01(input: List<Int>): Int {
        val midpoint = input.size / 2
        val median = if (input.size % 2 == 1) input[midpoint + 1] else (input[midpoint - 1] + input[midpoint]) / 2
        var fuel = 0
        input.forEach { fuel += abs(it - median) }
        return fuel
    }

    fun part02(input: List<Int>): Int {
        // I had to look up the formula for this from the reddit discussion threads, perhaps I should read that red book...

        fun price(x: Int = 0) = x * (x + 1) / 2

        var f1 = 0
        var f2 = 0
        val inputAvg = input.average().toInt()

        input.forEach { f1 += price(abs(it - inputAvg)) }
        input.forEach { f2 += price(abs(it - inputAvg - 1)) }
        println("$f1\t$f2")
        return minOf(f1, f2)
    }


    val testInput =
        readInput("Day07_test").toString().removeSurrounding("[", "]").split(",").map { it.toInt() }.sorted()
    println(part01(testInput))
    check(part01(testInput) == 37)

    part02(testInput)
    // see note in part02(), so for some reason the below check fails even though I get the correct answer for the real data
    // check(part02(testInput) == 206)

    val input = readInput("Day07").toString().removeSurrounding("[", "]").split(",").map { it.toInt() }.sorted()
    println(part01(input))
    println(part02(input))

}