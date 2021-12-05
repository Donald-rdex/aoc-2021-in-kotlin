import kotlin.math.max

fun main() {
    // initial thoughts, create a grid of 0's, and read each line and increment the grid cell
    // if a grid cell is > 1 then count it for part 1 solution.
    // Warning what follows is not very idomatic kotlin :-/

    val gridCells = mutableMapOf<Pair<Int, Int>, Int>()
    val testInput = readInput("Day05_test")

    fillHVGridCells(testInput, gridCells)
    println(gridCells)
    check(countGridCells(gridCells, 1) == 5)
    println("Filled Test Data: ${countGridCells(gridCells, 1)}")

    gridCells.clear()
    val input = readInput("Day05")
    fillHVGridCells(input, gridCells)
    println("Filled Cells: ${countGridCells(gridCells, 1)}")

    //part02
    gridCells.clear()
    fillGridCells(testInput, gridCells)

    println("Filled Part02 Cells Test Input: $gridCells")
    println("Filled Test Cells: ${countGridCells(gridCells, 1)}")
    check(countGridCells(gridCells, 1) == 12)

    gridCells.clear()
    fillGridCells(input, gridCells)
    println("Filled Cells Part 2: ${countGridCells(gridCells, 1)}")
}

fun fillGridCells(input: List<String>, gridCells: MutableMap<Pair<Int, Int>, Int>) {
    // slope of a line: m = (y2-y1)/(x2-x1)
    // point-slope form of a line: y = mx + b

    for (line in input) {
        val (pos1, pos2) = line.split(" -> ")
        val (x1, y1) = pos1.split(",").map { it.toInt() }
        val (x2, y2) = pos2.split(",").map { it.toInt() }

        val m: Double = if (x2 - x1 != 0) ((y2 - y1) / (x2 - x1)).toDouble() else 1.0

        if (m != 0.0 && m != 1.0 && m != 0.5 && m != -1.0 && m != -0.5)
            continue
        val b = y1 - m * x1

        var currentVal: Int
        var x = x1
        var yp = (m * x + b).toInt()
        while (!(yp == y2 && x == x2)) {
            currentVal = gridCells.getOrElse(Pair(x, yp)) { 0 }
            gridCells[Pair(x, yp)] = currentVal + 1
            if (x2 > x1) {
                x++
                yp = (m * x + b).toInt()
            } else if (x2 < x1) {
                x--
                yp = (m * x + b).toInt()
            } else {
                // vertical line
                if (y2 > y1) yp++ else yp--
            }

        }
        currentVal = gridCells.getOrElse(Pair(x2, yp)) { 0 }
        gridCells[Pair(x2, yp)] = currentVal + 1
    }
}

fun fillHVGridCells(input: List<String>, gridCells: MutableMap<Pair<Int, Int>, Int>) {
    // equation for a line: y = mx + b
    // slope of a line: m = (y2-y1)/(x2-x1), or undefined (1)
    // -- subnote, I had this wrong for the past hour with the numerator and denominator swapped, brain fail
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
            var currentVal: Int
            val maxX = if (x1 > x2) x1 else x2
            val minX = if (x1 < x2) x1 else x2
            for (x in minX..maxX) {
                currentVal = gridCells.getOrElse(Pair(x, y1)) { 0 }
                gridCells[Pair(x, y1)] = currentVal + 1
            }
        }
    }
}

fun countGridCells(gridCells: MutableMap<Pair<Int, Int>, Int>, threshold: Int): Int {
    return gridCells.count { it.value > threshold }
}