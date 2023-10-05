package com.parse_egyptian_national_id

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
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
        val nationalIdEngine = NationalIDEngine()
        if (nationalIdEngine.process(this, nationalId).error != null) {
            Toast.makeText(
                this,
                nationalIdEngine.process(this, nationalId).error,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            binding.cardViewParse.visibility = View.VISIBLE
            if (nationalIdEngine.process(this, nationalId).birthCentury == "18"){
                binding.tvBirthCentury.text = getString(R.string.The19Century)
            }else if (nationalIdEngine.process(this, nationalId).birthCentury == "19"){
                binding.tvBirthCentury.text = getString(R.string.The20Century)
            }else if (nationalIdEngine.process(this, nationalId).birthCentury == "19"){
                binding.tvBirthCentury.text = getString(R.string.The21Century)
            }else {
                binding.tvBirthCentury.text = nationalIdEngine.process(this, nationalId).birthCentury
            }
            binding.tvSequenceInComputer.text =
                nationalIdEngine.process(this, nationalId).sequenceInComputer
            binding.tvBirthDate.text = nationalIdEngine.process(
                this,
                nationalId
            ).birthYear + " / " + nationalIdEngine.process(
                this,
                nationalId
            ).monthOfBirth + " / " + nationalIdEngine.process(this, nationalId).dayOfBirth
            binding.tvGender.text =
                nationalIdEngine.process(this, nationalId).gender
            binding.tvBirthGovernorate.text =
                nationalIdEngine.process(this, nationalId).birthGovernorate
        }

    }
}