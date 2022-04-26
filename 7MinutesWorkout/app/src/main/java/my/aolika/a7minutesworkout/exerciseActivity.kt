package my.aolika.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import my.aolika.a7minutesworkout.adapter.ExerciseRecyclerAdapter
import my.aolika.a7minutesworkout.databinding.ActivityExerciseBinding
import my.aolika.a7minutesworkout.model.Constants
import my.aolika.a7minutesworkout.model.ExerciseModel
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class exerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null

    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseRecyclerAdapter? = null

    private var restTimerDuration: Long = 1
    private var exerciseTimerDuration: Long = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExerciseList()

        tts = TextToSpeech(this, this)

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
        setUpExerciseRecyclerAdapter()

    }

    private fun setUpExerciseRecyclerAdapter() {
        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseRecyclerAdapter(exerciseList!!)
        binding.rvExerciseStatus.adapter = exerciseAdapter
    }


    private fun setExerciseProgressBar() {
        binding.progressBarExercise.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30 - exerciseProgress
                binding.timerExercise.text = (30 - exerciseProgress).toString()

            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                } else {
                   finish()
                    val intent = Intent(this@exerciseActivity,
                        FinishActivity:: class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }

    private fun setRestProgressBar() {

        try {
            val soundURI = Uri.parse(
                "android.resource://my.aolika.a7minutesworkout/" +
                        R.raw.press_start
            )
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.progressBar.progress = restProgress

        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.progressBar.progress = 10 - restProgress
                binding.tvTimer.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
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



        if (restTimer != null) {
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


        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        binding.ivExercise
            .setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExercise.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null) {
            player!!.stop()
        }


    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Toast.makeText(
                    this@exerciseActivity,
                    "This language is not supported",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@exerciseActivity,
                    "Initialization failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}