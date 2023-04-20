package com.github.plplmax.chat

import com.github.plplmax.chat.db.UnicornDatabase
import com.github.plplmax.chat.plugins.configureRouting
import com.github.plplmax.chat.plugins.configureSerialization
import com.github.plplmax.chat.plugins.configureSockets
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    UnicornDatabase.init()
    configureSockets()
    configureSerialization()
    configureRouting()
}
