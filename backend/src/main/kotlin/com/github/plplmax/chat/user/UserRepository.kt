package com.github.plplmax.chat.user

interface UserRepository {
    fun userById(id: Int): User?
    fun userByUsername(username: String): User?
    fun createdUser(user: UnauthorizedUser): User
}