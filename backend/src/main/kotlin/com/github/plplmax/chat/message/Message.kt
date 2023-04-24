package com.github.plplmax.chat.message

import com.github.plplmax.chat.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Message(
    val id: Int,
    val sender: String,
    val senderId: Int,
    val message: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val date: LocalDateTime
)
