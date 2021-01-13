package de.larmic.ktor.demo.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class TaskTest {

    @Test
    internal fun `create new task`() {
        val task = Task(1, "simple task")
        assertThat(task.id).isEqualTo(1)
        assertThat(task.name).isEqualTo("simple task")
        assertThat(task.status).isEqualTo(TaskStatus.OPEN)
    }
}