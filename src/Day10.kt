fun main() {

    val openingChars = mapOf(Pair('{', '}'), Pair('[', ']'), Pair('(', ')'), Pair('<', '>'))
    val closingChars = mapOf(Pair('}', '{'), Pair(']', '['), Pair(')', '('), Pair('>', '<'))

    fun scoreIncompleteQueue(inputQueue: MutableList<Char>): Long {
        val scoring: Map<Char, Int> = mapOf(Pair(')', 1), Pair(']', 2), Pair('}', 3), Pair('>', 4))
        var queueScore = 0L

        inputQueue.reversed().forEach() { inputChar ->
            queueScore = 5 * queueScore + (scoring[openingChars[inputChar]] ?: 0)
        }

        return queueScore
    }

    fun part01(input: List<String>): Pair<MutableList<Char>, MutableList<Long>> {
        val inputQueue = mutableListOf<Char>()
        val corruptQueue = mutableListOf<Char>()
        val incompleteQueueScore = mutableListOf<Long>() //needed for part 2 solution
        var isIncomplete: Boolean

        input.forEach lineLoop@{ inputLine ->
            isIncomplete = false
            inputQueue.clear()
            inputLine.forEach { inputChar ->
                if (inputChar in openingChars.keys) {
                    inputQueue.add(inputChar)
                } else {
                    if (closingChars[inputChar] == inputQueue.last()) {
                        inputQueue.removeAt(inputQueue.size - 1)
                        isIncomplete = true
                    } else {
                        corruptQueue.add(inputChar)
                        isIncomplete = false
                        return@lineLoop
                    }
                }
            }
            if (isIncomplete && inputQueue.isNotEmpty()) {
                incompleteQueueScore.add(scoreIncompleteQueue(inputQueue))
            }
        }
        return Pair(corruptQueue, incompleteQueueScore)
    }


    val testInput = readInput("Day10_test")
    val scoring: Map<Char, Int> = mapOf(Pair(')', 3), Pair(']', 57), Pair('}', 1197), Pair('>', 25137))

    val (corruptChars, incompleteScores) = part01(testInput)
    println(corruptChars.sumOf { scoring[it] ?: 0 })
    check(corruptChars.sumOf { scoring[it] ?: 0 } == 26397)
    println("Solution 2: " + incompleteScores.sorted()[incompleteScores.size / 2])
    check(incompleteScores.sorted()[incompleteScores.size / 2] == 288957L)

    val input = readInput("Day10")
    val (corruptChars2, incompleteScores2) = part01(input)
    println(corruptChars2.sumOf { scoring[it] ?: 0 })

    // mixing in solution part 2 here...
    println("Solution 2: " + incompleteScores2.sorted()[incompleteScores2.size / 2])


}
