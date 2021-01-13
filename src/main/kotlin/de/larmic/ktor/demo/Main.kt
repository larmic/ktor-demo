package de.larmic.ktor.demo

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import de.larmic.ktor.demo.model.Task
import de.larmic.ktor.demo.model.TaskStatus
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.slf4j.event.Level

private val task = Task(1, "initial project setup", TaskStatus.DONE)

fun main() {
    embeddedServer(
        CIO, port = 8080, module = Application::mainModule
    ).start(wait = true)
}

fun Application.mainModule() {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
        }
    }
    install(CallLogging) {
        level = Level.DEBUG
    }

    routing {
        get("/") {
            //call.application.environment.log.info("Hello from /")
            call.respondText("THIS IS KTOR!", ContentType.Text.Html)
        }
        get("/tasks") {
            call.respond(listOf(task))
        }
        get("/tasks/{id}") {
            if (call.parameters["id"] == "1") {
                call.respond(task)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

