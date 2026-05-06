package com.example.bugetbuddysa

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bugetbuddysa.Expense


@Database(entities = [Expense::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
}