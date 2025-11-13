package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import com.gowoobro.gymspring.service.HealthService
import com.gowoobro.gymspring.entity.HealthResponse
import com.gowoobro.gymspring.entity.HealthcategoryResponse
import com.gowoobro.gymspring.service.HealthcategoryService
import com.gowoobro.gymspring.entity.TermResponse
import com.gowoobro.gymspring.service.TermService
import com.gowoobro.gymspring.entity.DiscountResponse
import com.gowoobro.gymspring.service.DiscountService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/health")
class HealthController(
    private val healthService: HealthService, private val healthcategoryService: HealthcategoryService, private val termService: TermService, private val discountService: DiscountService) {

    private fun toResponse(health: Health):
    HealthResponse {
        
        val healthcategory = healthcategoryService.findById(health.category)
        val healthcategoryResponse = healthcategory?.let{ HealthcategoryResponse.from(it) }
        
        val term = termService.findById(health.term)
        val termResponse = term?.let{ TermResponse.from(it) }
        
        val discount = discountService.findById(health.discount)
        val discountResponse = discount?.let{ DiscountResponse.from(it) }
        
        return HealthResponse.from(health, healthcategoryResponse, termResponse, discountResponse)
    }

    @GetMapping
    fun getHealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<HealthResponse>> {
        val result = healthService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getHealth(@PathVariable id: Long): ResponseEntity<HealthResponse> {
        val result = healthService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/category")
    fun getHealthByCategory(@RequestParam category: Long): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByCategory(category)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getHealthByTerm(@RequestParam term: Long): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByTerm(term)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getHealthByName(@RequestParam name: String): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/count")
    fun getHealthByCount(@RequestParam count: Int): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByCount(count)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/cost")
    fun getHealthByCost(@RequestParam cost: Int): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByCost(cost)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getHealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByDiscount(discount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/costdiscount")
    fun getHealthByCostdiscount(@RequestParam costdiscount: Int): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByCostdiscount(costdiscount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getHealthByContent(@RequestParam content: String): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByContent(content)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getHealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<HealthResponse>> {
        val result = healthService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealth(@RequestBody request: HealthCreateRequest): ResponseEntity<HealthResponse> {
        return try {
            val result = healthService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealths(@RequestBody requests: List<HealthCreateRequest>): ResponseEntity<List<HealthResponse>> {
        return try {
            val result = healthService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateHealth(
        @PathVariable id: Long,
        @RequestBody request: HealthUpdateRequest
    ): ResponseEntity<HealthResponse> {
        val updatedRequest = request.copy(id = id)
        val result = healthService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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