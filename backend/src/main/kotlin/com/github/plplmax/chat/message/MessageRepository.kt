package com.github.plplmax.chat.message

import java.time.LocalDateTime

interface MessageRepository {
    suspend fun messageById(id: Int): Message?
    suspend fun messages(): List<Message>
    suspend fun createdMessage(sender: Int, message: String, dateTime: LocalDateTime): Message
    suspend fun updatedMessage(id: Int, message: String): Message
    suspend fun delete(id: Int)
}