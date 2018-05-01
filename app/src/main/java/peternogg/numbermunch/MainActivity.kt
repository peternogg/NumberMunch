package peternogg.numbermunch

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import peternogg.numbermunch.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private val model = ProblemModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.model = model
    }

    fun buttonClicked(v: View) {
        if (model.verifyAnswer())
            toast(R.string.correct_answer)
        else
            toast(R.string.incorrect_answer)
    }
}
