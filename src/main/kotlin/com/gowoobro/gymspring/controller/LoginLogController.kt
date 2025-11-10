package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import com.gowoobro.gymspring.service.LoginlogService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/loginlog")
class LoginlogController(private val loginlogService: LoginlogService) {

    @GetMapping
    fun getLoginlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Loginlog>> {
        val result = loginlogService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getLoginlog(@PathVariable id: Long): ResponseEntity<Loginlog> {
        val result = loginlogService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/ip")
    fun getLoginlogByIp(@RequestParam ip: String): ResponseEntity<List<Loginlog>> {
        val result = loginlogService.findByIp(ip)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/ipvalue")
    fun getLoginlogByIpvalue(@RequestParam ipvalue: Long): ResponseEntity<List<Loginlog>> {
        val result = loginlogService.findByIpvalue(ipvalue)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getLoginlogByUser(@RequestParam user: Long): ResponseEntity<List<Loginlog>> {
        val result = loginlogService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getLoginlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Loginlog>> {
        val result = loginlogService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = loginlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createLoginlog(@RequestBody request: LoginlogCreateRequest): ResponseEntity<Loginlog> {
        return try {
            val result = loginlogService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createLoginlogs(@RequestBody requests: List<LoginlogCreateRequest>): ResponseEntity<List<Loginlog>> {
        return try {
            val result = loginlogService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateLoginlog(
        @PathVariable id: Long,
        @RequestBody request: LoginlogUpdateRequest
    ): ResponseEntity<Loginlog> {
        val updatedRequest = request.copy(id = id)
        val result = loginlogService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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