package com.pucetec.reservations.mappers

import com.pucetec.reservations.models.entities.Subject
import com.pucetec.reservations.models.requests.SubjectRequest
import com.pucetec.reservations.models.responses.ProfessorResponse
import com.pucetec.reservations.models.responses.StudentResponse
import com.pucetec.reservations.models.responses.SubjectResponse
import org.springframework.stereotype.Component

@Component
class SubjectMapper: BaseMapper<Subject, SubjectResponse> {
    override fun toResponse(entity: Subject): SubjectResponse {
        return SubjectResponse(
            id = entity.id,
            name = entity.name,
            semester = entity.semester,
            professor = ProfessorResponse(
                id = entity.professor.id,
                name = entity.professor.name,
                department = entity.professor.department
            ),
            students = entity.students.map {
                StudentResponse(
                    id = it.id,
                    name = it.name,
                    email = it.email
                )
            }
        )
    }

    override fun toResponseList(entities: List<Subject>): List<SubjectResponse> {
        return entities.map { toResponse(it) }
    }
}