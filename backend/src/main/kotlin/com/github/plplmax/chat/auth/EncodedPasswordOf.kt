package com.github.plplmax.chat.auth

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory

class EncodedPasswordOf(private val password: CharArray) : EncodedPassword {
    override val value: String by lazy { argon.hash(10, 65536, 1, password) }

    override fun matches(hash: String): Boolean {
        return argon.verify(hash, password)
    }

    private companion object {
        val argon: Argon2 = Argon2Factory.create()
    }
}