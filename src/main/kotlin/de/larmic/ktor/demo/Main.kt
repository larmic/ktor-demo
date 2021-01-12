package de.larmic.ktor.demo

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    val server = embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                logger.info { "localhost:8080/ is called!" }
                call.respondText("THIS IS KTOR!", ContentType.Text.Html)
            }
        }
    }
    server.start(wait = true)
}