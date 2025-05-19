package com.pucetec.reservations.mappers

import com.pucetec.reservations.models.entities.Professor
import com.pucetec.reservations.models.responses.ProfessorResponse
import org.springframework.stereotype.Component

@Component
class ProfessorMapper: BaseMapper<Professor, ProfessorResponse> {
    override fun toResponse(entity: Professor): ProfessorResponse {
        return ProfessorResponse(
            id = entity.id,
            name = entity.name,
            department = entity.department
        )
    }

    override fun toResponseList(entities: List<Professor>): List<ProfessorResponse> {
        return entities.map { toResponse(it) }
    }
}