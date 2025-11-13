package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import com.gowoobro.gymspring.service.PtreservationService
import com.gowoobro.gymspring.entity.PtreservationResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ptreservation.Status


@RestController
@RequestMapping("/api/ptreservation")
class PtreservationController(
    private val ptreservationService: PtreservationService, private val userService: UserService, private val gymService: GymService) {

    private fun toResponse(ptreservation: Ptreservation):
    PtreservationResponse {
        
        val traineruser = userService.findById(ptreservation.trainer)
        val traineruserResponse = traineruser?.let{ UserResponse.from(it) }
        
        val memberuser = userService.findById(ptreservation.member)
        val memberuserResponse = memberuser?.let{ UserResponse.from(it) }
        
        val gym = gymService.findById(ptreservation.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return PtreservationResponse.from(ptreservation, traineruserResponse, memberuserResponse, gymResponse)
    }

    @GetMapping
    fun getPtreservations(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PtreservationResponse>> {
        val result = ptreservationService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPtreservation(@PathVariable id: Long): ResponseEntity<PtreservationResponse> {
        val result = ptreservationService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getPtreservationByTrainer(@RequestParam trainer: Long): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByTrainer(trainer)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/member")
    fun getPtreservationByMember(@RequestParam member: Long): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByMember(member)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getPtreservationByGym(@RequestParam gym: Long): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/reservationdate")
    fun getPtreservationByReservationdate(@RequestParam reservationdate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByReservationdate(reservationdate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/starttime")
    fun getPtreservationByStarttime(@RequestParam starttime: String): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByStarttime(starttime)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/endtime")
    fun getPtreservationByEndtime(@RequestParam endtime: String): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByEndtime(endtime)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getPtreservationByDuration(@RequestParam duration: Int): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByDuration(duration)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getPtreservationByStatus(@RequestParam status: Status): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByStatus(status)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getPtreservationByNote(@RequestParam note: String): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByNote(note)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/cancelreason")
    fun getPtreservationByCancelreason(@RequestParam cancelreason: String): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByCancelreason(cancelreason)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getPtreservationByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByCreateddate(createddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getPtreservationByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPtreservationByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PtreservationResponse>> {
        val result = ptreservationService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ptreservationService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPtreservation(@RequestBody request: PtreservationCreateRequest): ResponseEntity<PtreservationResponse> {
        return try {
            val result = ptreservationService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPtreservations(@RequestBody requests: List<PtreservationCreateRequest>): ResponseEntity<List<PtreservationResponse>> {
        return try {
            val result = ptreservationService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = ptreservationService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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