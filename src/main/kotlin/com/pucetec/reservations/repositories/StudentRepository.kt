package com.pucetec.reservations.repositories

import com.pucetec.reservations.models.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long>{
    fun findByEmail(email: String): Student?
}