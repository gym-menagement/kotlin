package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.entity.TrainermemberPatchRequest
import com.gowoobro.gymspring.service.TrainermemberService
import com.gowoobro.gymspring.entity.TrainermemberResponse
import com.gowoobro.gymspring.enums.trainermember.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/trainermember")
class TrainermemberController(
    private val trainermemberService: TrainermemberService) {

    private fun toResponse(trainermember: Trainermember): TrainermemberResponse {
        return TrainermemberResponse.from(trainermember)
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
    fun getTrainermembers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) trainer: Long?,
        @RequestParam(required = false) member: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) startstartdate: LocalDateTime?,
        @RequestParam(required = false) endstartdate: LocalDateTime?,
        @RequestParam(required = false) startenddate: LocalDateTime?,
        @RequestParam(required = false) endenddate: LocalDateTime?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (trainer != null || member != null || gym != null || startstartdate != null || endstartdate != null || startenddate != null || endenddate != null || status != null || note != null || startdate != null || enddate != null || false) {
            var filtered = trainermemberService.findAll(0, Int.MAX_VALUE).content
            if (trainer != null) {
                filtered = filtered.filter { it.trainer == trainer }
            }
            if (member != null) {
                filtered = filtered.filter { it.member == member }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gym == gym }
            }
            if (startstartdate != null || endstartdate != null) {
                filtered = filtered.filter { filterByDateRange(it.startdate, startstartdate, endstartdate) }
            }
            if (startenddate != null || endenddate != null) {
                filtered = filtered.filter { filterByDateRange(it.enddate, startenddate, endenddate) }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            trainermemberService.findAll(0, Int.MAX_VALUE).content
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
    fun getTrainermember(@PathVariable id: Long): ResponseEntity<TrainermemberResponse> {
        val res = trainermemberService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getTrainermemberByTrainer(@RequestParam trainer: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByTrainer(trainer)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/member")
    fun getTrainermemberByMember(@RequestParam member: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByMember(member)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getTrainermemberByGym(@RequestParam gym: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getTrainermemberByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByStartdate(startdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getTrainermemberByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByEnddate(enddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getTrainermemberByStatus(@RequestParam status: Status): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getTrainermemberByNote(@RequestParam note: String): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTrainermemberByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = trainermemberService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTrainermember(@RequestBody request: TrainermemberCreateRequest): ResponseEntity<TrainermemberResponse> {
        return try {
            val res = trainermemberService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTrainermembers(@RequestBody requests: List<TrainermemberCreateRequest>): ResponseEntity<List<TrainermemberResponse>> {
        return try {
            val res = trainermemberService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateTrainermember(
        @PathVariable id: Long,
        @RequestBody request: TrainermemberUpdateRequest
    ): ResponseEntity<TrainermemberResponse> {
        val updatedRequest = request.copy(id = id)
        val res = trainermemberService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchTrainermember(
        @PathVariable id: Long,
        @RequestBody request: TrainermemberPatchRequest
    ): ResponseEntity<TrainermemberResponse> {
        val patchRequest = request.copy(id = id)
        val res = trainermemberService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTrainermember(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = trainermemberService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteTrainermembers(@RequestBody entities: List<Trainermember>): ResponseEntity<Map<String, Boolean>> {
        val success = trainermemberService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}