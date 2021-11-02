package com.dataart.coreservice.controllers

import com.dataart.coreservice.AbstractTestClass
import com.dataart.coreservice.dtos.UserDto
import com.dataart.coreservice.repository.UserRepository
import com.dataart.coreservice.services.UserService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils


class AuthControllerTest (
    @Autowired val userRepository: UserRepository,
    @Autowired testRest: TestRestTemplate
) : AbstractTestClass(testRest) {

    private val REGISTER_URI = "/register"
    private val LOGIN_URI = "/login"

    private val textValueLength = 6

    @Test
    fun `Verify that POST register works correctly`() {
        val email = RandomStringUtils.randomAlphabetic(textValueLength)
        val password = RandomStringUtils.randomAlphabetic(textValueLength)
        val registerRequest = UserDto(
            RandomStringUtils.randomAlphabetic(textValueLength),
            RandomStringUtils.randomAlphabetic(textValueLength),
            email,
            password
        )
        with(
            testRest
                .postForEntity(REGISTER_URI, registerRequest, HashMap::class.java)
        ) {
            statusCode shouldBe HttpStatus.OK
            body shouldNotBe null
            print(body)
        }

    }
}