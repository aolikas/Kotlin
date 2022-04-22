package my.aolika.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import my.aolika.a7minutesworkout.databinding.ActivityExerciseBinding
import my.aolika.a7minutesworkout.model.Constants
import my.aolika.a7minutesworkout.model.ExerciseModel

class exerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExerciseList()

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()

    }

    private fun setExerciseProgressBar() {
        binding.progressBarExercise.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30 - exerciseProgress
                binding.timerExercise.text = (30 - exerciseProgress).toString()

            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList?.size!! - 1) {
                    setupRestView()
                } else{
                    Toast.makeText(this@exerciseActivity,
                    "Done",
                    Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.tvTimer.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePosition++
                setupExerciseView()
            }

        }.start()
    }

    private fun setupRestView() {
        binding.flRestView.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvUpcomingExerciseTitle.visibility = View.VISIBLE
        binding.tvUpcomingExercise.visibility = View.VISIBLE
        binding.tvExercise.visibility = View.INVISIBLE
        binding.flExerciseView.visibility = View.INVISIBLE
        binding.ivExercise.visibility = View.INVISIBLE



        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        binding.tvUpcomingExercise.text = exerciseList!![currentExercisePosition + 1].getName()

        setRestProgressBar()
    }

    private fun setupExerciseView() {
        binding.flRestView.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvUpcomingExerciseTitle.visibility = View.INVISIBLE
        binding.tvUpcomingExercise.visibility = View.INVISIBLE
        binding.tvExercise.visibility = View.VISIBLE
        binding.flExerciseView.visibility = View.VISIBLE
        binding.ivExercise.visibility = View.VISIBLE


        if(exerciseTimer!= null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding.ivExercise
            .setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExercise.text = exerciseList!![currentExercisePosition].getName()


        setExerciseProgressBar()
    }


    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        if(exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
    }
}