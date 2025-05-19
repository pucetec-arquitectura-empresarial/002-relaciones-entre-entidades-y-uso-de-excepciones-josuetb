package com.pucetec.reservations.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "students")
data class Student(
    val name: String,

    val email: String
): BaseEntity()