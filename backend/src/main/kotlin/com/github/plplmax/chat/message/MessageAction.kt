package com.github.plplmax.chat.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime
import java.time.ZoneOffset

@Serializable
sealed class MessageAction {
    @Serializable
    @SerialName("add")
    data class Add(
        private val message: String,
        @Transient
        private val dateTime: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
    ) : MessageAction() {
        suspend fun added(sender: Int, repository: MessageRepository): Message {
            return repository.createdMessage(sender, message, dateTime)
        }
    }

    @Serializable
    @SerialName("edit")
    data class Edit(private val id: Int, private val message: String) : MessageAction()

    @Serializable
    @SerialName("delete")
    data class Delete(private val id: Int) : MessageAction()

    @Serializable
    sealed class Result {
        @Serializable
        @SerialName("add")
        data class Added(private val data: Message) : Result()
    }
}
