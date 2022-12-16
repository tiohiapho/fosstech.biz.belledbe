package biz.fosstech.plugins

import biz.fosstech.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        patientRouting()
    }
}
