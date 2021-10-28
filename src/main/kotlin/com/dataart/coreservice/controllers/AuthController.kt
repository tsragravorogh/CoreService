package com.dataart.coreservice.controllers

import com.dataart.coreservice.dtos.LoginDTO
import com.dataart.coreservice.dtos.RegisterDTO
import com.dataart.coreservice.model.User
import com.dataart.coreservice.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*
import kotlin.collections.HashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Controller
class AuthController(private val userService: UserService) {

    private val logger: Logger = LoggerFactory.getLogger("com.dataart.coreservice.logback")

    @PostMapping("/register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Any> {
        val userFromDb = userService.findByEmail(body.email)
        logger.info("Saving user' request: {}", body.toString())
        val passwordEncoder = BCryptPasswordEncoder()
        if (userFromDb == null) {
            val user = User(
                name = body.name,
                surname = body.surname,
                email = body.email,
                password = passwordEncoder.encode(body.password), ""
            )
            val savedUser = this.userService.save(user)

            logger.info("New user was saved: {}", body.email)

            val response: HashMap<String, Any> = HashMap()

            val issuer = savedUser.id.toString()

            val jwt = Jwts.builder()
                .setIssuer(issuer)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
                .signWith(SignatureAlgorithm.HS512, "secret").compact()

            response.put("userid", savedUser.id)
            response.put("token", jwt)


            return ResponseEntity.status(201).body(response)
        }
        logger.info("User was not saved: {}", body.email)
        val bodyResponse = HashMap<String, Any> ()
        bodyResponse.put("email", body.email)
        return ResponseEntity.status(409).body(bodyResponse)
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO): ResponseEntity<Any> {
        logger.info("User authorization request: {}", body.toString())
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)
        if (!user.comparePassword(body.password)) {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val response: HashMap<String, Any> = HashMap()

        response.put("userid", user.id)
        response.put("token", jwt)
        logger.info("User was authorized: {}", body.email)

        return ResponseEntity.ok().body(response)
    }
}