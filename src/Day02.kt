fun main() {
    fun part01(input: List<String>): Int {
        // return the value of depth * horizontal position as dictated by the input
        var forwardMovement = 0
        var depth = 0
        val reForward = "^forward ([0-9]+)".toRegex()
        val reDown = "^down ([0-9]+)".toRegex()
        val reUp = "^up ([0-9]+)".toRegex()


        for (command in input) {
            if (command.matches(reForward))
                forwardMovement += reForward.find(command)!!.groupValues[1].toInt()
            if (command.matches(reDown)) {
                depth += reDown.find(command)!!.groupValues[1].toInt()
            }
            if (command.matches(reUp))
                depth -= reUp.find(command)!!.groupValues[1].toInt()
        }
        return depth * forwardMovement
    }

    //test input from the problem text
    val testInput = readInput("Day02_test")
    check(part01(testInput) == 150)

    val day2Input = readInput("Day02")
    println(part01(day2Input))
}