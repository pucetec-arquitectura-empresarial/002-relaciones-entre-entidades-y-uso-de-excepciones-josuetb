package com.pucetec.reservations.services

import com.pucetec.reservations.mappers.StudentMapper
import com.pucetec.reservations.models.entities.Student
import com.pucetec.reservations.models.responses.StudentResponse
import com.pucetec.reservations.repositories.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val studentMapper: StudentMapper
) {
    fun createStudent(student: Student): StudentResponse{
        val existingStudent: Student? = studentRepository.findByEmail(student.email)
        if (existingStudent == null) {
            val savedStudent = studentRepository.save(student)
            return studentMapper.toResponse(savedStudent)
        }
        return studentMapper.toResponse(existingStudent)
    }
    fun listStudents(): List<StudentResponse>{
        val students = studentRepository.findAll()
        return students.map { studentMapper.toResponse(it) }
    }
}