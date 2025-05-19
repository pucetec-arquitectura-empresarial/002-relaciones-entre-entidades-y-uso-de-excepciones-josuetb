package com.pucetec.reservations.controllers

import com.pucetec.reservations.constants.Routes
import com.pucetec.reservations.models.entities.Professor
import com.pucetec.reservations.models.responses.ProfessorResponse
import com.pucetec.reservations.services.ProfessorService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE_PATH + Routes.PROFESSOR)
class ProfessorController(
    private val professorService: ProfessorService
) {

    @PostMapping
    fun create(@RequestBody professor: Professor): ProfessorResponse =
        professorService.createProfessor(professor)

    @GetMapping
    fun list(): List<ProfessorResponse> = professorService.listProfessors()
}