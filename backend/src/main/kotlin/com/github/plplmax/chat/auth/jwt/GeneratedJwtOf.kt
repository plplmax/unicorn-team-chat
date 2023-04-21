package com.github.plplmax.chat.auth.jwt

import com.auth0.jwt.JWT
import java.util.*

class GeneratedJwtOf(private val subject: String) : GeneratedJwt {
    override val value: String by lazy {
        JWT.create()
            .withSubject(subject)
            .withIssuer(JwtConfig.ISSUER)
            .withAudience(JwtConfig.AUDIENCE)
            .withExpiresAt(Date(System.currentTimeMillis() + JwtConfig.VALIDITY_IN_MS))
            .sign(JwtConfig.algorithm)
    }
}