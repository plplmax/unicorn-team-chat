package com.github.plplmax.chat.auth

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
                // @todo #5:15m Should be returned UserIdPrincipal or something like.
            }.onFailure {
                call.respond(HttpStatusCode.Unauthorized, it.localizedMessage ?: "Unknown error")
            }
    }
}