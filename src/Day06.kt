fun main() {
    // AOC Day 6: counting an exponentially increasing amount of lantern fish.
    // WIP - Incomplete solution

    fun fishReproduction(fishBuckets: MutableMap<Int, Int>, days: Int) {
        var todaysFish = fishBuckets
        for (day in 0..days) {
            println("$day: ${todaysFish.values.sum()} \t$todaysFish")
            var tomorrowsFish = MutableList(size = 9) { 0 }
            /* for (x in 0 until tomorrowsFish.size) {
                if (x == 0) {
                    tomorrowsFish[5] += todaysFish.getOrDefault(x, 0)
                    tomorrowsFish[7] += todaysFish.getOrDefault(x, 0)
                } else {
                    tomorrowsFish[x - 1] += todaysFish.getOrDefault(x, 0)
                }
            }*/

            tomorrowsFish.forEachIndexed { index, _ ->
                if (index == 0) {
                    tomorrowsFish[6] += todaysFish.getOrDefault(index, 0)
                    tomorrowsFish[8] += todaysFish.getOrDefault(index, 0)
                } else {
                    tomorrowsFish[index - 1] += todaysFish.getOrDefault(index, 0)
                }
            }
            // because indexOf() returns the first value found, and there can be duplicates the below doesn't work (I think)
            // todaysFish = tomorrowsFish.associateBy { tomorrowsFish.indexOf(it) }.toMutableMap()
            tomorrowsFish.forEachIndexed() { index, i -> todaysFish[index] = i }
        }
    }

    fun fishReproductionv2(fishBuckets: MutableList<Int>, days: Int) {

        for (day in 1..days) {
            var tomorrowsFish = MutableList<Int>(9) { 0 }
            println("$day: ${fishBuckets.sum()}\t$fishBuckets")
            fishBuckets.forEachIndexed() { idx, cnt ->
                if (idx == 0) {
                    tomorrowsFish[5] += cnt
                    tomorrowsFish[7] += cnt
                } else {
                    tomorrowsFish[idx - 1] += cnt
                }
            }
            tomorrowsFish.forEachIndexed() { index, i -> fishBuckets[index] = i }
        }
    }

    val input = readInput("Day06_test").toString()
    val fishBuckets: MutableMap<Int, Int> = mutableMapOf(0 to 0)
    var currentVal: Int

    //putFishInBucket
    for (fishIdx in input.trim('[', ']').split(',')) {
        currentVal = fishBuckets.getOrDefault(fishIdx.toInt(), 0)
        fishBuckets[fishIdx.toInt()] = ++currentVal
    }

    val fb = MutableList(9) { 0 }
    for (fishIdx in input.trim('[', ']').split(',')) {
        fb[fishIdx.toInt()] += 1
    }
    fishReproductionv2(fb, 18)
    // fishReproduction(fishBuckets, 18)
    println(fb.sum())
}