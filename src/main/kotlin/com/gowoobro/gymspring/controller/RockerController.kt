package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import com.gowoobro.gymspring.service.RockerService
import com.gowoobro.gymspring.entity.RockerResponse
import com.gowoobro.gymspring.enums.rocker.Available

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/rocker")
class RockerController(
    private val rockerService: RockerService) {

    private fun toResponse(rocker: Rocker): RockerResponse {
        return RockerResponse.from(rocker)
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
    fun getRockers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) group: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) available: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || group != null || name != null || available != null || startdate != null || enddate != null || false) {
            var filtered = rockerService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (group != null) {
                filtered = filtered.filter { it.groupId == group }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (available != null) {
                filtered = filtered.filter { it.available.ordinal == available }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            rockerService.findAll(0, Int.MAX_VALUE).content
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
    fun getRocker(@PathVariable id: Long): ResponseEntity<RockerResponse> {
        val res = rockerService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRockerByGym(@RequestParam gym: Long): ResponseEntity<List<RockerResponse>> {
        val res = rockerService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/group")
    fun getRockerByGroup(@RequestParam group: Long): ResponseEntity<List<RockerResponse>> {
        val res = rockerService.findByGroup(group)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getRockerByName(@RequestParam name: String): ResponseEntity<List<RockerResponse>> {
        val res = rockerService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/available")
    fun getRockerByAvailable(@RequestParam available: Available): ResponseEntity<List<RockerResponse>> {
        val res = rockerService.findByAvailable(available)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRockerByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockerResponse>> {
        val res = rockerService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockerService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRocker(@RequestBody request: RockerCreateRequest): ResponseEntity<RockerResponse> {
        return try {
            val res = rockerService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockers(@RequestBody requests: List<RockerCreateRequest>): ResponseEntity<List<RockerResponse>> {
        return try {
            val res = rockerService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRocker(
        @PathVariable id: Long,
        @RequestBody request: RockerUpdateRequest
    ): ResponseEntity<RockerResponse> {
        val updatedRequest = request.copy(id = id)
        val res = rockerService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRocker(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = rockerService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRockers(@RequestBody entities: List<Rocker>): ResponseEntity<Map<String, Boolean>> {
        val success = rockerService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}