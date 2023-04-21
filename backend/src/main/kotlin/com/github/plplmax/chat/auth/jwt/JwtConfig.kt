package com.github.plplmax.chat.auth.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

object JwtConfig {
    private const val SECRET = "secret"
    const val ISSUER = "unicorn.com"
    const val AUDIENCE = "audience"
    const val VALIDITY_IN_MS = 3_600_000L
    val algorithm: Algorithm = Algorithm.HMAC256(SECRET)
    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .acceptExpiresAt(VALIDITY_IN_MS)
        .build()
}