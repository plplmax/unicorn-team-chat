package com.github.plplmax.chat.plugins

import com.github.plplmax.chat.auth.authRoutes
import com.github.plplmax.chat.message.messageRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        authRoutes()
        messageRoutes()
    }
}
