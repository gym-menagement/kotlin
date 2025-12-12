package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import com.gowoobro.gymspring.entity.DaytypePatchRequest
import com.gowoobro.gymspring.service.DaytypeService
import com.gowoobro.gymspring.entity.DaytypeResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/daytype")
class DaytypeController(
    private val daytypeService: DaytypeService) {

    private fun toResponse(daytype: Daytype): DaytypeResponse {
        return DaytypeResponse.from(daytype)
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
    fun getDaytypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || name != null || startdate != null || enddate != null || false) {
            var filtered = daytypeService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gym == gym }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            daytypeService.findAll(0, Int.MAX_VALUE).content
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
    fun getDaytype(@PathVariable id: Long): ResponseEntity<DaytypeResponse> {
        val res = daytypeService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getDaytypeByGym(@RequestParam gym: Long): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getDaytypeByName(@RequestParam name: String): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getDaytypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = daytypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDaytype(@RequestBody request: DaytypeCreateRequest): ResponseEntity<DaytypeResponse> {
        return try {
            val res = daytypeService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDaytypes(@RequestBody requests: List<DaytypeCreateRequest>): ResponseEntity<List<DaytypeResponse>> {
        return try {
            val res = daytypeService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateDaytype(
        @PathVariable id: Long,
        @RequestBody request: DaytypeUpdateRequest
    ): ResponseEntity<DaytypeResponse> {
        val updatedRequest = request.copy(id = id)
        val res = daytypeService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchDaytype(
        @PathVariable id: Long,
        @RequestBody request: DaytypePatchRequest
    ): ResponseEntity<DaytypeResponse> {
        val patchRequest = request.copy(id = id)
        val res = daytypeService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteDaytype(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = daytypeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteDaytypes(@RequestBody entities: List<Daytype>): ResponseEntity<Map<String, Boolean>> {
        val success = daytypeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}