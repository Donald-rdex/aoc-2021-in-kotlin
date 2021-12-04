fun main() {

    fun part01a(input: List<String>): Int {
        // Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position
        // The epsilon rate is calculated in a similar way; the least common bit from each position is used.
        // Multiplying the gamma rate (22) by the epsilon rate (9) produces the power consumption, 198
        // What is the power consumption of the submarine?

        var sumList: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        var gammaBits = ""
        var epsilonBits = ""

        input.map { inputLine ->
            inputLine.trim().toList().map { Character.getNumericValue(it) }
        }.forEach { sumList = it.zip(sumList) { i, j -> i + j } }

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

    fun mostCommonBit(input: List<String>, bitIndex: Int): Pair<Char, Double> {
        var sumList: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        input.map { inputLine ->
            inputLine.trim().toList().map { Character.getNumericValue(it) }
        }.forEach { sumList = it.zip(sumList) { i, j -> i + j } }

        return if (sumList[bitIndex] > input.size / 2) {
            '1' to sumList[bitIndex] / input.size.toDouble() * 100.0
        } else {
            '0' to sumList[bitIndex] / input.size.toDouble() * 100.0
        }
    }

    fun part02Scrubber(input: List<String>): Int {
        var scrubberList = input.subList(0, input.size)
        val bitSize = scrubberList[0].length
        var co2FilterBits = ""

        for (i in 0 until bitSize) {
            val (mcb, percent) = mostCommonBit(scrubberList, i)
            co2FilterBits += if (percent == 50.0) {
                '0'
            } else {
                if (mcb == '1') '0' else '1'
            }
            if (scrubberList.size > 1) scrubberList = scrubberList.filter { it.startsWith(co2FilterBits) }
        }

        val scrubberRating = scrubberList[0].toInt(2)
        println("$scrubberList: $scrubberRating")

        return scrubberRating
    }

    fun part02Oxygen(input: List<String>): Int {
        var oxygenList = input.subList(0, input.size)

        //find Oxygen rating... not the best algorithm for this, O(m*n) but it's late
        val bitSize = oxygenList[0].length
        var o2FilterBits = ""

        for (i in 0 until bitSize) {
            val (mcb, percent) = mostCommonBit(oxygenList, i)
            o2FilterBits += if (percent == 50.0) '1' else mcb
            if (oxygenList.size > 1) oxygenList = oxygenList.filter { it.startsWith(o2FilterBits) }
        }

        val oxygenRating = oxygenList[0].toInt(2)
        println("$oxygenList: $oxygenRating")
        return oxygenRating
    }

    val testInput = readInput("Day03_test")
    check(part01a(testInput) == 198)
    check(part02Scrubber(testInput) * part02Oxygen(testInput) == 230)

    val input = readInput("Day03")
    println(part01a(input))

    println(part02Scrubber(input) * part02Oxygen(input))
}
