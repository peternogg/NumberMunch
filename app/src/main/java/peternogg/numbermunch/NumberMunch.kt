package peternogg.numbermunch

import android.databinding.BaseObservable
import java.util.*

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
        return "$x $operation $ = "
    }
}

class SolvedProblem(parent: Problem, val answer: Int) : Problem(parent.x, parent.y, parent.operation) {
    val isCorrect: Boolean = verifyAnswer(answer)

    override fun verifyAnswer(answer: Int): Boolean {
        return this.answer == result
    }
}

private val ops = listOf('+', '-', '*')

fun randomProblem(): Problem {
    return Problem(
            Random().nextInt(10),
            Random().nextInt(10),
            ops[Random().nextInt(ops.count())]
    )
}

class ProblemModel(val history: AnswerHistoryAdapter) : BaseObservable() {
    var currentProblem = randomProblem()
    var correct: Int = 0
    var incorrect: Int = 0
    var userEntry: String = ""

    fun next() {
        currentProblem = randomProblem()
        userEntry = ""
        notifyPropertyChanged(BR._all)
    }

    fun submitAnswer(): Boolean? {
        val solution = userEntry.toIntOrNull() ?: return null
        val solvedProblem = SolvedProblem(currentProblem, solution)

        if (solvedProblem.isCorrect)
            correct++
        else
            incorrect++

        history.add(solvedProblem)
        next()
        return solvedProblem.isCorrect
    }
}

