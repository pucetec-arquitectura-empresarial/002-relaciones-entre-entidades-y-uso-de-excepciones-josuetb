package com.pucetec.reservations.mappers

import com.pucetec.reservations.models.entities.Student
import com.pucetec.reservations.models.responses.StudentResponse
import org.springframework.stereotype.Component

@Component
class StudentMapper: BaseMapper<Student, StudentResponse> {
    override fun toResponse(entity: Student): StudentResponse {
        return StudentResponse(
            id = entity.id,
            name = entity.name,
            email = entity.email
        )
    }

    override fun toResponseList(entities: List<Student>): List<StudentResponse> {
        return entities.map { toResponse(it) }
    }
}