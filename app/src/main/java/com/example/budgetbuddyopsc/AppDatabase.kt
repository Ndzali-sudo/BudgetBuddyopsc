package com.example.bugetbuddysa

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Expense::class, Goal::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun goalDao(): GoalDao
}