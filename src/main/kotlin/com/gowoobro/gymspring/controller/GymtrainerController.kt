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

    @GetMapping
    fun getGymtrainers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<GymtrainerResponse>> {
        val res = gymtrainerService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
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