package com.github.plplmax.chat.user

/**
 * @todo #5:15m Implement the user repository.
 */
interface UserRepository {
    fun userById(id: Int): User
    fun userByUsername(username: String): User
    fun createdUser(user: UnauthorizedUser): User
}