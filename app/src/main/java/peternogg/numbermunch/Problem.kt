package peternogg.numbermunch

open class Problem(val x: Int, val y: Int, val operation: Char) {
    val result: Int = when (operation) {
        '+' -> x + y
        '-' -> x - y
        '*' -> x * y
        else -> 0
    }

    open fun verifyAnswer(answer: Int): Boolean {
        return result == answer
    }

    fun questionString(): String {
        return "$x $operation $y = "
    }
}

class SolvedProblem(parent: Problem, val answer: Int) : Problem(parent.x, parent.y, parent.operation) {
    val isCorrect: Boolean = verifyAnswer(answer)

    override fun verifyAnswer(answer: Int): Boolean {
        return this.answer == result
    }
}
