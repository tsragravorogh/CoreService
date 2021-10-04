package com.dataart.coreservice.controllers

import com.dataart.coreservice.services.InitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InitServiceController {
    @GetMapping
    fun helloWorld(): String {
        val initService = InitService();
        return initService.getMessage();
    }
}