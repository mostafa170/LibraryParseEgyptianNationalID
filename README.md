# Library Parse Egyptian National ID
Library to parse Egyptian National id automatic from A to Z

# Enter any national id return automatic 
- birthCentury
- birthYear 
- monthOfBirth
- dayOfBirth
- birthGovernorate
- sequenceInComputer
- gender

Library support English and Arabic languages 

# Validations and checks
- return parse error in error list
- birthCentury (Vaild or not)
- birthYear (Vaild from 1800 to 2024 or not "return string error")
- monthOfBirth (Vaild from 1 to 12 or not "return string error")
- dayOfBirth (Vaild from 1 to 30 "to April , June , September and November " & or from 1 to 29 "to February " & "from 1 to 31 January , March,May,July,August, October ,December" not "return string error")
- birthGovernorate (Vaild or not "return string error")
- sequenceInComputer return 3 numbers
- gender (male or female)

# The national ID consists of the following:

|2|90|01|01|21|345|5|7

|A  |B   |C   |D   |E  | F  |G|

- A -> The century:A=1 From (1800-1899), A=2 From (1900-1999), A=3 From (2000-2099) 
- B~D (Date of birth): B -> Year of birth C -> Month of birth D -> Day of birth
- E -> Governorate code ex: {21: "Giza"}
- F -> sequenceInComputer code ex: {345}
- G -> Unique code. (Odd is male, Even is female)
- ![Alt Text](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExemdkMGh6eDBoeXRmeGQ1YnlhNWNiMDlxdHJpOWJtbDE0enNtMmFqbyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/6PB36p8m0KPmGOP7MH/giphy.gif) ![Alt Text](https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExZnZxcjVxdm0zeG92cTgwNTRhOWN6OXNqaHExOXAyaTVoajVhenkzNyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/2IEtugOhX17BQDhUFT/giphy.gif) ![Alt Text](https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExNnJ5M2FtYjExZm55NzV5dWRoZHY3MHI1YTA2dHJrdW52YmRlaG9lcCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/6LZpupCVSaX2oCQZOk/giphy.gif)

 # Usage :
 - First download library from : https://drive.google.com/file/d/16V8GYfohFTEUDNHnCQIrJ-9INGwDyPGw/view
 - add library in libs (project android studio) : https://postimg.cc/bDyKr7pN
 - add in gradle kotlin :
   ```kotlin
    implementation ("com.google.code.gson:gson:2.10.1")
   
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
 - gradle Groovy
   ```kotlin
    implementation ("com.google.code.gson:gson:2.10.1")
   
    implementation fileTree(dir: 'libs', include: ['*.aar'])

 - to parse national and using in project sample code kotlin:
   ```kotlin
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
   
  - and sample xml :
```xml
 
     <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/national_id"
            style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="15dp"
            android:hint="@string/national_id"
            android:textColorHint="@color/black"
            app:boxStrokeColor="@color/black"
            app:counterEnabled="true"
            app:counterMaxLength="14"
            app:endIconMode="clear_text"
            app:helperText="@string/required"
            app:helperTextTextColor="@color/black"
            app:layout_constraintTop_toTopOf="parent"
            app:prefixTextColor="@color/black"
            app:startIconDrawable="@drawable/baseline_branding_watermark_24">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/national_id_ed"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="numberDecimal"
                android:textColor="@color/black" />

        </com.google.android.material.textfield.TextInputLayout>

        <androidx.appcompat.widget.AppCompatButton
            android:id="@+id/parseBtn"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="40dp"
            android:background="@color/black"
            android:padding="8dp"
            android:text="@string/parse"
            android:textAllCaps="false"
            android:textColor="@color/white"
            app:layout_constraintEnd_toEndOf="@+id/national_id"
            app:layout_constraintStart_toStartOf="@+id/national_id"
            app:layout_constraintTop_toBottomOf="@+id/national_id" />


        <androidx.cardview.widget.CardView
            android:id="@+id/cardView_parse"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginHorizontal="20dp"
            android:layout_marginVertical="20dp"
            app:cardCornerRadius="10dp"
            app:cardElevation="8dp"
            app:layout_constraintTop_toBottomOf="@+id/parseBtn">
            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/constraintLayout_parse_error"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:visibility="gone"
                android:padding="5dp">

            <TextView
                android:id="@+id/tv_errors_label"
                style="@style/boldTxpurple"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                tools:text="@string/birthCentury"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
            </androidx.constraintlayout.widget.ConstraintLayout>

            <androidx.constraintlayout.widget.ConstraintLayout
                android:id="@+id/constraintLayout_parse"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:visibility="gone"
                android:padding="5dp">

                <TextView
                    android:id="@+id/tv_birthCentury_label"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_margin="10dp"
                    android:text="@string/birthCentury"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent" />

                <TextView
                    android:id="@+id/tv_birthCentury"
                    style="@style/boldTxpurple"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="5dp"
                    app:layout_constraintStart_toEndOf="@+id/tv_birthCentury_label"
                    app:layout_constraintTop_toTopOf="@+id/tv_birthCentury_label"
                    tools:text="19" />

                <TextView
                    android:id="@+id/tv_birthGovernorate_label"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dp"
                    android:text="@string/birthGovernorate"
                    app:layout_constraintStart_toStartOf="@+id/tv_birthCentury_label"
                    app:layout_constraintTop_toBottomOf="@+id/tv_birthCentury_label" />

                <TextView
                    android:id="@+id/tv_birthGovernorate"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="5dp"
                    app:layout_constraintStart_toEndOf="@+id/tv_birthGovernorate_label"
                    app:layout_constraintTop_toTopOf="@+id/tv_birthGovernorate_label"
                    tools:text="Giza" />


                <TextView
                    android:id="@+id/tv_birth_date"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dp"
                    android:drawablePadding="5dp"
                    app:drawableStartCompat="@drawable/baseline_calendar_month_24"
                    app:layout_constraintStart_toStartOf="@+id/tv_birthGovernorate_label"
                    app:layout_constraintTop_toBottomOf="@+id/tv_birthGovernorate_label"
                    tools:text="22-2-2022 13:13:00" />

                <TextView
                    android:id="@+id/tv_sequenceInComputer_label"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dp"
                    android:text="@string/sequenceInComputer"
                    app:layout_constraintStart_toStartOf="@+id/tv_birth_date"
                    app:layout_constraintTop_toBottomOf="@+id/tv_birth_date" />

                <TextView
                    android:id="@+id/tv_sequenceInComputer"
                    style="@style/boldTxpurple"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="5dp"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintStart_toEndOf="@+id/tv_sequenceInComputer_label"
                    app:layout_constraintTop_toTopOf="@+id/tv_sequenceInComputer_label"
                    tools:text="341324132" />

                <TextView
                    android:id="@+id/tv_gender_label"
                    style="@style/boldTxpurple"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginVertical="10dp"
                    android:text="@string/gender"
                    app:layout_constraintBottom_toBottomOf="parent"
                    app:layout_constraintStart_toStartOf="@+id/tv_sequenceInComputer_label"
                    app:layout_constraintTop_toBottomOf="@+id/tv_sequenceInComputer_label" />

                <TextView
                    android:id="@+id/tv_gender"
                    style="@style/boldTxpurple"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginHorizontal="5dp"
                    android:gravity="center"
                    app:layout_constraintStart_toEndOf="@+id/tv_gender_label"
                    app:layout_constraintTop_toTopOf="@+id/tv_gender_label"
                    tools:text="Failed" />

            </androidx.constraintlayout.widget.ConstraintLayout>
        </androidx.cardview.widget.CardView>
    </androidx.constraintlayout.widget.ConstraintLayout>
    
