package my.aolika.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import my.aolika.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var currentVisibleView: String = "METRIC_UNIT_VIEW"
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

        makeVisibleMetricUnitsView()

        binding.rgUnits.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rb_units) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUSUnitsView()
            }
        }

        binding.btnBmiCalculate.setOnClickListener {
            calculateUnits()
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNIT_VIEW
        binding.llMetricUnits.visibility = View.VISIBLE
        binding.llUsUnits.visibility = View.INVISIBLE

        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitWeight.text!!.clear()

        binding.llBmiResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding.llMetricUnits.visibility = View.INVISIBLE
        binding.llUsUnits.visibility = View.VISIBLE

        binding.etUsUnitWeight.text!!.clear()
        binding.etUsUnitFeet.text!!.clear()
        binding.etUsUnitInch.text!!.clear()

        binding.llBmiResult.visibility = View.INVISIBLE
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

    private fun calculateUnits() {
        if (currentVisibleView == METRIC_UNIT_VIEW) {
            if (validateMetricUnits()) {
                val weightMetricValue: Float = binding.etMetricUnitWeight.text.toString().toFloat()
                val heightMetricValue: Float =
                    binding.etMetricUnitHeight.text.toString().toFloat() / 100

                val bmiMetric = weightMetricValue / (heightMetricValue * heightMetricValue)

                displayBMIResults(bmiMetric)

            } else {
                Toast.makeText(
                    this@BMIActivity,
                    "Please enter valid values",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else {
            if (validateUsUnits()) {
                val feetUsValue: String = binding.etUsUnitFeet.text.toString()
                val inchUsValue: String = binding.etUsUnitInch.text.toString()
                val weightUsValue: Float = binding.etUsUnitWeight.text.toString().toFloat()

                val heightUsValue = inchUsValue.toFloat() + feetUsValue.toFloat() * 12

                val bmiUS = 703 * (weightUsValue / (heightUsValue * heightUsValue))

                displayBMIResults(bmiUS)
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

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (binding.etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun validateUsUnits(): Boolean {
        var isValid = true

        when {
            binding.etUsUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.etUsUnitFeet.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.etUsUnitInch.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }
}