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
    ): ResponseEntity<ApiSingleResponse<JwtResponse?>> {
        return try {
            println("JWT Login attempt: loginid=$loginid")
            val user = userService.findByLoginid(loginid)
            println("User found: ${user != null}")

            if (user != null) {
                println("User password from DB: ${user.passwd}")
                println("Input password: $passwd")
                val passwordMatches = passwordEncoder.matches(passwd, user.passwd)
                println("Password matches: $passwordMatches")

                if (passwordMatches) {
                    val jwt = jwtUtil.generateToken(user.loginid)
                    println("JWT generated successfully")
                    ResponseEntity.ok(ApiSingleResponse(item = JwtResponse(jwt)))
                } else {
                    ResponseEntity.ok(ApiSingleResponse(code = "error", item = null))
                }
            } else {
                ResponseEntity.ok(ApiSingleResponse(code = "not_found", item = null))
            }
        } catch (e: Exception) {
            println("Authentication error: ${e.message}")
            e.printStackTrace()
            ResponseEntity.ok(ApiSingleResponse(code = "error", item = null))
        }
    }
    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ApiSingleResponse<JwtResponse?>> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.loginid,
                    loginRequest.passwd
                )
            )
            val jwt = jwtUtil.generateToken(loginRequest.loginid)
            ResponseEntity.ok(ApiSingleResponse(item = JwtResponse(jwt)))
        } catch (e: Exception) {
            ResponseEntity.ok(ApiSingleResponse(code = "error", item = null))
        }
    }
}
