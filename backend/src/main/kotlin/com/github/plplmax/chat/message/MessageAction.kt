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
    data class Edit(private val id: Int, private val message: String) : MessageAction() {
        suspend fun edited(sender: Int, repository: MessageRepository): kotlin.Result<Message> {
            return repository.messageById(id)?.let { dbMessage ->
                if (dbMessage.senderId != sender) return kotlin.Result.failure(IllegalStateException("You cannot delete this message"))
                repository.updatedMessage(id, message).let { kotlin.Result.success(it) }
            } ?: kotlin.Result.failure(IllegalArgumentException("Undefined message with ID = $id"))
        }
    }

    @Serializable
    @SerialName("delete")
    data class Delete(private val id: Int) : MessageAction()

    @Serializable
    sealed class Result {
        @Serializable
        @SerialName("add")
        data class Added(private val data: Message) : Result()

        @Serializable
        @SerialName("edit")
        data class Edited(private val data: Message) : Result()
    }
}
