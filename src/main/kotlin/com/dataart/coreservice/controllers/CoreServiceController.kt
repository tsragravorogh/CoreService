package com.dataart.coreservice.controllers

import com.dataart.coreservice.CoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CoreServiceController {
    @GetMapping
    fun helloWorld(): String {
        val coreService = CoreService();
        return coreService.getMessage();
    }
}