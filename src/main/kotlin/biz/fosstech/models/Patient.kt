package biz.fosstech.models

import kotlinx.serialization.Serializable

@Serializable
data class Patient(val id: String, val location: String, val callStatus: String)

val patientStorage = mutableListOf<Patient>()