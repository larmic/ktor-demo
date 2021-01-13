package de.larmic.ktor.demo

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test

internal class MainKtIT {

    @Test
    internal fun `get root`() {
        withTestApplication(Application::mainModule) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
                assertThat(response.content).isEqualTo("THIS IS KTOR!")
            }
        }
    }

    @Test
    internal fun `get all tasks`() {
        withTestApplication(Application::mainModule) {
            handleRequest(HttpMethod.Get, "/tasks").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
                assertThat(response.content).isEqualTo(
                    """
                    [ {
                      "id" : 1,
                      "name" : "initial project setup",
                      "status" : "DONE"
                    } ]
                """.trimIndent()
                )
            }
        }
    }

    @Test
    internal fun `get task 1`() {
        withTestApplication(Application::mainModule) {
            handleRequest(HttpMethod.Get, "/tasks/1").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
                assertThat(response.content).isEqualTo(
                    """
                    {
                      "id" : 1,
                      "name" : "initial project setup",
                      "status" : "DONE"
                    }
                """.trimIndent()
                )
            }
        }
    }

    @Test
    internal fun `get not existing task`() {
        withTestApplication(Application::mainModule) {
            handleRequest(HttpMethod.Get, "/tasks/2").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.NotFound)
                assertThat(response.content).isNull()
            }
        }
    }
}