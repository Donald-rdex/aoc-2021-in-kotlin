fun main() {

    fun part1(input: List<Int>): Int {
        //
        // How many measurements are larger than the previous measurement?
        //
        var count = 0
        var oldDepth = -1
        for (depth in input) {
            if (depth > oldDepth && oldDepth != -1) {
                count++
            }
            oldDepth = depth
        }
        return count
    }

    fun part2(input: List<Int>): Int {
        //
        // Your goal now is to count the number of times the sum of
        // measurements in this sliding window increases from the previous sum.
        //
        val windowDepth = mutableListOf<Int>()

        for ((index, _) in input.withIndex()) {
            windowDepth.add(
                input.getOrElse(index) { 0 }
                        + input.getOrElse(index + 1) { 0 }
                        + input.getOrElse(index + 2) { 0 }
            )
        }

        return part1(windowDepth.toList())
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))

}
