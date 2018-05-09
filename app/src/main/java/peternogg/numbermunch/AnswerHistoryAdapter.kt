package peternogg.numbermunch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.answer_entry.view.*

class AnswerHistoryAdapter(val history: MutableList<SolvedProblem>) : RecyclerView.Adapter<AnswerHistoryAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.answer_entry, parent, false)

        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val problem = history[position]

        holder.view.Question.text = problem.questionString()
        holder.view.UserAnswer.text = problem.answer.toString()
        holder.view.CorrectAnswer.text = problem.result.toString()
        holder.view.CorrectAnswer.visibility = if (problem.isCorrect) View.INVISIBLE else View.VISIBLE
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun add(problem: SolvedProblem) {
        history.add(problem)
        notifyDataSetChanged()
    }
}
