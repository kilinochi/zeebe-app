package org.kilinochi.demo.zeebe.controller

import org.kilinochi.demo.zeebe.controller.dto.EmergencyReasonDto
import org.kilinochi.demo.zeebe.service.EmergencyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/emergency")
class EmergencyController(private val emergencyService: EmergencyService) {

    @PostMapping
    fun startProcess(@RequestBody reason: EmergencyReasonDto): ResponseEntity<Void> {
        emergencyService.startEvent(reason)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}