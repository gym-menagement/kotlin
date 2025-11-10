package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import com.gowoobro.gymspring.service.TokenService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.token.Status


@RestController
@RequestMapping("/api/token")
class TokenController(private val tokenService: TokenService) {

    @GetMapping
    fun getTokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Token>> {
        val result = tokenService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getToken(@PathVariable id: Long): ResponseEntity<Token> {
        val result = tokenService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getTokenByUser(@RequestParam user: Long): ResponseEntity<List<Token>> {
        val result = tokenService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/token")
    fun getTokenByToken(@RequestParam token: String): ResponseEntity<List<Token>> {
        val result = tokenService.findByToken(token)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getTokenByStatus(@RequestParam status: Status): ResponseEntity<List<Token>> {
        val result = tokenService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getTokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Token>> {
        val result = tokenService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = tokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createToken(@RequestBody request: TokenCreateRequest): ResponseEntity<Token> {
        return try {
            val result = tokenService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTokens(@RequestBody requests: List<TokenCreateRequest>): ResponseEntity<List<Token>> {
        return try {
            val result = tokenService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateToken(
        @PathVariable id: Long,
        @RequestBody request: TokenUpdateRequest
    ): ResponseEntity<Token> {
        val updatedRequest = request.copy(id = id)
        val result = tokenService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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