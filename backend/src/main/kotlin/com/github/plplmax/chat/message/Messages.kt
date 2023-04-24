package com.github.plplmax.chat.message

import com.github.plplmax.chat.user.Users
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Messages : IntIdTable() {
    val sender: Column<Int> = integer("sender").references(Users.id)
    val message: Column<String> = text("message")
    val date: Column<LocalDateTime> = datetime("date")
}

fun ResultRow.toMessage(): Message = Message(
    this[Messages.id].value,
    this[Users.username],
    this[Messages.sender],
    this[Messages.message],
    this[Messages.date]
)
