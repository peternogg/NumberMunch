package peternogg.numbermunch

import android.databinding.BaseObservable
import java.util.*

private val ops = listOf('+', '-', '*')

fun randomProblem(): Problem {
    return Problem(
            Random().nextInt(10),
            Random().nextInt(10),
            ops[Random().nextInt(ops.count())]
    )
}

class NumberMunchModel(private val history: AnswerHistoryAdapter) : BaseObservable() {
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

