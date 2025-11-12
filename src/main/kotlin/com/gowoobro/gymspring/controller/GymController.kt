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
class GymController(private val gymService: GymService) {

    @GetMapping
    fun getGyms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<GymResponse>> {
        val result = gymService.findAll(page, pageSize)
        val responsePage = result.map { GymResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getGym(@PathVariable id: Long): ResponseEntity<GymResponse> {
        val result = gymService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(GymResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/name")
    fun getGymByName(@RequestParam name: String): ResponseEntity<List<GymResponse>> {
        val result = gymService.findByName(name)
        return ResponseEntity.ok(result.map { GymResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getGymByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<GymResponse>> {
        val result = gymService.findByDate(date)
        return ResponseEntity.ok(result.map { GymResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = gymService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createGym(@RequestBody request: GymCreateRequest): ResponseEntity<GymResponse> {
        return try {
            val result = gymService.create(request)
            ResponseEntity.ok(GymResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createGyms(@RequestBody requests: List<GymCreateRequest>): ResponseEntity<List<GymResponse>> {
        return try {
            val result = gymService.createBatch(requests)
            return ResponseEntity.ok(result.map { GymResponse.from(it) } )
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
        val result = gymService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(GymResponse.from(result))
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