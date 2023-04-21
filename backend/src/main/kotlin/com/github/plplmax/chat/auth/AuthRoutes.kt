package com.github.plplmax.chat.auth

import com.github.plplmax.chat.auth.jwt.GeneratedJwtOf
import com.github.plplmax.chat.user.UnauthorizedUser
import com.github.plplmax.chat.user.UserRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {
    val authorization = AuthorizationImpl(UserRepositoryImpl())
    post("/auth") {
        call.receive<UnauthorizedUser>()
            .asAuthorized(authorization)
            .onSuccess {
                call.respond(mapOf("token" to GeneratedJwtOf("${it.id}").value))
            }.onFailure {
                call.respond(HttpStatusCode.Unauthorized, it.localizedMessage ?: "Unknown error")
            }
    }
}