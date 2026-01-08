package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import com.gowoobro.gymspring.entity.LoginlogPatchRequest
import com.gowoobro.gymspring.service.LoginlogService
import com.gowoobro.gymspring.entity.LoginlogResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/loginlog")
class LoginlogController(
    private val loginlogService: LoginlogService) {

    private fun toResponse(loginlog: Loginlog): LoginlogResponse {
        return LoginlogResponse.from(loginlog)
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
    fun getLoginlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) ip: String?,
        @RequestParam(required = false) ipvalue: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (ip != null || ipvalue != null || user != null || startdate != null || enddate != null || false) {
            var filtered = loginlogService.findAll(0, Int.MAX_VALUE).content
            if (ip != null) {
                filtered = filtered.filter { it.ip == ip }
            }
            if (ipvalue != null) {
                filtered = filtered.filter { it.ipvalue == ipvalue }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            loginlogService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getLoginlog(@PathVariable id: Long): ResponseEntity<LoginlogResponse> {
        val res = loginlogService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/ip")
    fun getLoginlogByIp(@RequestParam ip: String): ResponseEntity<List<LoginlogResponse>> {
        val res = loginlogService.findByIp(ip)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/ipvalue")
    fun getLoginlogByIpvalue(@RequestParam ipvalue: Long): ResponseEntity<List<LoginlogResponse>> {
        val res = loginlogService.findByIpvalue(ipvalue)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getLoginlogByUser(@RequestParam user: Long): ResponseEntity<List<LoginlogResponse>> {
        val res = loginlogService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getLoginlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<LoginlogResponse>> {
        val res = loginlogService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = loginlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createLoginlog(@RequestBody request: LoginlogCreateRequest): ResponseEntity<LoginlogResponse> {
        return try {
            val res = loginlogService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createLoginlogs(@RequestBody requests: List<LoginlogCreateRequest>): ResponseEntity<List<LoginlogResponse>> {
        return try {
            val res = loginlogService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateLoginlog(
        @PathVariable id: Long,
        @RequestBody request: LoginlogUpdateRequest
    ): ResponseEntity<LoginlogResponse> {
        val updatedRequest = request.copy(id = id)
        val res = loginlogService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchLoginlog(
        @PathVariable id: Long,
        @RequestBody request: LoginlogPatchRequest
    ): ResponseEntity<LoginlogResponse> {
        val patchRequest = request.copy(id = id)
        val res = loginlogService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteLoginlog(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = loginlogService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteLoginlogs(@RequestBody entities: List<Loginlog>): ResponseEntity<Map<String, Boolean>> {
        val success = loginlogService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}