package com.github.plplmax.chat.user

/**
 * @todo #5:15m Implement the user repository. Use the Exposed library.
 */
class UserRepositoryImpl : UserRepository {
    override fun userById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun userByUsername(username: String): User? {
        TODO("Not yet implemented")
    }

    override fun createdUser(user: UnauthorizedUser): User {
        TODO("Not yet implemented")
    }
}