package org.example.kmpskeleton.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.kmpskeleton.RickAppDB

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(RickAppDB.Schema, "rickapp.db")
    }
}