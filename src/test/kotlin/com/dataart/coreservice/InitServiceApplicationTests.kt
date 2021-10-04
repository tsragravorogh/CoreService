package com.dataart.coreservice

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.*
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InitServiceApplicationTests(@Autowired val testRest: TestRestTemplate) {

    @Test
    fun coreServiceControllerTest() {
        val webTestEntity = testRest.getForEntity<String>("/")
        assertThat(webTestEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(webTestEntity.body).contains("Hello world! We are CORE!")
    }
}
