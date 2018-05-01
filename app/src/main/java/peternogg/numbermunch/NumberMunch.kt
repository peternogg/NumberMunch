package peternogg.numbermunch

import android.databinding.BaseObservable
import java.util.*

data class Problem(val x: Int, val y: Int, val operation: Char) {
    private val result get() =
        when (operation) {
            '+' -> x + y
            '-' -> x - y
            '*' -> x * y
            else -> 0
        }

    fun verifyAnswer(answer: Int): Boolean {
        return result == answer
    }

    override fun toString(): String {
        return "$x $operation $y"
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
class ProblemModel : BaseObservable() {
    var currentProblem = randomProblem()
    var correct: Int = 0
    var incorrect: Int = 0
    var userAnswer: String = ""

    fun next() {
        currentProblem = randomProblem()
        userAnswer = ""
        notifyPropertyChanged(BR._all)
    }

    fun verifyAnswer(): Boolean {
        val correctAnswer = currentProblem.verifyAnswer(userAnswer.toInt())
        if (correctAnswer) {
            correct += 1
        } else {
            incorrect += 1
        }

        next()
        return correctAnswer
    }
}

