package com.example.bugetbuddysa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Add Expense button
        val addBtn = findViewById<Button>(R.id.addExpenseBtn)
        addBtn.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // View Expenses button
        val viewBtn = findViewById<Button>(R.id.viewExpensesBtn)
        viewBtn.setOnClickListener {
            val intent = Intent(this, ViewExpensesActivity::class.java)
            startActivity(intent)
        }
    }
}