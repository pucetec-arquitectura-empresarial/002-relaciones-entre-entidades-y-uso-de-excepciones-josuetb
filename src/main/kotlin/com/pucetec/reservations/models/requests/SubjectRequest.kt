package com.pucetec.reservations.models.requests

data class SubjectRequest(
    val name: String,
    val semester: String,
    val professorId: Long
)