package com.pucetec.reservations.repositories

import com.pucetec.reservations.models.entities.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : JpaRepository<Professor, Long>{
    fun findByName (name: String): Professor?
}