package peternogg.numbermunch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView

class AnswerHistoryAdapter(val history: MutableList<SolvedProblem>) : RecyclerView.Adapter<AnswerHistoryAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val Question: TextView
        val UserAnswer: TextView
        val CorrectAnswer: TextView
        init {
            Question = view.findViewById(R.id.Question)
            UserAnswer = view.findViewById(R.id.UserAnswer)
            CorrectAnswer = view.findViewById(R.id.CorrectAnswer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.answer_entry, parent, false)

        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val problem = history[position]

        holder.Question.text = problem.questionString()
        holder.UserAnswer.text = problem.answer.toString()
        holder.CorrectAnswer.text = problem.result.toString()
        holder.CorrectAnswer.visibility = if (problem.isCorrect) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun add(problem: SolvedProblem) {
        history.add(problem)
        notifyDataSetChanged()
    }
}
