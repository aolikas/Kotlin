package my.aolika.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.aolika.a7minutesworkout.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiactivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.bmiToolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        binding?.bmiToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}