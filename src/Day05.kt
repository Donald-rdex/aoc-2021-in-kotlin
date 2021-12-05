import kotlin.math.max

fun main() {
    // initial thoughts, create a grid of 0's, and read each line and increment the grid cell
    // if a grid cell is > 1 then count it for part 1 solution.

    val gridCells = mutableMapOf<Pair<Int, Int>, Int>()
    val testInput = readInput("Day05_test")

    fillHVGridCells(testInput, gridCells)
    println(gridCells)
    check(countHVGridCells(gridCells, 1) == 5)
    println("Filled Test Data: ${countHVGridCells(gridCells, 1)}")

    gridCells.clear()
    val input = readInput("Day05")
    fillHVGridCells(input, gridCells)
    println("Filled Cells: ${countHVGridCells(gridCells, 1)}")

}


fun fillHVGridCells(input: List<String>, gridCells: MutableMap<Pair<Int, Int>, Int>) {
    // equation for a line: y = mx + b
    // slope of a line: m = (y2-y1)/(x2-x1), or undefined (1)
    // -- subnote, I had this wrong for the past hour with the numerator and demoninator swapped, brain fail
    // however ignoring in part one since only looking for slope 0 (y2=y1) or 1 (x2=x1)

    for (line in input) {
        val (pos1, pos2) = line.split(" -> ")
        val (x1, y1) = pos1.split(",").map { it.toInt() }
        val (x2, y2) = pos2.split(",").map { it.toInt() }

        if (x1 == x2) {
            var currentVal: Int
            val maxY = if (y1 > y2) y1 else y2
            val minY = if (y1 < y2) y1 else y2
            for (y in minY..maxY) {
                currentVal = gridCells.getOrElse(Pair(x1, y)) { 0 }
                gridCells[Pair(x1, y)] = currentVal + 1
            }
        }
        if (y1 == y2) {
            var currentVal = 0
            val maxX = if (x1 > x2) x1 else x2
            val minX = if (x1 < x2) x1 else x2
            for (x in minX..maxX) {
                currentVal = gridCells.getOrElse(Pair(x, y1)) { 0 }
                gridCells[Pair(x, y1)] = currentVal + 1
            }
        }
    }
}

fun countHVGridCells(gridCells: MutableMap<Pair<Int, Int>, Int>, threshold: Int): Int {
    return gridCells.count { it.value > threshold }
}