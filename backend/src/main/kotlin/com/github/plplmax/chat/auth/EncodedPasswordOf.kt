package com.github.plplmax.chat.auth

/**
 * @todo #5:15m Implement encoding password and matching encoded passwords.
 * It's recommended to use some cutting-edge library for this.
 */
class EncodedPasswordOf(private val password: String) : EncodedPassword {
    override val value: String
        get() = TODO("Not yet implemented")

    override fun matches(password: String): Boolean {
        TODO("Not yet implemented")
    }
}