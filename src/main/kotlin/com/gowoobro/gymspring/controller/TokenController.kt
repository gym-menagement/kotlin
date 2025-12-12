package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import com.gowoobro.gymspring.entity.TokenPatchRequest
import com.gowoobro.gymspring.service.TokenService
import com.gowoobro.gymspring.entity.TokenResponse
import com.gowoobro.gymspring.enums.token.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/token")
class TokenController(
    private val tokenService: TokenService) {

    private fun toResponse(token: Token): TokenResponse {
        return TokenResponse.from(token)
    }

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getTokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) token: String?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || token != null || status != null || startdate != null || enddate != null || false) {
            var filtered = tokenService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.user == user }
            }
            if (token != null) {
                filtered = filtered.filter { it.token == token }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            tokenService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getToken(@PathVariable id: Long): ResponseEntity<TokenResponse> {
        val res = tokenService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getTokenByUser(@RequestParam user: Long): ResponseEntity<List<TokenResponse>> {
        val res = tokenService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/token")
    fun getTokenByToken(@RequestParam token: String): ResponseEntity<List<TokenResponse>> {
        val res = tokenService.findByToken(token)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getTokenByStatus(@RequestParam status: Status): ResponseEntity<List<TokenResponse>> {
        val res = tokenService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TokenResponse>> {
        val res = tokenService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = tokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createToken(@RequestBody request: TokenCreateRequest): ResponseEntity<TokenResponse> {
        return try {
            val res = tokenService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTokens(@RequestBody requests: List<TokenCreateRequest>): ResponseEntity<List<TokenResponse>> {
        return try {
            val res = tokenService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = tokenService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchToken(
        @PathVariable id: Long,
        @RequestBody request: TokenPatchRequest
    ): ResponseEntity<TokenResponse> {
        val patchRequest = request.copy(id = id)
        val res = tokenService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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