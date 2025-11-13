package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import com.gowoobro.gymspring.service.LoginlogService
import com.gowoobro.gymspring.entity.LoginlogResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/loginlog")
class LoginlogController(
    private val loginlogService: LoginlogService, private val userService: UserService) {

    private fun toResponse(loginlog: Loginlog):
    LoginlogResponse {
        
        val user = userService.findById(loginlog.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return LoginlogResponse.from(loginlog, userResponse)
    }

    @GetMapping
    fun getLoginlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<LoginlogResponse>> {
        val result = loginlogService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getLoginlog(@PathVariable id: Long): ResponseEntity<LoginlogResponse> {
        val result = loginlogService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/ip")
    fun getLoginlogByIp(@RequestParam ip: String): ResponseEntity<List<LoginlogResponse>> {
        val result = loginlogService.findByIp(ip)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/ipvalue")
    fun getLoginlogByIpvalue(@RequestParam ipvalue: Long): ResponseEntity<List<LoginlogResponse>> {
        val result = loginlogService.findByIpvalue(ipvalue)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getLoginlogByUser(@RequestParam user: Long): ResponseEntity<List<LoginlogResponse>> {
        val result = loginlogService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getLoginlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<LoginlogResponse>> {
        val result = loginlogService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = loginlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createLoginlog(@RequestBody request: LoginlogCreateRequest): ResponseEntity<LoginlogResponse> {
        return try {
            val result = loginlogService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createLoginlogs(@RequestBody requests: List<LoginlogCreateRequest>): ResponseEntity<List<LoginlogResponse>> {
        return try {
            val result = loginlogService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = loginlogService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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