package my.aolika.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import my.aolika.a7minutesworkout.databinding.ActivityExerciseBinding

class exerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()

    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.timer.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
                Toast.makeText(
                    this@exerciseActivity,
                    "Here now we will start the exercise",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()
    }

    private fun setupRestView() {
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
    }
}