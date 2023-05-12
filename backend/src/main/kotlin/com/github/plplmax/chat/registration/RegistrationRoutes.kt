package com.github.plplmax.chat.registration

import com.github.plplmax.chat.user.UnregisteredUser
import com.github.plplmax.chat.user.UserRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registrationRoutes() {
    val registration = RegistrationImpl(UserRepositoryImpl())
    post("/register") {
        call.receive<UnregisteredUser>()
            .asRegistered(registration)
            .onSuccess { call.respond(HttpStatusCode.Created) }
            .onFailure { call.respond(HttpStatusCode.BadRequest, it.localizedMessage ?: "Unknown error") }
    }
}