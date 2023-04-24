package com.github.plplmax.chat.message

import io.ktor.server.websocket.*

data class Connection(
    private val id: Int,
    private val session: WebSocketServerSession
) : WebSocketServerSession by session
