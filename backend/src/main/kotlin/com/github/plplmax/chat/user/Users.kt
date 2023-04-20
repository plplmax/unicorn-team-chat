package com.github.plplmax.chat.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

object Users : IntIdTable() {
    val username: Column<String> = varchar("username", 16).uniqueIndex()
    val password: Column<String> = varchar("password", 256)
}

fun ResultRow.toUser(): User = User(
    this[Users.id].value,
    this[Users.username],
    this[Users.password],
)

