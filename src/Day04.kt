fun main() {
    // WIP - Incomplete

    val testInput = readInput("Day04_test")
    val drawNumbers = testInput[0].split(",").map { it.toInt() }

    var boards: MutableList<Board> = mutableListOf()
    var boardCnt = 0
    testInput.subList(1, testInput.size).forEach { it ->

        if (it.length > 1) {

            val boardLineML = it.trim().split("\\s+".toRegex()).map { it.toInt() }.toMutableList()

            var thisboard = boards.getOrElse(boardCnt) { Board(boardCnt, listOf()) }
            thisboard.board.last()
            /*( boards.set(
                index = boardCnt,
                element =
            ) */
        } else {
            boardCnt++
        }
    }

    println(boards)

}

data class Board(val boardId: Int = 0, val board: List<MutableList<Int>> = List(10) { MutableList(5) { 0 } })
