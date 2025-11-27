package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import com.gowoobro.gymspring.service.RoleService
import com.gowoobro.gymspring.entity.RoleResponse
import com.gowoobro.gymspring.enums.role.Roleid

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/role")
class RoleController(
    private val roleService: RoleService) {

    private fun toResponse(role: Role): RoleResponse {
        return RoleResponse.from(role)
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
    fun getRoles(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) roleid: Int?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || roleid != null || name != null || startdate != null || enddate != null || false) {
            var filtered = roleService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (roleid != null) {
                filtered = filtered.filter { it.roleid.ordinal == roleid }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            roleService.findAll(0, Int.MAX_VALUE).content
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
    fun getRole(@PathVariable id: Long): ResponseEntity<RoleResponse> {
        val res = roleService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRoleByGym(@RequestParam gym: Long): ResponseEntity<List<RoleResponse>> {
        val res = roleService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/roleid")
    fun getRoleByRoleid(@RequestParam roleid: Roleid): ResponseEntity<List<RoleResponse>> {
        val res = roleService.findByRoleid(roleid)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getRoleByName(@RequestParam name: String): ResponseEntity<List<RoleResponse>> {
        val res = roleService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRoleByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RoleResponse>> {
        val res = roleService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = roleService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRole(@RequestBody request: RoleCreateRequest): ResponseEntity<RoleResponse> {
        return try {
            val res = roleService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRoles(@RequestBody requests: List<RoleCreateRequest>): ResponseEntity<List<RoleResponse>> {
        return try {
            val res = roleService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = roleService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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