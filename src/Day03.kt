fun main() {

    fun part01a(input: List<String>): Int {
        // Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position
        // The epsilon rate is calculated in a similar way; the least common bit from each position is used.
        // Multiplying the gamma rate (22) by the epsilon rate (9) produces the power consumption, 198
        // What is the power consumption of the submarine?

        var sumList: List<Int> = listOf(0,0,0,0,0,0,0,0,0,0,0,0)
        var gammaBits= ""
        var epsilonBits = ""

        input.map { inputLine -> inputLine.trim().toList()
                    .map { Character.getNumericValue(it) } }
                    .forEach { sumList = it.zip(sumList) { i, j -> i + j } }

        sumList.forEach {
            if (it > input.size / 2) {
                gammaBits += "1"
                epsilonBits += "0"
            } else {
                gammaBits += "0"
                epsilonBits += "1"
            }
        }
        println("$gammaBits, ${gammaBits.toInt(2)}")
        println("$epsilonBits, ${epsilonBits.toInt(2)}")

        return epsilonBits.toInt(2) * gammaBits.toInt(2)
    }

    val testInput = readInput("Day03_test")
    check(part01a(testInput) == 198)

    val input = readInput("Day03")
    println(part01a(input))
    }