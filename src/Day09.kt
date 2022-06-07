fun main() {
    // WIP - Incomplete solution

    fun part01(input: List<String>): Int {
        // for a grid of ints (input as strings) find the minimums add 1 and return the sum
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

    fun part02(input: List<String>): Int {
        // for a grid of ints, use the '9's as walls and find the number of digits in a "basin"
        // and multiply the 3 largest sized basins together and return the value

        fun getBasin(basinList: MutableList<MutableList<Pair<Int, Int>>>, point: Pair<Int, Int>): Int {
            basinList.forEachIndexed { index, basin ->
                if (point in basin) return index
            }
            return -1
        }

        val basins = mutableListOf(mutableListOf<Pair<Int, Int>>())
        // val basins: MutableList<MutableList<Pair<Int, Int>>>
        var basinCount = 0
        val secondPassQueue = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { index, s ->
            s.forEachIndexed { index2, basinNumber ->
                var localBasin = -1
                if (basinNumber.digitToInt() == 9) {
                    basinCount++
                    basins.add(mutableListOf())
                } else {
                    val up = input.getOrElse(index - 1) { listOf<String>() }.toString().getOrElse(index2) { ' ' }
                    val down = input.getOrElse(index + 1) { listOf<String>() }.toString().getOrElse(index2) { ' ' }
                    val left = input.getOrElse(index) { listOf<String>() }.toString().getOrElse(index2 - 1) { ' ' }
                    val right = input.getOrElse(index) { listOf<String>() }.toString().getOrElse(index2 + 1) { ' ' }

                    if (up.isDigit() && up.digitToInt() != 9 && localBasin == -1) {
                        localBasin = getBasin(basins, Pair(index - 1, index2))
                        if (localBasin == -1 && index > 0) secondPassQueue.add(Pair(index, index2))
                    }
                    if (down.isDigit() && down.digitToInt() != 9 && localBasin == -1) {
                        localBasin = getBasin(basins, Pair(index + 1, index2))
                        if (localBasin == -1 && index > 0) secondPassQueue.add(Pair(index, index2))
                    }
                    if (left.isDigit() && left.digitToInt() != 9 && localBasin == -1) {
                        localBasin = getBasin(basins, Pair(index, index2 - 1))
                        if (localBasin == -1 && index > 0) secondPassQueue.add(Pair(index, index2))
                    }
                    if (right.isDigit() && right.digitToInt() != 9 && localBasin == -1) {
                        localBasin = getBasin(basins, Pair(index, index2 + 1))
                        if (localBasin == -1 && index > 0) secondPassQueue.add(Pair(index, index2))
                    }

                    if (localBasin == -1) {
                        // create basin
                        // reset basinCount to account for multiple 9's in a row.
                        //if (basinCount > basins.lastIndex + 1) basinCount = basins.lastIndex + 1
                        //if (basinCount > basins.lastIndex)
                        //    basins.add(mutableListOf( Pair(index, index2)))
                        //else
                        basins[basinCount].add(Pair(index, index2))
                    } else {
                        //if (basinCount > basins.lastIndex)
                        //    basins.add(mutableListOf( Pair(index, index2)))
                        //else
                        basins[localBasin].add(Pair(index, index2))
                    }
                }
            }
        }

        while (secondPassQueue.size > 0) {
            for (point in secondPassQueue) {
                val index = point.first
                val index2 = point.second

                val up = Pair(index-1, index2)
                val down = Pair(index+1, index2)
                val left = Pair(index, index2-1)
                val right = Pair(index, index2+1)

                var localBasin = -1

                localBasin = getBasin(basins, up)
                if (localBasin != -1)
                    localBasin = getBasin(basins, down)
                if (localBasin != -1)
                    localBasin = getBasin(basins, left)
                if (localBasin != -1)
                    localBasin = getBasin(basins, right)

                if (localBasin != -1)
                    basins[localBasin].add(Pair(index, index2))
                    secondPassQueue.remove(point)
            }
        }

        for (basin in basins) println("${basin.size} \t $basin")
        return 1134
    }


    val testInput = readInput("Day09_test")
    //println(part01(testInput))
    //check(part01(testInput) == 15)
    //println(part02(testInput))
    check(part02(testInput) == 1134)

    //val input = readInput("Day09")
    //println(part01(input))
}
