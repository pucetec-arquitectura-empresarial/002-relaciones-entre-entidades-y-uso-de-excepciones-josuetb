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

    // Crea una nueva materia con un profesor asociado
    fun createSubject(request: SubjectRequest): SubjectResponse {
        val professor = professorRepository.findById(request.professorId)
            .orElseThrow { ProfessorNotFoundException("Profesor no encontrado") }

        val subject = Subject(
            name = request.name,
            semester = request.semester,
            professor = professor,
            students = mutableSetOf()
        )

        return subjectMapper.toResponse(subjectRepository.save(subject))
    }

    // Inscribe un estudiante en una materia específica
    fun enrollStudent(subjectId: Long, studentId: Long): SubjectResponse {
        val subject = subjectRepository.findById(subjectId)
            .orElseThrow { SubjectNotFoundException("Asignatura no encontrada") }

        val student = studentRepository.findById(studentId)
            .orElseThrow { StudentNotFoundException("Estudiante no encontrado") }

        if (subject.students.contains(student)) {
            throw StudentAlreadyEnrolledException("El estudiante ya está inscrito en esta asignatura")
        }

        subject.students.add(student)

        return subjectMapper.toResponse(subjectRepository.save(subject))
    }

    // Devuelve todas las materias registradas
    fun listSubjects(): List<SubjectResponse> =
        subjectMapper.toResponseList(subjectRepository.findAll())

    // Elimina una materia por su identificador si existe
    fun deleteSubjectById(subjectId: Long): Boolean {
        if (!subjectRepository.existsById(subjectId)) {
            throw SubjectNotFoundException("No se puede eliminar: la asignatura no existe")
        }

        subjectRepository.deleteById(subjectId)
        return true
    }
}
