package com.github.plplmax.chat.message

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.messageRoutes() {
    val repository = MessageRepositoryImpl()
    route("/message") {
        get { call.respond(repository.messages()) }
    }
}