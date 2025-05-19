package com.pucetec.reservations.repositories

import com.pucetec.reservations.models.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository: JpaRepository<Subject, Long>