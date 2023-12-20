package com.parse_egyptian_national_id

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.parse.egyptian_national_id.engine.NationalIDEngine
import com.parse_egyptian_national_id.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        binding.parseBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.nationalIdEd.getText().toString())) {
                binding.nationalIdEd.setError(getString(R.string.pleaseEnterRequiredField))
            } else {
                parseNationalId(binding.nationalIdEd.text.toString())
            }
        }
    }

    private fun parseNationalId(nationalId: String) {
        val nationalIdEngine = NationalIDEngine(this)
        val processedData = nationalIdEngine.process(nationalId)
        when {
            processedData.error.isNotEmpty() -> {
                binding.constraintLayoutParse.visibility = View.GONE
                binding.constraintLayoutParseError.visibility = View.VISIBLE
                val errorMessage = processedData.error.joinToString("\n")
                binding.tvErrorsLabel.text = errorMessage
            }
            else -> {
                binding.constraintLayoutParse.visibility = View.VISIBLE
                binding.constraintLayoutParseError.visibility = View.GONE

                when (processedData.birthCentury) {
                    "18" -> {
                        binding.tvBirthCentury.text = getString(R.string.The19Century)
                    }

                    "19" -> {
                        binding.tvBirthCentury.text = getString(R.string.The20Century)
                    }

                    "20" -> {
                        binding.tvBirthCentury.text = getString(R.string.The21Century)
                    }

                    else -> {
                        binding.tvBirthCentury.text = processedData.birthCentury
                    }
                }

                binding.tvSequenceInComputer.text = processedData.sequenceInComputer
                binding.tvBirthDate.text =
                    "${processedData.birthYear} / ${processedData.monthOfBirth} / ${processedData.dayOfBirth}"
                binding.tvGender.text = processedData.gender
                binding.tvBirthGovernorate.text = processedData.birthGovernorate
            }
        }

    }
}