package com.pucetec.reservations.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity(name = "professors")
data class Professor(
    val name: String,

    val department: String
): BaseEntity()
