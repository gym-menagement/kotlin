package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import com.gowoobro.gymspring.service.HealthcategoryService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/healthcategory")
class HealthcategoryController(private val healthcategoryService: HealthcategoryService) {

    @GetMapping
    fun getHealthcategorys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Healthcategory>> {
        val result = healthcategoryService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getHealthcategory(@PathVariable id: Long): ResponseEntity<Healthcategory> {
        val result = healthcategoryService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getHealthcategoryByGym(@RequestParam gym: Long): ResponseEntity<List<Healthcategory>> {
        val result = healthcategoryService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getHealthcategoryByName(@RequestParam name: String): ResponseEntity<List<Healthcategory>> {
        val result = healthcategoryService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getHealthcategoryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Healthcategory>> {
        val result = healthcategoryService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthcategoryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealthcategory(@RequestBody request: HealthcategoryCreateRequest): ResponseEntity<Healthcategory> {
        return try {
            val result = healthcategoryService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealthcategorys(@RequestBody requests: List<HealthcategoryCreateRequest>): ResponseEntity<List<Healthcategory>> {
        return try {
            val result = healthcategoryService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateHealthcategory(
        @PathVariable id: Long,
        @RequestBody request: HealthcategoryUpdateRequest
    ): ResponseEntity<Healthcategory> {
        val updatedRequest = request.copy(id = id)
        val result = healthcategoryService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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