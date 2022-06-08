// Advent of code 2021 Day 14
// WIP - Incomplete

fun main() {

    val verbose = true

    fun parseInput(polymerInput: List<String>, polymerRules: MutableMap<String, String>): String {

        for (i in 2 until polymerInput.size) {  //seems like i could replace this with a filter() and toMap() call...
            var insertString = polymerInput[i].split(" -> ", limit = 2)
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
                var pair = currentPolyString[pairIndex].toString() + currentPolyString[pairIndex + 1].toString()
                nextPolymerString += when (pairIndex) {
                    0 -> currentPolyString[pairIndex].toString() +
                            polymerRules[pair] +
                            currentPolyString[pairIndex + 1].toString()
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


    }

    val testInput = readInput("Day14_test")

    var polymerTemplate = ""
    var polymerRules = mutableMapOf<String, String>()

    polymerTemplate = parseInput(testInput, polymerRules)

    part01(polymerTemplate, polymerRules, 5)
}