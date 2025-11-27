package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import com.gowoobro.gymspring.service.PtreservationService
import com.gowoobro.gymspring.entity.PtreservationResponse
import com.gowoobro.gymspring.enums.ptreservation.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/ptreservation")
class PtreservationController(
    private val ptreservationService: PtreservationService) {

    private fun toResponse(ptreservation: Ptreservation): PtreservationResponse {
        return PtreservationResponse.from(ptreservation)
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
    fun getPtreservations(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) trainer: Long?,
        @RequestParam(required = false) member: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) startreservationdate: LocalDateTime?,
        @RequestParam(required = false) endreservationdate: LocalDateTime?,
        @RequestParam(required = false) starttime: String?,
        @RequestParam(required = false) endtime: String?,
        @RequestParam(required = false) duration: Int?,
        @RequestParam(required = false) status: Status?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) cancelreason: String?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startupdateddate: LocalDateTime?,
        @RequestParam(required = false) endupdateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (trainer != null || member != null || gym != null || startreservationdate != null || endreservationdate != null || starttime != null || endtime != null || duration != null || status != null || note != null || cancelreason != null || startcreateddate != null || endcreateddate != null || startupdateddate != null || endupdateddate != null || startdate != null || enddate != null || false) {
            var filtered = ptreservationService.findAll(0, Int.MAX_VALUE).content
            if (trainer != null) {
                filtered = filtered.filter { it.trainerId == trainer }
            }
            if (member != null) {
                filtered = filtered.filter { it.memberId == member }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (startreservationdate != null || endreservationdate != null) {
                filtered = filtered.filter { filterByDateRange(it.reservationdate, startreservationdate, endreservationdate) }
            }
            if (starttime != null) {
                filtered = filtered.filter { it.starttime == starttime }
            }
            if (endtime != null) {
                filtered = filtered.filter { it.endtime == endtime }
            }
            if (duration != null) {
                filtered = filtered.filter { it.duration == duration }
            }
            if (status != null) {
                filtered = filtered.filter { it.status == status }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (cancelreason != null) {
                filtered = filtered.filter { it.cancelreason == cancelreason }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startupdateddate != null || endupdateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.updateddate, startupdateddate, endupdateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            ptreservationService.findAll(0, Int.MAX_VALUE).content
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
    fun getPtreservation(@PathVariable id: Long): ResponseEntity<PtreservationResponse> {
        val res = ptreservationService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getPtreservationByTrainer(@RequestParam trainer: Long): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByTrainer(trainer)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/member")
    fun getPtreservationByMember(@RequestParam member: Long): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByMember(member)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getPtreservationByGym(@RequestParam gym: Long): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/reservationdate")
    fun getPtreservationByReservationdate(@RequestParam reservationdate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByReservationdate(reservationdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/starttime")
    fun getPtreservationByStarttime(@RequestParam starttime: String): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByStarttime(starttime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/endtime")
    fun getPtreservationByEndtime(@RequestParam endtime: String): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByEndtime(endtime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getPtreservationByDuration(@RequestParam duration: Int): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByDuration(duration)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getPtreservationByStatus(@RequestParam status: Status): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getPtreservationByNote(@RequestParam note: String): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/cancelreason")
    fun getPtreservationByCancelreason(@RequestParam cancelreason: String): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByCancelreason(cancelreason)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getPtreservationByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getPtreservationByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPtreservationByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val res = ptreservationService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ptreservationService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPtreservation(@RequestBody request: PtreservationCreateRequest): ResponseEntity<PtreservationResponse> {
        return try {
            val res = ptreservationService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPtreservations(@RequestBody requests: List<PtreservationCreateRequest>): ResponseEntity<List<PtreservationResponse>> {
        return try {
            val res = ptreservationService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePtreservation(
        @PathVariable id: Long,
        @RequestBody request: PtreservationUpdateRequest
    ): ResponseEntity<PtreservationResponse> {
        val updatedRequest = request.copy(id = id)
        val res = ptreservationService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePtreservation(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = ptreservationService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deletePtreservations(@RequestBody entities: List<Ptreservation>): ResponseEntity<Map<String, Boolean>> {
        val success = ptreservationService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}