fun main() {

    fun part01(coords: MutableList<Pair<Int, Int>>, fold: Pair<Char, Int>): MutableList<Pair<Int, Int>> {
        // return elements in the input after doing <fold> operation.
        val foldedInput = mutableSetOf<Pair<Int, Int>>()

        // a "fold" is a reflection around the axis defined, so i can take each point,
        // if it is on the non-folding side leave it, if it is on the folding side...
        //  for a y fold: new y = yoldsize - yrow until y == fold

        if (fold.first == 'y') {
            for (coord in coords) {
                val newYCoord: Int = if (coord.second > fold.second) {
                    val fDelta = coord.second - fold.second
                    fold.second - fDelta
                } else {
                    coord.second
                }
                foldedInput.add(Pair(coord.first, newYCoord))
            }
        } else {
            // fold.first should be X
            for (coord in coords) {
                val newXCoord: Int = if (coord.first > fold.second) {
                    val fDelta = coord.first - fold.second
                    fold.second - fDelta
                } else {
                    coord.first
                }
                foldedInput.add(Pair(newXCoord, coord.second))
            }
        }

        return foldedInput.toMutableList()
    }

    fun parseInput(input: List<String>): Pair<MutableList<Pair<Int, Int>>, MutableList<Pair<Char, Int>>> {
        val coords = mutableListOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<Char, Int>>()
        for (line in input) {
            if ("""^\d+,\d+""".toRegex().containsMatchIn(line))
                coords.add(
                    Pair(line.substringBefore(",").trim().toInt(),
                        line.substringAfter(",").trim().toInt()))
            else
                if ("""^.* [xy]=\d+""".toRegex().containsMatchIn(line))
                    folds.add(
                        Pair(line.substringAfterLast(" ").substringBefore("=").trim()[0],
                            line.substringAfterLast(" ").substringAfter("=").trim().toInt()
                        )
                    )
        }
        return Pair(coords, folds)
    }

    fun printPaper(input: MutableList<Pair<Int, Int>>) {
        val outStrings = mutableListOf<StringBuilder>()
        var maxX = 0
        var maxY = 0

        // build output strings
        for (coord in input) {
            // expand output lines with '.'s as needed.
            if (coord.first >= maxX) {
                // expand existing lines to new length
                maxX = coord.first + 1
                for ((index, line) in outStrings.withIndex()) {
                    outStrings[index].append(".".repeat(maxX - line.length))
                }
            }

            if (coord.second >= maxY) {
                // add new lines to new size.
                maxY = coord.second + 1
                while (outStrings.size < maxY) {
                    outStrings.add(StringBuilder(maxX).append(".".repeat(maxX)))
                }
            }

            outStrings[coord.second].setCharAt(coord.first, '#')
        }

        outStrings.forEach { line ->
            println(line.toString())
        }
    }

    val testInput = readInput("Day13_test")
    // val coords = mutableListOf<Pair<Int, Int>>()
    // val folds = mutableListOf<Pair<Char, Int>>()

    val (coordsTest, foldsTest) = parseInput(testInput)
    println(foldsTest)  // sanity check
    println(coordsTest)

    val testResult = part01(coordsTest, foldsTest[0])
    println(testResult)
    println(testResult.size) // test answer to part 01 of the problem
    check(testResult.size == 17)


    val input = readInput("Day13")
    val (coords, folds) = parseInput(input)
    val coordsAfterOneFold = part01(coords, folds[0])
    println(coordsAfterOneFold.size)  // final answer to part01 of the problem

    // part2 complete all folds and print.
    var coordsAfterFold = coordsAfterOneFold
    for (i in 1 until folds.size) {
        coordsAfterFold = part01(coordsAfterFold, folds[i])
    }
    printPaper(coordsAfterFold)

}
