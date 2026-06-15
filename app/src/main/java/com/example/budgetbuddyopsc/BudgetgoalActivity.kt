package com.example.bugetbuddysa

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetGoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_goal)

        val minGoalEdit = findViewById<EditText>(R.id.minGoalEdit)
        val maxGoalEdit = findViewById<EditText>(R.id.maxGoalEdit)
        val saveGoalBtn = findViewById<Button>(R.id.saveGoalBtn)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "budget_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        saveGoalBtn.setOnClickListener {

            val minGoal =
                minGoalEdit.text.toString().toDoubleOrNull()

            val maxGoal =
                maxGoalEdit.text.toString().toDoubleOrNull()

            if (minGoal == null || maxGoal == null) {

                Toast.makeText(
                    this,
                    "Enter valid goals",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {

                db.goalDao().insertGoal(
                    Goal(
                        minGoal = minGoal,
                        maxGoal = maxGoal
                    )
                )

                runOnUiThread {

                    Toast.makeText(
                        this@BudgetGoalActivity,
                        "Goals Saved",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}