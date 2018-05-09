package peternogg.numbermunch

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.answer_entry.view.*
import peternogg.numbermunch.databinding.AnswerEntryBinding

class AnswerHistoryAdapter(val history: MutableList<SolvedProblem>) : RecyclerView.Adapter<AnswerHistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: AnswerEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(problem: SolvedProblem) {
            binding.problem = problem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context)
        val binding = AnswerEntryBinding.inflate(row, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(history[position])
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun add(problem: SolvedProblem) {
        history.add(problem)
        notifyDataSetChanged()
    }
}
