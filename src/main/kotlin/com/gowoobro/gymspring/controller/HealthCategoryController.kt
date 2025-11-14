package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import com.gowoobro.gymspring.service.HealthcategoryService
import com.gowoobro.gymspring.entity.HealthcategoryResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/healthcategory")
class HealthcategoryController(
    private val healthcategoryService: HealthcategoryService) {

    private fun toResponse(healthcategory: Healthcategory): HealthcategoryResponse {
        return HealthcategoryResponse.from(healthcategory)
    }

    @GetMapping
    fun getHealthcategorys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<HealthcategoryResponse>> {
        val res = healthcategoryService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getHealthcategory(@PathVariable id: Long): ResponseEntity<HealthcategoryResponse> {
        val res = healthcategoryService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getHealthcategoryByGym(@RequestParam gym: Long): ResponseEntity<List<HealthcategoryResponse>> {
        val res = healthcategoryService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getHealthcategoryByName(@RequestParam name: String): ResponseEntity<List<HealthcategoryResponse>> {
        val res = healthcategoryService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getHealthcategoryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<HealthcategoryResponse>> {
        val res = healthcategoryService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthcategoryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealthcategory(@RequestBody request: HealthcategoryCreateRequest): ResponseEntity<HealthcategoryResponse> {
        return try {
            val res = healthcategoryService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealthcategorys(@RequestBody requests: List<HealthcategoryCreateRequest>): ResponseEntity<List<HealthcategoryResponse>> {
        return try {
            val res = healthcategoryService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = healthcategoryService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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