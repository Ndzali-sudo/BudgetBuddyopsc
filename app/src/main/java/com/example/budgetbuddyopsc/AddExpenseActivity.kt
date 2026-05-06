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

class AddExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Initialize database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "budget_db"
        ).build()

        // Input fields
        val amount = findViewById<EditText>(R.id.amount)
        val date = findViewById<EditText>(R.id.date)
        val startTime = findViewById<EditText>(R.id.startTime)
        val endTime = findViewById<EditText>(R.id.endTime)
        val description = findViewById<EditText>(R.id.description)
        val category = findViewById<EditText>(R.id.category)

        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {

            val amountText = amount.text.toString()
            val dateText = date.text.toString()
            val startText = startTime.text.toString()
            val endText = endTime.text.toString()
            val descText = description.text.toString()
            val catText = category.text.toString()

            // Validation
            if (amountText.isEmpty() || dateText.isEmpty() || catText.isEmpty()) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show()
            } else {

                val expense = Expense(
                    amount = amountText.toDouble(),
                    date = dateText,
                    startTime = startText,
                    endTime = endText,
                    description = descText,
                    category = catText
                )

                // Save to database (background thread)
                CoroutineScope(Dispatchers.IO).launch {
                    db.expenseDao().insertExpense(expense)
                }

                Toast.makeText(this, "Saved to database", Toast.LENGTH_SHORT).show()

                // Clear fields
                amount.text.clear()
                date.text.clear()
                startTime.text.clear()
                endTime.text.clear()
                description.text.clear()
                category.text.clear()
            }
        }
    }
}