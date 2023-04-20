package com.github.plplmax.chat.user

/**
 * @todo #5:15m Implement the user repository. Use the Exposed library.
 */
class UserRepositoryImpl : UserRepository {
    override fun userById(id: Int): User? {
        throw NotImplementedError()
    }

    override fun userByUsername(username: String): User? {
        throw NotImplementedError()
    }

    override fun createdUser(user: UnauthorizedUser): User {
        throw NotImplementedError()
    }
}