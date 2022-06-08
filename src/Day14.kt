// Advent of code 2021 Day 14
// WIP - Incomplete

fun main() {

    val verbose = true

    fun parseInput(polymerInput: List<String>, polymerRules: MutableMap<String, String>): String {

        for (i in 2 until polymerInput.size) {  //seems like i could replace this with a filter() and toMap() call...
            val insertString = polymerInput[i].split(" -> ", limit = 2)
            polymerRules[insertString.first()] = insertString.last()
        }

        if (verbose) {
            println(polymerInput[0])
            println(polymerRules.toString())
        }

        return polymerInput[0]  // first line is the starting template string, not the best way to do this
    }


    fun part01(initialPolyTemplate: String, polymerRules: MutableMap<String, String>, steps: Int) {
        var currentStep = 0
        var currentPolyString = initialPolyTemplate

        while (currentStep < steps) {
            var nextPolymerString = ""
            var pairIndex = 0
            while (pairIndex < currentPolyString.length - 1) {
                val pair = currentPolyString[pairIndex].toString() + currentPolyString[pairIndex + 1].toString()
                nextPolymerString += when (pairIndex) {
                    0 -> currentPolyString[0].toString() +
                            polymerRules[pair] +
                            currentPolyString[1].toString()
                    else -> polymerRules[pair] +
                            currentPolyString[pairIndex + 1].toString()
                }

                pairIndex += 1
            }
            currentPolyString = nextPolymerString
            currentStep += 1
            if (verbose) {
                println("$currentStep: $currentPolyString")
            }
        }

        val charCounts = mutableMapOf<String, Int>()
        for (i in currentPolyString) {
            if (charCounts.containsKey(i.toString())) {
                charCounts[i.toString()] = charCounts[i.toString()]!! + 1
            } else {
                charCounts[i.toString()] = 1
            }
        }
        if (verbose) println(charCounts)

        var mostcommon = Pair("", 0)
        var leastcommon = Pair("", Int.MAX_VALUE)
        charCounts.forEach { (t, u) ->
            if (u > mostcommon.second) {
                mostcommon = Pair(t, u)
            }
            if (u < leastcommon.second) {
                leastcommon = Pair(t, u)
            }
        }

        println("${mostcommon.first} - ${leastcommon.first} = ${mostcommon.second - leastcommon.second}")

    }

    val testInput = readInput("Day14_test")

    var polymerTemplate: String
    val polymerRules = mutableMapOf<String, String>()

    polymerTemplate = parseInput(testInput, polymerRules)

    part01(polymerTemplate, polymerRules, 10)

    // test data passes, using real input
    polymerRules.clear()
    val realInput = readInput("Day14")
    polymerTemplate = parseInput(realInput, polymerRules)
    part01(polymerTemplate, polymerRules, 10)


}