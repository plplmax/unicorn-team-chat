package com.github.plplmax.chat.plugins

import com.github.plplmax.chat.auth.jwt.JwtConfig
import com.github.plplmax.chat.user.AuthorizedUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureAuthentication() {
    authentication {
        jwt {
            verifier(JwtConfig.verifier)
            validate { credential ->
                val isAudienceValid = credential.audience.contains(JwtConfig.AUDIENCE)
                val isIssuerValid = credential.issuer == JwtConfig.ISSUER
                val userId = credential.subject?.toIntOrNull()
                if (isAudienceValid && isIssuerValid && userId != null) {
                    AuthorizedUser(userId)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}