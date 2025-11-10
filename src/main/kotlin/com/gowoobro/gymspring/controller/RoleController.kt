package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import com.gowoobro.gymspring.service.RoleService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/role")
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getRoles(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Role>> {
        val result = roleService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getRole(@PathVariable id: Long): ResponseEntity<Role> {
        val result = roleService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRoleByGym(@RequestParam gym: Long): ResponseEntity<List<Role>> {
        val result = roleService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/role")
    fun getRoleByRole(@RequestParam role: Int): ResponseEntity<List<Role>> {
        val result = roleService.findByRole(role)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getRoleByName(@RequestParam name: String): ResponseEntity<List<Role>> {
        val result = roleService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getRoleByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Role>> {
        val result = roleService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = roleService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRole(@RequestBody request: RoleCreateRequest): ResponseEntity<Role> {
        return try {
            val result = roleService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRoles(@RequestBody requests: List<RoleCreateRequest>): ResponseEntity<List<Role>> {
        return try {
            val result = roleService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRole(
        @PathVariable id: Long,
        @RequestBody request: RoleUpdateRequest
    ): ResponseEntity<Role> {
        val updatedRequest = request.copy(id = id)
        val result = roleService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRole(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = roleService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRoles(@RequestBody entities: List<Role>): ResponseEntity<Map<String, Boolean>> {
        val success = roleService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}