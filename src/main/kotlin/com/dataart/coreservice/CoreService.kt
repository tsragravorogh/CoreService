package com.dataart.coreservice

import org.springframework.stereotype.Service

@Service
class CoreService {
    private val answer = "Hello world! We are CORE!";

    fun getMessage() = answer;
}