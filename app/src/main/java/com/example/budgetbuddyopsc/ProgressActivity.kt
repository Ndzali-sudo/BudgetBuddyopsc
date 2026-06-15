package com.example.bugetbuddysa

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val txtGoal = findViewById<TextView>(R.id.txtGoal)
        val txtSpent = findViewById<TextView>(R.id.txtSpent)
        val txtPercentage = findViewById<TextView>(R.id.txtPercentage)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "budget_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        CoroutineScope(Dispatchers.IO).launch {

            val goal = db.goalDao().getLatestGoal()
            val totalSpent = db.expenseDao().getTotalSpent() ?: 0.0

            runOnUiThread {

                if (goal != null) {

                    txtGoal.text = "Maximum Goal: R${goal.maxGoal}"
                    txtSpent.text = "Current Spending: R$totalSpent"

                    val percentage =
                        ((totalSpent / goal.maxGoal) * 100).toInt()

                    progressBar.progress = percentage
                    txtPercentage.text = "$percentage%"

                }
            }
        }
    }
}