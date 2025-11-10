package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.config.JwtUtil
import com.gowoobro.gymspring.entity.*
import com.gowoobro.gymspring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
data class LoginRequest(val loginid: String, val passwd: String)
data class JwtResponse(val token: String, val type: String = "Bearer")
@RestController
@RequestMapping("/api")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {
    
    @GetMapping("/jwt")
    fun authenticateUser(
        @RequestParam loginid: String,
        @RequestParam passwd: String
    ): ResponseEntity<JwtResponse?> {
        return try {
            println("JWT Login attempt: loginid=$loginid")
            val users = userService.findByLoginid(loginid)
            val user = users.firstOrNull()
            println("User found: ${user != null}")

            if (user != null) {
                println("User password from DB: ${user.passwd}")
                println("Input password: $passwd")
                val passwordMatches = passwordEncoder.matches(passwd, user.passwd)
                println("Password matches: $passwordMatches")

                if (passwordMatches) {
                    val jwt = jwtUtil.generateToken(user.loginid)
                    println("JWT generated successfully")
                    ResponseEntity.ok(JwtResponse(jwt))
                } else {
                    ResponseEntity.status(401).body(null)
                }
            } else {
                ResponseEntity.status(404).body(null)
            }
        } catch (e: Exception) {
            println("Authentication error: ${e.message}")
            e.printStackTrace()
            ResponseEntity.status(500).body(null)
        }
    }
    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<JwtResponse?> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.loginid,
                    loginRequest.passwd
                )
            )
            val jwt = jwtUtil.generateToken(loginRequest.loginid)
            ResponseEntity.ok(JwtResponse(jwt))
        } catch (e: Exception) {
            ResponseEntity.status(401).body(null)
        }
    }
}
