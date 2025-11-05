package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import com.gowoobro.gymspring.service.HealthService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/health")
class HealthController(private val healthService: HealthService) {

    @GetMapping
    fun getHealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Health>> {
        val result = healthService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getHealth(@PathVariable id: Long): ResponseEntity<Health> {
        val result = healthService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getHealthById(@RequestParam id: String): ResponseEntity<List<Health>> {
        val result = healthService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/category")
    fun getHealthByCategory(@RequestParam category: String): ResponseEntity<List<Health>> {
        val result = healthService.findByCategory(category)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/term")
    fun getHealthByTerm(@RequestParam term: String): ResponseEntity<List<Health>> {
        val result = healthService.findByTerm(term)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getHealthByName(@RequestParam name: String): ResponseEntity<List<Health>> {
        val result = healthService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/count")
    fun getHealthByCount(@RequestParam count: String): ResponseEntity<List<Health>> {
        val result = healthService.findByCount(count)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/cost")
    fun getHealthByCost(@RequestParam cost: String): ResponseEntity<List<Health>> {
        val result = healthService.findByCost(cost)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/discount")
    fun getHealthByDiscount(@RequestParam discount: String): ResponseEntity<List<Health>> {
        val result = healthService.findByDiscount(discount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/costdiscount")
    fun getHealthByCostdiscount(@RequestParam costdiscount: String): ResponseEntity<List<Health>> {
        val result = healthService.findByCostdiscount(costdiscount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/content")
    fun getHealthByContent(@RequestParam content: String): ResponseEntity<List<Health>> {
        val result = healthService.findByContent(content)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getHealthByDate(@RequestParam date: String): ResponseEntity<List<Health>> {
        val result = healthService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealth(@RequestBody request: HealthCreateRequest): ResponseEntity<Health> {
        return try {
            val result = healthService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealths(@RequestBody requests: List<HealthCreateRequest>): ResponseEntity<List<Health>> {
        return try {
            val result = healthService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateHealth(
        @PathVariable id: Long,
        @RequestBody request: HealthUpdateRequest
    ): ResponseEntity<Health> {
        val updatedRequest = request.copy(id = id)
        val result = healthService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteHealth(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = healthService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteHealths(@RequestBody entities: List<Health>): ResponseEntity<Map<String, Boolean>> {
        val success = healthService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}