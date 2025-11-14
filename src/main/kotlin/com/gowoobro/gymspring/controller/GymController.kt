package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Gym
import com.gowoobro.gymspring.entity.GymCreateRequest
import com.gowoobro.gymspring.entity.GymUpdateRequest
import com.gowoobro.gymspring.service.GymService
import com.gowoobro.gymspring.entity.GymResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/gym")
class GymController(
    private val gymService: GymService) {

    private fun toResponse(gym: Gym): GymResponse {
        return GymResponse.from(gym)
    }

    @GetMapping
    fun getGyms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<GymResponse>> {
        val res = gymService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getGym(@PathVariable id: Long): ResponseEntity<GymResponse> {
        val res = gymService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/name")
    fun getGymByName(@RequestParam name: String): ResponseEntity<List<GymResponse>> {
        val res = gymService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getGymByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<GymResponse>> {
        val res = gymService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = gymService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createGym(@RequestBody request: GymCreateRequest): ResponseEntity<GymResponse> {
        return try {
            val res = gymService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createGyms(@RequestBody requests: List<GymCreateRequest>): ResponseEntity<List<GymResponse>> {
        return try {
            val res = gymService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateGym(
        @PathVariable id: Long,
        @RequestBody request: GymUpdateRequest
    ): ResponseEntity<GymResponse> {
        val updatedRequest = request.copy(id = id)
        val res = gymService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteGym(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = gymService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteGyms(@RequestBody entities: List<Gym>): ResponseEntity<Map<String, Boolean>> {
        val success = gymService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}