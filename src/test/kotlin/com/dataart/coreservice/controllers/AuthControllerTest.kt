package com.dataart.coreservice.controllers

import com.dataart.coreservice.AbstractTestClass
import com.dataart.coreservice.repository.UserRepository
import com.dataart.coreservice.services.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate


class AuthControllerTest(
    @Autowired val userRepository: UserRepository,
    @Autowired val userService: UserService,
    @Autowired testRest: TestRestTemplate
) : AbstractTestClass(testRest) {

    private val EVENT_URI_LOGIN = "/login"
    private val EVENT_URI_REGISTER = "/register"
    private val text_value_length = 6;

//    private val requestLoginBody: LoginDto = LoginDto("a2@a.com", "111")

    @Test
    fun `Verify that POST login works correctly`() {
//        val expectedId: Long = userRepository.count() + 1
//
//        val loginUser = randomAlphabetic(text_value_length)
//        val passwordUser = randomAlphabetic(text_value_length)
//
//        val testedUser: User = userRepository.save(
//            User(
//                randomAlphabetic(text_value_length),
//                randomAlphabetic(text_value_length),
//                loginUser,
//                passwordUser,
//                randomAlphabetic(text_value_length)
//            )
//        )
//
//        var userRequest = LoginDTO(
//                loginUser,
//                passwordUser)
//
//        with(
//            testRest
//                .postForEntity(EVENT_URI_LOGIN, userRequest, Map)
//                {
//
//                }
//        )

            //Assertions.assertEquals(200, response.statusCode)
//        Assertions.assertEquals()
    }
}