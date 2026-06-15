package com.example.bugetbuddysa

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BadgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        val badge1 = findViewById<TextView>(R.id.badge1)
        val badge2 = findViewById<TextView>(R.id.badge2)
        val badge3 = findViewById<TextView>(R.id.badge3)

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
            val expenses = db.expenseDao().getAllExpenses()

            runOnUiThread {

                // Badge 1: Budget Master
                if (goal != null && totalSpent <= goal.maxGoal) {
                    badge1.text = "🏆 Budget Master Unlocked"
                }

                // Badge 2: Saver Badge
                if (totalSpent < 1000) {
                    badge2.text = "💰 Saver Badge Unlocked"
                }

                // Badge 3: Expense Logger
                if (expenses.size >= 5) {
                    badge3.text = "🔥 Expense Logger Unlocked"
                }
            }
        }
    }
}