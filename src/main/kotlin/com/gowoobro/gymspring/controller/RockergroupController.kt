package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockergroup
import com.gowoobro.gymspring.entity.RockergroupCreateRequest
import com.gowoobro.gymspring.entity.RockergroupUpdateRequest
import com.gowoobro.gymspring.service.RockergroupService
import com.gowoobro.gymspring.entity.RockergroupResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/rockergroup")
class RockergroupController(
    private val rockergroupService: RockergroupService) {

    private fun toResponse(rockergroup: Rockergroup): RockergroupResponse {
        return RockergroupResponse.from(rockergroup)
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
    fun getRockergroups(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || name != null || startdate != null || enddate != null || false) {
            var filtered = rockergroupService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            rockergroupService.findAll(0, Int.MAX_VALUE).content
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
    fun getRockergroup(@PathVariable id: Long): ResponseEntity<RockergroupResponse> {
        val res = rockergroupService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRockergroupByGym(@RequestParam gym: Long): ResponseEntity<List<RockergroupResponse>> {
        val res = rockergroupService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getRockergroupByName(@RequestParam name: String): ResponseEntity<List<RockergroupResponse>> {
        val res = rockergroupService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRockergroupByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockergroupResponse>> {
        val res = rockergroupService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockergroupService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRockergroup(@RequestBody request: RockergroupCreateRequest): ResponseEntity<RockergroupResponse> {
        return try {
            val res = rockergroupService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockergroups(@RequestBody requests: List<RockergroupCreateRequest>): ResponseEntity<List<RockergroupResponse>> {
        return try {
            val res = rockergroupService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRockergroup(
        @PathVariable id: Long,
        @RequestBody request: RockergroupUpdateRequest
    ): ResponseEntity<RockergroupResponse> {
        val updatedRequest = request.copy(id = id)
        val res = rockergroupService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRockergroup(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = rockergroupService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRockergroups(@RequestBody entities: List<Rockergroup>): ResponseEntity<Map<String, Boolean>> {
        val success = rockergroupService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}