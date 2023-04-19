package com.github.plplmax.chat.auth

import io.ktor.server.routing.*

/**
 * @todo #5:30m Implement authentication routes. Should be ability
 * to log in with username and password. The raw password that passed
 * from a user should be hashed and compared with the hashed password
 * from the database. If the hashed passwords are equal than we should
 * provide the user some token/session identifier to authenticate the
 * following requests.
 */
fun Route.authRoutes() {
}