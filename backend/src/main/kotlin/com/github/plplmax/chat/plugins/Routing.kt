package com.github.plplmax.chat.plugins

import com.github.plplmax.chat.auth.authRoutes
import com.github.plplmax.chat.message.messageRoutes
import com.github.plplmax.chat.registration.registrationRoutes
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authenticate {
            get("/") {
                call.respondText("Hello World!")
            }
        }
        authRoutes()
        registrationRoutes()
        messageRoutes()
    }
}
