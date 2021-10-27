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

@Controller
class AuthController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Any> {
        var userFromDb = userService.findByEmail(body.email)
        val passwordEncoder = BCryptPasswordEncoder()
        if (userFromDb == null) {
            val user = User(
                name = body.name,
                surname = body.surname,
                email = body.email,
                password = passwordEncoder.encode(body.password), ""
            )
            val savedUser = this.userService.save(user)

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
        return ResponseEntity.badRequest().body(HttpStatus.CONFLICT)
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO): ResponseEntity<Any> {
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)


        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val response: HashMap<String, Any> = HashMap()

        response.put("userid", user.id)
        response.put("token", jwt)

        return ResponseEntity.ok().body(response)
    }
}