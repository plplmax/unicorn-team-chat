package com.github.plplmax.chat.db

import com.github.plplmax.chat.user.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object UnicornDatabase {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:5432/unicorn"
        val database = Database.connect(jdbcURL, driverClassName, "root", "root")
        transaction(database) { SchemaUtils.create(Users) }
    }
}