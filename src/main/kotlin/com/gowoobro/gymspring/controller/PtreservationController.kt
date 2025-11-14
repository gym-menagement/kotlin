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

    @GetMapping
    fun getPtreservations(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PtreservationResponse>> {
        val res = ptreservationService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
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