package org.example.kmpskeleton.data.datasources.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.kmpskeleton.RickAppDB

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(RickAppDB.Schema, context, "rickapp")
}