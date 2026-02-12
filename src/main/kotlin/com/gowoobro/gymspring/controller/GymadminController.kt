package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.*
import com.gowoobro.gymspring.service.GymadminService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/gymadmin")
class GymadminController(
    private val gymadminService: GymadminService
) {

    private fun toResponse(gymadmin: Gymadmin): GymadminResponse {
        return GymadminResponse.from(gymadmin)
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
    fun getGymadmins(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) level: Int?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || user != null || level != null || status != null || startdate != null || enddate != null) {
            var filtered = gymadminService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (level != null) {
                filtered = filtered.filter { it.level.ordinal == level }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            gymadminService.findAll(0, Int.MAX_VALUE).content
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
    fun getGymadmin(@PathVariable id: Long): ResponseEntity<GymadminResponse> {
        val res = gymadminService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/search/gym")
    fun getGymadminByGym(@RequestParam gym: Long): ResponseEntity<List<GymadminResponse>> {
        val res = gymadminService.findByGymId(gym, 0, Int.MAX_VALUE).content
        return ResponseEntity.ok(res.map { toResponse(it) })
    }

    @GetMapping("/search/user")
    fun getGymadminByUser(@RequestParam user: Long): ResponseEntity<List<GymadminResponse>> {
        val res = gymadminService.findByUserId(user, 0, Int.MAX_VALUE).content
        return ResponseEntity.ok(res.map { toResponse(it) })
    }
    
    @PostMapping
    fun createGymadmin(@RequestBody request: GymadminCreateRequest): ResponseEntity<GymadminResponse> {
        return try {
            val res = gymadminService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createGymadmins(@RequestBody requests: List<GymadminCreateRequest>): ResponseEntity<List<GymadminResponse>> {
        return try {
            val res = gymadminService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) })
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateGymadmin(
        @PathVariable id: Long,
        @RequestBody request: GymadminUpdateRequest
    ): ResponseEntity<GymadminResponse> {
        val updatedRequest = request.copy(id = id)
        val res = gymadminService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchGymadmin(
        @PathVariable id: Long,
        @RequestBody request: GymadminPatchRequest
    ): ResponseEntity<GymadminResponse> {
        val patchRequest = request.copy(id = id)
        val res = gymadminService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGymadmin(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = gymadminService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteGymadmins(@RequestBody entities: List<Gymadmin>): ResponseEntity<Map<String, Boolean>> {
        val success = gymadminService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}
