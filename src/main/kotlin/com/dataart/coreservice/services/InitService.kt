package com.dataart.coreservice.services

import org.springframework.stereotype.Service

@Service
class InitService {
    private val answer = "Hello world! We are CORE!"

    fun getMessage() = answer
}
