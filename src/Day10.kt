fun main() {

    fun part01(input: List<String>): MutableList<Char> {
        val openingChars = mapOf(Pair('{', '}'), Pair('[', ']'), Pair('(', ')'), Pair('<', '>'))
        val closingChars = mapOf(Pair('}', '{'), Pair(']', '['), Pair(')', '('), Pair('>', '<'))

        val inputQueue = mutableListOf<Char>()
        val corruptQueue = mutableListOf<Char>()

        input.forEach lineLoop@{ inputLine ->
            inputLine.forEach { inputChar ->
                if (inputChar in openingChars.keys) {
                    inputQueue.add(inputChar)
                } else {
                    if (closingChars[inputChar] == inputQueue.last()) {
                        inputQueue.removeAt(inputQueue.size - 1)
                    } else {
                        corruptQueue.add(inputChar)
                        return@lineLoop
                    }

                }
            }
        }
        return corruptQueue
    }

    val testInput = readInput("Day10_test")
    val scoring: Map<Char, Int> = mapOf(Pair(')', 3), Pair(']', 57), Pair('}', 1197), Pair('>', 25137))

    var corruptChars = part01(testInput)
    println(corruptChars.sumOf { scoring[it] ?: 0 })
    check(corruptChars.sumOf { scoring[it] ?: 0 } == 26397)

    val input = readInput("Day10")
    corruptChars = part01(input)
    println(corruptChars.sumOf { scoring[it] ?: 0 })

}
