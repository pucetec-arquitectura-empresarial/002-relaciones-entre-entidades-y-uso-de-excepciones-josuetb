package com.pucetec.reservations.controllers

import com.pucetec.reservations.constants.Routes
import com.pucetec.reservations.models.entities.Student
import com.pucetec.reservations.models.responses.StudentResponse
import com.pucetec.reservations.services.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE_PATH + Routes.STUDENT)
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    fun create(@RequestBody student: Student): StudentResponse = studentService.createStudent(student)

    @GetMapping
    fun list(): List<StudentResponse> = studentService.listStudents()
}