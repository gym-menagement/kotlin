package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import com.gowoobro.gymspring.service.PtreservationService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/ptreservation")
class PtreservationController(private val ptreservationService: PtreservationService) {

    @GetMapping
    fun getPtreservations(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Ptreservation>> {
        val result = ptreservationService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getPtreservation(@PathVariable id: Long): ResponseEntity<Ptreservation> {
        val result = ptreservationService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getPtreservationByTrainer(@RequestParam trainer: Long): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByTrainer(trainer)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/member")
    fun getPtreservationByMember(@RequestParam member: Long): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByMember(member)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/gym")
    fun getPtreservationByGym(@RequestParam gym: Long): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/reservationdate")
    fun getPtreservationByReservationdate(@RequestParam reservationdate: LocalDateTime): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByReservationdate(reservationdate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/starttime")
    fun getPtreservationByStarttime(@RequestParam starttime: String): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByStarttime(starttime)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/endtime")
    fun getPtreservationByEndtime(@RequestParam endtime: String): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByEndtime(endtime)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/duration")
    fun getPtreservationByDuration(@RequestParam duration: Int): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByDuration(duration)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getPtreservationByStatus(@RequestParam status: Int): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getPtreservationByNote(@RequestParam note: String): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/cancelreason")
    fun getPtreservationByCancelreason(@RequestParam cancelreason: String): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByCancelreason(cancelreason)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createddate")
    fun getPtreservationByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByCreateddate(createddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/updateddate")
    fun getPtreservationByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<Ptreservation>> {
        val result = ptreservationService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ptreservationService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPtreservation(@RequestBody request: PtreservationCreateRequest): ResponseEntity<Ptreservation> {
        return try {
            val result = ptreservationService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPtreservations(@RequestBody requests: List<PtreservationCreateRequest>): ResponseEntity<List<Ptreservation>> {
        return try {
            val result = ptreservationService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePtreservation(
        @PathVariable id: Long,
        @RequestBody request: PtreservationUpdateRequest
    ): ResponseEntity<Ptreservation> {
        val updatedRequest = request.copy(id = id)
        val result = ptreservationService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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