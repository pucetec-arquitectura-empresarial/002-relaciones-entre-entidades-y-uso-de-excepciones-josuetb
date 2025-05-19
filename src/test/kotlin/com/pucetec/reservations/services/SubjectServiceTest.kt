package com.pucetec.reservations.services

import com.pucetec.reservations.exceptions.ProfessorNotFoundException
import com.pucetec.reservations.exceptions.StudentAlreadyEnrolledException
import com.pucetec.reservations.exceptions.StudentNotFoundException
import com.pucetec.reservations.exceptions.SubjectNotFoundException
import com.pucetec.reservations.mappers.SubjectMapper
import com.pucetec.reservations.models.entities.Professor
import com.pucetec.reservations.models.entities.Student
import com.pucetec.reservations.models.entities.Subject
import com.pucetec.reservations.models.requests.SubjectRequest
import com.pucetec.reservations.repositories.ProfessorRepository
import com.pucetec.reservations.repositories.StudentRepository
import com.pucetec.reservations.repositories.SubjectRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*

class SubjectServiceTest {

    private lateinit var subjectRepository: SubjectRepository
    private lateinit var professorRepository: ProfessorRepository
    private lateinit var studentRepository: StudentRepository
    private lateinit var subjectMapper: SubjectMapper
    private lateinit var subjectService: SubjectService

    @BeforeEach
    fun setUp() {
        subjectRepository = mock(SubjectRepository::class.java)
        professorRepository = mock(ProfessorRepository::class.java)
        studentRepository = mock(StudentRepository::class.java)
        subjectMapper = SubjectMapper()
        subjectService = SubjectService(subjectRepository, professorRepository, studentRepository, subjectMapper)
    }

    @Test
    fun `should return correct subject name`() {
        val professor: Professor = Professor(
            name = "Jorge",
            department = "Sistemas"
        )
        val subject = Subject(name = "Arquitectura Empresarial",
            semester = "2023-2",
            professor = professor,
            students = mutableSetOf()
        )
        assertEquals("Arquitectura Empresarial", subject.name)
    }

    @Test
    fun `should return correct subject semester`() {
        val professor: Professor = Professor(
            name = "Jorge",
            department = "Sistemas"
        )
        val subject = Subject(name = "Arquitectura Empresarial",
            semester = "2023-2",
            professor = professor,
            students = mutableSetOf()
        )
        assertEquals("2023-2", subject.semester)
    }

    @Test
    fun `should return correct subject professor`() {
        val professor: Professor = Professor(
            name = "Jorge",
            department = "Sistemas"
        )
        val subject = Subject(name = "Arquitectura Empresarial",
            semester = "2023-2",
            professor = professor,
            students = mutableSetOf()
        )
        assertEquals(professor, subject.professor)
    }

    @Test
    fun `should create subject with existing professor`() {
        val professor = Professor( name = "Jorge", department = "Arquitectura Empresarial")
        val request = SubjectRequest(name = "Algorithms", semester = "2025A", professorId = 1L)
        val subject = Subject(name = request.name, semester = request.semester, professor = professor)

        `when`(professorRepository.findById(1L)).thenReturn(Optional.of(professor))
        `when`(subjectRepository.save(any(Subject::class.java))).thenReturn(subject)

        val response = subjectService.createSubject(request)

        assertEquals("Algorithms", response.name)
        assertEquals("2025A", response.semester)
        assertEquals("Jorge", response.professor.name)
    }

    @Test
    fun `should throw ProfessorNotFoundException when professor does not exist`() {
        val request = SubjectRequest(name = "Algorithms", semester = "2025A", professorId = 99L)

        `when`(professorRepository.findById(99L)).thenReturn(Optional.empty())

        assertThrows<ProfessorNotFoundException> {
            subjectService.createSubject(request)
        }
    }

    @Test
    fun `should enroll student in subject`() {
        val professor = Professor(name = "Jorge", department = "Arquitectura Empresarial")
        val subject = Subject(name = "Algorithms", semester = "2025A", professor = professor)
        val student = Student(name = "Lamine Yamal", email = "lamine.yamal@puce.edu.ec")

        `when`(subjectRepository.findById(1L)).thenReturn(Optional.of(subject))
        `when`(studentRepository.findById(1L)).thenReturn(Optional.of(student))
        `when`(subjectRepository.save(any(Subject::class.java))).thenReturn(subject)

        val response = subjectService.enrollStudent(1L, 1L)

        assertTrue(response.students.any { it.id == 0L })
    }

    @Test
    fun `should throw StudentAlreadyEnrolledException when student is already enrolled`() {
        val professor = Professor(name = "Jorge", department = "Arquitectura Empresarial")
        val student = Student(name = "Lamine Yamal", email = "lamine.yamal@puce.edu.ec")
        val subject = Subject(name = "Algorithms", semester = "2025A", professor = professor, students = mutableSetOf(student))

        `when`(subjectRepository.findById(1L)).thenReturn(Optional.of(subject))
        `when`(studentRepository.findById(1L)).thenReturn(Optional.of(student))

        assertThrows<StudentAlreadyEnrolledException> {
            subjectService.enrollStudent(1L, 1L)
        }
    }

    @Test
    fun `should throw SubjectNotFoundException when subject does not exist`() {
        `when`(subjectRepository.findById(99L)).thenReturn(Optional.empty())

        assertThrows<SubjectNotFoundException> {
            subjectService.enrollStudent(99L, 1L)
        }
    }

    @Test
    fun `should throw StudentNotFoundException when student does not exist`() {
        val professor = Professor(name = "Jorge", department = "Arquitectura Empresarial")
        val subject = Subject(name = "Algorithms", semester = "2025A", professor = professor)

        `when`(subjectRepository.findById(1L)).thenReturn(Optional.of(subject))
        `when`(studentRepository.findById(99L)).thenReturn(Optional.empty())

        assertThrows<StudentNotFoundException> {
            subjectService.enrollStudent(1L, 99L)
        }
    }

}