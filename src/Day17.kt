import kotlin.math.abs

fun main() {
    // WIP - Incomplete Solution

    fun part01(xTargetRange: Pair<Int, Int>, yTargetRange: Pair<Int, Int>) {
        // determine starting velocity xV, yV that ends inside X/Y Range at end of each step, where yV is a maximum.
        // xV decreases by 1 until reaching 0 for each step
        // yV decreases by 1 each step -> when y crosses origin yV will be -yV

        // Since xV goes to zero, the numbers of steps will be in the XTargetRange
        val (stepsMin, stepsMax) = xTargetRange
        val yDelta = if (yTargetRange.first > yTargetRange.second) -1 else 1
        var yVMax = Int.MIN_VALUE
        for (step in stepsMin..stepsMax) {
            for (yTargetPos in yTargetRange.first..yTargetRange.second step yDelta) {
                val yV = abs(yTargetPos / step)
                if (yV > yVMax) yVMax = yV
            }
        }

        println(yVMax)
    }

    // more steps to split this up, but it is more read-able and works
    val testInput = readInput("Day17_test")
    val targetArea = testInput[0].split(":")[1]
    val (xTargetRange, yTargetRange) = targetArea.split(", ")
    val (xTargetMin, xTargetMax) = xTargetRange.trim().substring(2).split("..").map { it -> it.toInt() }
    val (yTargetMin, yTargetMax) = yTargetRange.trim().substring(2).split("..").map { it -> it.toInt() }

    // below looks like it should work, but fails with Index out of range error, and I'm not sure why.
    // val (xMin, xMax, yMin, yMax) = testInput[0].split(":")[1].split(", ").map { it -> it.trim().substring(2).split("..").map { it.toInt() } }

    println("X Target Range: $xTargetRange, Y Target Range: $yTargetRange)")
    println("$xTargetMin, $xTargetMax, $yTargetMin, $yTargetMax")
    part01(Pair(xTargetMin, xTargetMax), Pair(yTargetMin, yTargetMax) )
}