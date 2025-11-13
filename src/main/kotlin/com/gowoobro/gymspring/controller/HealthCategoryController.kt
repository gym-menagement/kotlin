package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import com.gowoobro.gymspring.service.HealthcategoryService
import com.gowoobro.gymspring.entity.HealthcategoryResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/healthcategory")
class HealthcategoryController(
    private val healthcategoryService: HealthcategoryService, private val gymService: GymService) {

    private fun toResponse(healthcategory: Healthcategory):
    HealthcategoryResponse {
        
        val gym = gymService.findById(healthcategory.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return HealthcategoryResponse.from(healthcategory, gymResponse)
    }

    @GetMapping
    fun getHealthcategorys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<HealthcategoryResponse>> {
        val result = healthcategoryService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getHealthcategory(@PathVariable id: Long): ResponseEntity<HealthcategoryResponse> {
        val result = healthcategoryService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getHealthcategoryByGym(@RequestParam gym: Long): ResponseEntity<List<HealthcategoryResponse>> {
        val result = healthcategoryService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getHealthcategoryByName(@RequestParam name: String): ResponseEntity<List<HealthcategoryResponse>> {
        val result = healthcategoryService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getHealthcategoryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<HealthcategoryResponse>> {
        val result = healthcategoryService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthcategoryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealthcategory(@RequestBody request: HealthcategoryCreateRequest): ResponseEntity<HealthcategoryResponse> {
        return try {
            val result = healthcategoryService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealthcategorys(@RequestBody requests: List<HealthcategoryCreateRequest>): ResponseEntity<List<HealthcategoryResponse>> {
        return try {
            val result = healthcategoryService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateHealthcategory(
        @PathVariable id: Long,
        @RequestBody request: HealthcategoryUpdateRequest
    ): ResponseEntity<HealthcategoryResponse> {
        val updatedRequest = request.copy(id = id)
        val result = healthcategoryService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteHealthcategory(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = healthcategoryService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteHealthcategorys(@RequestBody entities: List<Healthcategory>): ResponseEntity<Map<String, Boolean>> {
        val success = healthcategoryService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}