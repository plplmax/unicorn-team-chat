package com.github.plplmax.chat.message

import com.github.plplmax.chat.user.Users
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class MessageRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MessageRepository {
    override suspend fun messages(): List<Message> {
        return newSuspendedTransaction(ioDispatcher) {
            (Messages innerJoin Users).select { Messages.sender eq Users.id }
                .orderBy(Messages.date, SortOrder.DESC)
                .map(ResultRow::toMessage)
        }
    }
}