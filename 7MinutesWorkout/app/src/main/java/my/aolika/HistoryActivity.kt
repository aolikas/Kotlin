package my.aolika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.aolika.a7minutesworkout.R
import my.aolika.a7minutesworkout.databinding.ActivityBmiactivityBinding
import my.aolika.a7minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistory)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }

        binding.toolbarHistory.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}