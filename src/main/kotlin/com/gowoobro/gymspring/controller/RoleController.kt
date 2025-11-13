package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import com.gowoobro.gymspring.service.RoleService
import com.gowoobro.gymspring.entity.RoleResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/role")
class RoleController(
    private val roleService: RoleService, private val gymService: GymService) {

    private fun toResponse(role: Role):
    RoleResponse {
        
        val gym = gymService.findById(role.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return RoleResponse.from(role, gymResponse)
    }

    @GetMapping
    fun getRoles(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<RoleResponse>> {
        val result = roleService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getRole(@PathVariable id: Long): ResponseEntity<RoleResponse> {
        val result = roleService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRoleByGym(@RequestParam gym: Long): ResponseEntity<List<RoleResponse>> {
        val result = roleService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/roleid")
    fun getRoleByRoleid(@RequestParam roleid: Int): ResponseEntity<List<RoleResponse>> {
        val result = roleService.findByRoleid(roleid)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getRoleByName(@RequestParam name: String): ResponseEntity<List<RoleResponse>> {
        val result = roleService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRoleByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RoleResponse>> {
        val result = roleService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = roleService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRole(@RequestBody request: RoleCreateRequest): ResponseEntity<RoleResponse> {
        return try {
            val result = roleService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRoles(@RequestBody requests: List<RoleCreateRequest>): ResponseEntity<List<RoleResponse>> {
        return try {
            val result = roleService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRole(
        @PathVariable id: Long,
        @RequestBody request: RoleUpdateRequest
    ): ResponseEntity<RoleResponse> {
        val updatedRequest = request.copy(id = id)
        val result = roleService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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