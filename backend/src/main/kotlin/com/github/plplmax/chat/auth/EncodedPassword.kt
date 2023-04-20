package com.github.plplmax.chat.auth

interface EncodedPassword {
    val value: String
    fun matches(password: String): Boolean
}