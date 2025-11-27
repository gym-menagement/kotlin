package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import com.gowoobro.gymspring.service.StopService
import com.gowoobro.gymspring.entity.StopResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/stop")
class StopController(
    private val stopService: StopService) {

    private fun toResponse(stop: Stop): StopResponse {
        return StopResponse.from(stop)
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
    fun getStops(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) usehealth: Long?,
        @RequestParam(required = false) count: Int?,
        @RequestParam(required = false) startstartday: LocalDateTime?,
        @RequestParam(required = false) endstartday: LocalDateTime?,
        @RequestParam(required = false) startendday: LocalDateTime?,
        @RequestParam(required = false) endendday: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?
    ): ResponseEntity<Map<String, Any>> {
        var results = if (usehealth != null || count != null || startstartday != null || endstartday != null || startendday != null || endendday != null || startdate != null || enddate != null) {
            var filtered = stopService.findAll(0, Int.MAX_VALUE).content
            if (usehealth != null) {
                filtered = filtered.filter { it.usehealthId == usehealth }
            }
            if (count != null) {
                filtered = filtered.filter { it.count == count }
            }
            if (startstartday != null || endstartday != null) {
                filtered = filtered.filter { filterByDateRange(it.startday, startstartday, endstartday) }
            }
            if (startendday != null || endendday != null) {
                filtered = filtered.filter { filterByDateRange(it.endday, startendday, endendday) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            stopService.findAll(0, Int.MAX_VALUE).content
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
    fun getStop(@PathVariable id: Long): ResponseEntity<StopResponse> {
        val res = stopService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/usehealth")
    fun getStopByUsehealth(@RequestParam usehealth: Long): ResponseEntity<List<StopResponse>> {
        val res = stopService.findByUsehealth(usehealth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startday")
    fun getStopByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val res = stopService.findByStartday(startday)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/endday")
    fun getStopByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val res = stopService.findByEndday(endday)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/count")
    fun getStopByCount(@RequestParam count: Int): ResponseEntity<List<StopResponse>> {
        val res = stopService.findByCount(count)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getStopByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val res = stopService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = stopService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createStop(@RequestBody request: StopCreateRequest): ResponseEntity<StopResponse> {
        return try {
            val res = stopService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createStops(@RequestBody requests: List<StopCreateRequest>): ResponseEntity<List<StopResponse>> {
        return try {
            val res = stopService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateStop(
        @PathVariable id: Long,
        @RequestBody request: StopUpdateRequest
    ): ResponseEntity<StopResponse> {
        val updatedRequest = request.copy(id = id)
        val res = stopService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteStop(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = stopService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteStops(@RequestBody entities: List<Stop>): ResponseEntity<Map<String, Boolean>> {
        val success = stopService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}