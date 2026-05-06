package com.example.bugetbuddysa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "budget_db"
        ).build()

        // Load data from database
        CoroutineScope(Dispatchers.IO).launch {
            val expenses = db.expenseDao().getAllExpenses()

            // Show data on screen
            runOnUiThread {
                recyclerView.layoutManager = LinearLayoutManager(this@ViewExpensesActivity)
                recyclerView.adapter = ExpenseAdapter(expenses)
            }
        }
    }
}