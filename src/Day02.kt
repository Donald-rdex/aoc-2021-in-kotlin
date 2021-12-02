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

    fun part02(input: List<String>): Int {
        // What do you get if you multiply your final horizontal position by your final depth?
        // down X increases your aim by X units.
        // up X decreases your aim by X units.
        // forward X does two things:
        //   It increases your horizontal position by X units.
        //   It increases your depth by your aim multiplied by X.

        var aim = 0
        var depth = 0
        var horizontalPosition = 0

        for (command in input) {
            val (k, v) = command.split(" ")
            when (k) {
                "up" -> aim -= v.toInt()
                "down" -> aim += v.toInt()
                "forward" -> {
                    horizontalPosition += v.toInt()
                    depth += aim * v.toInt()
                }
            }
        }
        return depth * horizontalPosition
    }

    //test input from the problem text
    val testInput = readInput("Day02_test")
    check(part01(testInput) == 150)
    check(part02(testInput) == 900)

    val day2Input = readInput("Day02")
    println(part01(day2Input))
    println(part02(day2Input))
}