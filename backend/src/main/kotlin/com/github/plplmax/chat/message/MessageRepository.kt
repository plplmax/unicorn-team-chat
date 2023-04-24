package com.github.plplmax.chat.message

interface MessageRepository {
    suspend fun messages(): List<Message>
}