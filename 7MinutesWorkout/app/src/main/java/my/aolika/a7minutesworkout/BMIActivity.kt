package my.aolika.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import my.aolika.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bmiToolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        binding.bmiToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnBmiCalculate.setOnClickListener {
            if (validateMetricUnits()) {
                val weightValue: Float = binding.etMetricUnitWeight.text.toString().toFloat()
                val heightValue: Float =
                    binding.etMetricUnitHeight.text.toString().toFloat() / 100

                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResults(bmi)

            } else {
                Toast.makeText(
                    this@BMIActivity,
                    "Please enter valid values",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun displayBMIResults(bmi: Float) {
        val bmiType: String
        val bmiDescription: String
        if (bmi.compareTo(15f) <= 0) {
            bmiType = "Very Severely Underweight"
            bmiDescription = "Eat more"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiType = "Severely Underweight"
            bmiDescription = "Eat more"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiType = "Underweight"
            bmiDescription = "Eat more"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiType = "Normal"
            bmiDescription = "Good shape"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiType = "Overweight"
            bmiDescription = "Workout"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiType = "Moderately Obese"
            bmiDescription = "Workout"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiType = "Severely Obese"
            bmiDescription = "Workout"
        } else {
            bmiType = "Very Severely Obese"
            bmiDescription = "Workout"
        }

        val bmiValue =
            BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.llBmiResult.visibility = View.VISIBLE
        binding.tvBmiValue.text = bmiValue
        binding.tvBmiType.text = bmiType
        binding.tvBmiDescription.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (binding.etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
}