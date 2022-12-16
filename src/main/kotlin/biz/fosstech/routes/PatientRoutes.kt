package biz.fosstech.routes

import biz.fosstech.models.Patient
import biz.fosstech.models.patientStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.patientRouting() {
    route("/patient") {
        get {
            if(patientStorage.isNotEmpty()){
                call.respond(patientStorage)
            }
        }
        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "{ message: Missing id }",
                status = HttpStatusCode.BadRequest
            )
            val patient =
                patientStorage.find { it.id == id } ?: return@get call.respondText(
                    "{ message: No patient with id $id }",
                    status = HttpStatusCode.NotFound
                )
            call.respond(patient)
        }
        post {
            val patient = call.receive<Patient>()
            patientStorage.add(patient)
            call.respondText("{ message: Patient received correctly }", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (patientStorage.removeIf { it.id == id }) {
                call.respond("Discharged")
            }
        }
    }
}