package com.github.plplmax.chat.message

import com.github.plplmax.chat.auth.jwt.DecodedJwtOf
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout
import java.util.*

fun Route.messageRoutes() {
    val repository = MessageRepositoryImpl()
    val connections = Collections.synchronizedSet<WebSocketServerSession>(mutableSetOf())
    route("/message") {
        authenticate {
            get { call.respond(repository.messages()) }
        }
        webSocket {
            val user = withTimeout(5000) {
                val frame = incoming.receive() as Frame.Text
                DecodedJwtOf(frame.readText()).user
            }
            connections += this
            try {
                while (isActive) {
                    when (val action = receiveDeserialized<MessageAction>()) {
                        is MessageAction.Add -> {
                            val message = action.added(user.id, repository)
                            val response: MessageAction.Result = MessageAction.Result.Added(message)
                            connections.forEach { it.sendSerialized(response) }
                        }

                        is MessageAction.Edit -> {
                            action.edited(user.id, repository).onSuccess { message ->
                                val response: MessageAction.Result = MessageAction.Result.Edited(message)
                                connections.forEach { it.sendSerialized(response) }
                            }.onFailure { throw it }
                        }
                        is MessageAction.Delete -> send(Frame.Text("delete"))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connections -= this
            }
        }
    }
}