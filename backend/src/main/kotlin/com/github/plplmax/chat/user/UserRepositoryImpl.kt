package com.github.plplmax.chat.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class UserRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {
    override suspend fun userById(id: Int): User? {
        return newSuspendedTransaction(ioDispatcher) {
            Users.select { Users.id eq id }.singleOrNull()?.toUser()
        }
    }

    override suspend fun userByUsername(username: String): User? {
        return newSuspendedTransaction(ioDispatcher) {
            Users.select { Users.username eq username }.singleOrNull()?.toUser()
        }
    }

    override suspend fun createdUser(username: String, password: String): User {
        return newSuspendedTransaction(ioDispatcher) {
            Users.insertAndGetId {
                it[Users.username] = username
                it[Users.password] = password
            }.value
        }.let { id -> userById(id)!! }
    }
}