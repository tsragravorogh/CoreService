package com.dataart.coreservice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class IntegrationTests {

    @Container
    val container = PostgreSQLContainer<Nothing>(
        DockerImageName.parse("postgres:13-alpine")
    ).apply {
        withDatabaseName("core_service")
        withUsername("postgres")
        withPassword("12345678")
        withInitScript("db/migration/V1__init.sql")
    }

    @Test
    fun `container is up and running`() {
        Assertions.assertTrue(container.isRunning)
    }
}