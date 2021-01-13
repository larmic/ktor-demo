package de.larmic.ktor.demo.model

class Task(val id: Int, val name: String, val status: TaskStatus = TaskStatus.OPEN)
enum class TaskStatus { OPEN, IN_PROGRESS, DONE }