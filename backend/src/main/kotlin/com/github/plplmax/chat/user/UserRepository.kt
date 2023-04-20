package com.github.plplmax.chat.user

interface UserRepository {
    suspend fun userById(id: Int): User?
    suspend fun userByUsername(username: String): User?
    suspend fun createdUser(username: String, password: String): User
}