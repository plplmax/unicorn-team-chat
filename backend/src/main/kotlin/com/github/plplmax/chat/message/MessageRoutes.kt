package com.github.plplmax.chat.message

import com.github.plplmax.chat.auth.jwt.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Collections

fun Route.messageRoutes() {
    val repository = MessageRepositoryImpl()
    val connections = Collections.synchronizedSet<Connection>(mutableSetOf())
    route("/message") {
        authenticate {
            get { call.respond(repository.messages()) }
        }
        webSocket {
            val userId: Int
            withTimeout(5000) {
                val token = incoming.receive() as Frame.Text
                val decoded = JwtConfig.verifier.verify(token.readText())
                userId = decoded.subject.toInt()
            }
            val thisConnection = Connection(userId, this)
            connections += thisConnection
            try {
                while (isActive) {
                    when (val action = receiveDeserialized<MessageAction>()) {
                        is MessageAction.Add -> {
                            val message = action.added(userId, repository)
                            val response = MessageAction.Result.Added(message)
                            connections.forEach { it.send(Json.encodeToString<MessageAction.Result>(response)) }
                        }

                        is MessageAction.Edit -> send(Frame.Text("edit"))
                        is MessageAction.Delete -> send(Frame.Text("delete"))
                    }
                }
            } catch (e: Exception) {
                currentCoroutineContext().ensureActive()
                println(e.printStackTrace())
                send("error occurred")
            } finally {
                connections -= thisConnection
            }
        }
    }
}