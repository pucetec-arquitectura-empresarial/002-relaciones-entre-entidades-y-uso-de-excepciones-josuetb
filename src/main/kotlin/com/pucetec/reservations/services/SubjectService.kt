package com.pucetec.reservations.services

import com.pucetec.reservations.exceptions.ProfessorNotFoundException
import com.pucetec.reservations.exceptions.StudentAlreadyEnrolledException
import com.pucetec.reservations.exceptions.StudentNotFoundException
import com.pucetec.reservations.exceptions.SubjectNotFoundException
import com.pucetec.reservations.mappers.SubjectMapper
import com.pucetec.reservations.models.entities.Subject
import com.pucetec.reservations.models.requests.SubjectRequest
import com.pucetec.reservations.models.responses.SubjectResponse
import com.pucetec.reservations.repositories.ProfessorRepository
import com.pucetec.reservations.repositories.StudentRepository
import com.pucetec.reservations.repositories.SubjectRepository
import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val professorRepository: ProfessorRepository,
    private val studentRepository: StudentRepository,
    private val subjectMapper: SubjectMapper,
) {
    fun createSubject(request: SubjectRequest): SubjectResponse {
        // TODO: Implement the logic to create a subject
        // Step 1: Find the professor by ID
        // Step 2: Create a new Subject entity
        // Step 3: Save the subject to the repository
        // Step 4: Return the created subject response
        // Note: This is a placeholder implementation
        throw NotImplementedError("Not yet implemented")
    }

    fun enrollStudent(subjectId: Long, studentId: Long): SubjectResponse {
        // TODO: Implement the logic to enroll a student in a subject
        // Step 1: Find the subject by ID
        // Step 2: Find the student by ID
        // Step 3: Check if the student is already enrolled in the subject
        // Step 4: If not, enroll the student in the subject
        // Step 5: Return the updated subject response
        // Note: This is a placeholder implementation
        throw NotImplementedError("Not yet implemented")
    }

    fun listSubjects(): List<SubjectResponse> =
        subjectMapper.toResponseList(subjectRepository.findAll())
}