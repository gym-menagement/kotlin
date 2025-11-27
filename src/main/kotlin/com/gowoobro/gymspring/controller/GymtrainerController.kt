package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Gymtrainer
import com.gowoobro.gymspring.entity.GymtrainerCreateRequest
import com.gowoobro.gymspring.entity.GymtrainerUpdateRequest
import com.gowoobro.gymspring.service.GymtrainerService
import com.gowoobro.gymspring.entity.GymtrainerResponse
import com.gowoobro.gymspring.enums.gymtrainer.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/gymtrainer")
class GymtrainerController(
    private val gymtrainerService: GymtrainerService) {

    private fun toResponse(gymtrainer: Gymtrainer): GymtrainerResponse {
        return GymtrainerResponse.from(gymtrainer)
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
    fun getGymtrainers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) trainer: Long?,
        @RequestParam(required = false) startstartdate: LocalDateTime?,
        @RequestParam(required = false) endstartdate: LocalDateTime?,
        @RequestParam(required = false) startenddate: LocalDateTime?,
        @RequestParam(required = false) endenddate: LocalDateTime?,
        @RequestParam(required = false) status: Status?,
        @RequestParam(required = false) position: String?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || trainer != null || startstartdate != null || endstartdate != null || startenddate != null || endenddate != null || status != null || position != null || note != null || startdate != null || enddate != null || false) {
            var filtered = gymtrainerService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (trainer != null) {
                filtered = filtered.filter { it.trainerId == trainer }
            }
            if (startstartdate != null || endstartdate != null) {
                filtered = filtered.filter { filterByDateRange(it.startdate, startstartdate, endstartdate) }
            }
            if (startenddate != null || endenddate != null) {
                filtered = filtered.filter { filterByDateRange(it.enddate, startenddate, endenddate) }
            }
            if (status != null) {
                filtered = filtered.filter { it.status == status }
            }
            if (position != null) {
                filtered = filtered.filter { it.position == position }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            gymtrainerService.findAll(0, Int.MAX_VALUE).content
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
    fun getGymtrainer(@PathVariable id: Long): ResponseEntity<GymtrainerResponse> {
        val res = gymtrainerService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getGymtrainerByGym(@RequestParam gym: Long): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/trainer")
    fun getGymtrainerByTrainer(@RequestParam trainer: Long): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByTrainer(trainer)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getGymtrainerByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByStartdate(startdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getGymtrainerByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByEnddate(enddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getGymtrainerByStatus(@RequestParam status: Status): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/position")
    fun getGymtrainerByPosition(@RequestParam position: String): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByPosition(position)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getGymtrainerByNote(@RequestParam note: String): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getGymtrainerByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<GymtrainerResponse>> {
        val res = gymtrainerService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = gymtrainerService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createGymtrainer(@RequestBody request: GymtrainerCreateRequest): ResponseEntity<GymtrainerResponse> {
        return try {
            val res = gymtrainerService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createGymtrainers(@RequestBody requests: List<GymtrainerCreateRequest>): ResponseEntity<List<GymtrainerResponse>> {
        return try {
            val res = gymtrainerService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateGymtrainer(
        @PathVariable id: Long,
        @RequestBody request: GymtrainerUpdateRequest
    ): ResponseEntity<GymtrainerResponse> {
        val updatedRequest = request.copy(id = id)
        val res = gymtrainerService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGymtrainer(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = gymtrainerService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteGymtrainers(@RequestBody entities: List<Gymtrainer>): ResponseEntity<Map<String, Boolean>> {
        val success = gymtrainerService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}