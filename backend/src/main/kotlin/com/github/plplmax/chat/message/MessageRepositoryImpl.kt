package com.github.plplmax.chat.message

import com.github.plplmax.chat.user.Users
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

class MessageRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MessageRepository {
    override suspend fun messageById(id: Int): Message? {
        return newSuspendedTransaction(ioDispatcher) {
            (Messages innerJoin Users)
                .select { (Messages.id eq id) and (Messages.sender eq Users.id) }
                .singleOrNull()
                ?.toMessage()
        }
    }

    override suspend fun messages(): List<Message> {
        return newSuspendedTransaction(ioDispatcher) {
            (Messages innerJoin Users).select { Messages.sender eq Users.id }
                .orderBy(Messages.date, SortOrder.DESC)
                .map(ResultRow::toMessage)
        }
    }

    override suspend fun createdMessage(sender: Int, message: String, dateTime: LocalDateTime): Message {
        return newSuspendedTransaction(ioDispatcher) {
            Messages.insertAndGetId {
                it[Messages.sender] = sender
                it[Messages.message] = message
                it[date] = dateTime
            }.value
        }.let { id -> messageById(id)!! }
    }
}