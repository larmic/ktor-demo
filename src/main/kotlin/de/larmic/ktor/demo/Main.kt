package de.larmic.ktor.demo

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.cio.CIO
import org.slf4j.event.Level

fun main() {
    embeddedServer(CIO, port = 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    install(CallLogging) {
        level = Level.DEBUG
    }

    routing {
        get("/api") {
            //call.application.environment.log.info("Hello from /api")
            call.respondText("THIS IS KTOR!", ContentType.Text.Html)
        }
    }
}