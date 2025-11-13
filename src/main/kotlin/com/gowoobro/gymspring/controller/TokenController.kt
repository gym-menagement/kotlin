package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import com.gowoobro.gymspring.service.TokenService
import com.gowoobro.gymspring.entity.TokenResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.token.Status


@RestController
@RequestMapping("/api/token")
class TokenController(
    private val tokenService: TokenService, private val userService: UserService) {

    private fun toResponse(token: Token):
    TokenResponse {
        
        val user = userService.findById(token.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return TokenResponse.from(token, userResponse)
    }

    @GetMapping
    fun getTokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<TokenResponse>> {
        val result = tokenService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getToken(@PathVariable id: Long): ResponseEntity<TokenResponse> {
        val result = tokenService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getTokenByUser(@RequestParam user: Long): ResponseEntity<List<TokenResponse>> {
        val result = tokenService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/token")
    fun getTokenByToken(@RequestParam token: String): ResponseEntity<List<TokenResponse>> {
        val result = tokenService.findByToken(token)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getTokenByStatus(@RequestParam status: Status): ResponseEntity<List<TokenResponse>> {
        val result = tokenService.findByStatus(status)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TokenResponse>> {
        val result = tokenService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = tokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createToken(@RequestBody request: TokenCreateRequest): ResponseEntity<TokenResponse> {
        return try {
            val result = tokenService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTokens(@RequestBody requests: List<TokenCreateRequest>): ResponseEntity<List<TokenResponse>> {
        return try {
            val result = tokenService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateToken(
        @PathVariable id: Long,
        @RequestBody request: TokenUpdateRequest
    ): ResponseEntity<TokenResponse> {
        val updatedRequest = request.copy(id = id)
        val result = tokenService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteToken(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = tokenService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteTokens(@RequestBody entities: List<Token>): ResponseEntity<Map<String, Boolean>> {
        val success = tokenService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}