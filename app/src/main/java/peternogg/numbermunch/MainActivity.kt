package peternogg.numbermunch

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import org.jetbrains.anko.toast
import peternogg.numbermunch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var historyAdapter: AnswerHistoryAdapter
    private lateinit var model: ProblemModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyAdapter = AnswerHistoryAdapter(mutableListOf(SolvedProblem(Problem(1, 2, '+'), 3)))
        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.EntriesList).apply {
            setHasFixedSize(true)
            adapter = historyAdapter
            layoutManager = viewManager
        }

        model = ProblemModel(historyAdapter)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = model

        findViewById<EditText>(R.id.AnswerEntry).setOnEditorActionListener(({_, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                submitAnswer()
                true
            } else
                false
        }))
    }

    private fun submitAnswer() {
        val result = model.submitAnswer()
        when(result) {
            null -> toast("Bad format!")
            true -> toast(R.string.correct_answer)
            false -> toast(R.string.incorrect_answer)
        }
    }
}
