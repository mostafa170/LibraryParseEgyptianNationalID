package com.parse.egyptian_national_id.engine

import android.content.Context
import com.google.gson.Gson
import com.parse.egyptian_national_id.R
import com.parse.egyptian_national_id.model.GovernorateModelItem
import com.parse.egyptian_national_id.model.NationalIDData
import com.parse.egyptian_national_id.model.YearOfBirth
import java.io.BufferedReader
import java.io.InputStreamReader

class NationalIDEngine(private val context: Context) {
    private val nationalIdData = NationalIDData()

    fun process(nationalID: String): NationalIDData {
        return when {
            isNationalIDOfLength(nationalID) -> {
                nationalIdData.birthCentury = getBirthCentury(nationalID)
                nationalIdData.birthYear = getBirthYear(nationalID)
                nationalIdData.monthOfBirth = getBirthMonth(nationalID)
                nationalIdData.dayOfBirth = getDayBirth(nationalID)
                nationalIdData.birthGovernorate = getBirthGovernorate(nationalID)
                nationalIdData.sequenceInComputer = getSequenceInComputer(nationalID)
                nationalIdData.gender = getGender(nationalID)
                nationalIdData
            }

            else -> {
                nationalIdData
            }
        }
    }

    private fun isNationalIDOfLength(nationalID: String): Boolean {
        return when (nationalID.length) {
            14 -> {
                true
            }

            else -> {
                nationalIdData.error.add(context.getString(R.string.national_id_less_then_14))
                false
            }
        }
    }

    private fun getBirthCentury(nationalID: String): String {
        return when {
            nationalID[0] == YearOfBirth.THE19CENTURY.value -> {
                "18"
            }

            nationalID[0] == YearOfBirth.THE20CENTURY.value -> {
                "19"
            }

            nationalID[0] == YearOfBirth.THE21CENTURY.value -> {
                "20"
            }

            else -> {
                nationalIdData.error.add(context.getString(R.string.birth_century_not_vaild))
                context.getString(R.string.birth_century_not_vaild)
            }
        }
    }

    private fun getBirthYear(nationalID: String): String {
        val year: String = nationalIdData.birthCentury + nationalID[1] + nationalID[2]
        var BirthDate = 0
        if (nationalIdData.birthCentury == context.getString(R.string.birth_century_not_vaild)) {
            return context.getString(R.string.year_birth_not_vaild)
        } else {
            BirthDate = year.toInt()
        }
        return when (BirthDate) {
            in 1800..2024 -> {
                BirthDate.toString()
            }

            else -> {
                nationalIdData.error.add(context.getString(R.string.year_birth_not_vaild))
                return context.getString(R.string.year_birth_not_vaild)
            }
        }
    }

    private fun getBirthMonth(nationalID: String): String {
        val month: String = "" + nationalID[3] + nationalID[4]
        return when (month.toInt()) {
            in 1..12 -> {
                month
            }

            else -> {
                nationalIdData.error.add(context.getString(R.string.birth_month_not_vaild))
                return context.getString(R.string.birth_month_not_vaild)
            }
        }
    }

    private fun getDayBirth(nationalID: String): String {
        val day: String = ""+nationalID[5]+nationalID[6]
        val dayBirth: Int = day.toInt()
        when (nationalIdData.monthOfBirth) {
            "01", "03", "05", "07", "08", "10", "12" -> {
                when (dayBirth) {
                    in 1..31 -> {
                        return day
                    }
                }
            }
            "04", "06", "09", "11" -> {
                when (dayBirth) {
                    in 1..30 -> {
                        return day
                    }
                }
            }
            "02" -> {
                when (dayBirth) {
                    in 1..29 -> {
                        return day
                    }
                }
            }
        }
        nationalIdData.error.add(context.getString(R.string.month_not_vaild))
        return context.getString(R.string.month_not_vaild)
    }

    private fun getBirthGovernorate(nationalID: String): String? {
        val birthGovernorate: String = "" + nationalID[7] + nationalID[8]
        val assetManager = context.assets
        val inputStream = assetManager.open("birth_governorate.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        val json = stringBuilder.toString()
        val gson = Gson()
        val governorates = gson.fromJson(json, Array<GovernorateModelItem>::class.java).toList()
        for (governorate in governorates) {
            if (governorate.id == birthGovernorate) {
                return governorate.birthGovernorate
            }
        }
        nationalIdData.error.add(context.getString(R.string.birthGovernorate_not_vaild))
        return context.getString(R.string.birthGovernorate_not_vaild)
    }

    private fun getSequenceInComputer(nationalID: String): String {
        return nationalID.substring(9, 12)
    }

    private fun getGender(nationalID: String): String {
        val gender: Int = nationalID[12].toString().toInt()
        return when {
            gender in 0..8 && gender % 2 == 0 -> {
                context.getString(R.string.female)
            }

            gender in 1..9 && gender % 2 != 0 -> {
                context.getString(R.string.male)
            }

            else -> {
                nationalIdData.error.add(context.getString(R.string.gender_not_vaild))
                context.getString(R.string.gender_not_vaild)
            }
        }
    }
}