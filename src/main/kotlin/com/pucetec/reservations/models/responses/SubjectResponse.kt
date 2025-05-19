package com.pucetec.reservations.models.responses

data class SubjectResponse(
    val id: Long,
    val name: String,
    val semester: String,
    val professor: ProfessorResponse,
    val students: List<StudentResponse>
)