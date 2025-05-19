package com.pucetec.reservations.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne

@Entity(name = "subjects")
data class Subject(
    val name: String,

    val semester: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    val professor: Professor,

    @ManyToMany
    @JoinTable(
        name = "subject_students",
        joinColumns = [JoinColumn(name = "subject_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    val students: MutableSet<Student> = mutableSetOf()
): BaseEntity()