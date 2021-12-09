fun main() {

    fun part01(input: List<String>): Int {
        val localMins = mutableListOf<Int>()

        input.forEachIndexed { index, s ->
            s.forEachIndexed { index2, t ->
                val compareList = mutableListOf<Int>()
                val up = input.getOrElse(index - 1) { listOf<String>() }.toString().getOrElse(index2) { ' ' }
                val down = input.getOrElse(index + 1) { listOf<String>() }.toString().getOrElse(index2) { ' ' }
                val left = input.getOrElse(index) { listOf<String>() }.toString().getOrElse(index2 - 1) { ' ' }
                val right = input.getOrElse(index) { listOf<String>() }.toString().getOrElse(index2 + 1) { ' ' }

                if (up.isDigit()) compareList.add(up.digitToInt())
                if (down.isDigit()) compareList.add(down.digitToInt())
                if (left.isDigit()) compareList.add(left.digitToInt())
                if (right.isDigit()) compareList.add(right.digitToInt())
                if (compareList.all { it > t.digitToInt() }) localMins.add(t.digitToInt())
            }
        }

        return localMins.sumOf { it + 1 }
    }

    val testInput = readInput("Day09_test")
    println(part01(testInput))
    check(part01(testInput) == 15)

    val input = readInput("Day09")
    println(part01(input))
}
