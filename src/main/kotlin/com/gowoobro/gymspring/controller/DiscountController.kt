package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import com.gowoobro.gymspring.service.DiscountService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/discount")
class DiscountController(private val discountService: DiscountService) {
    
    @GetMapping
    fun getDiscounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Discount>> {
        val result = discountService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/{id}")
    fun getDiscount(@PathVariable id: Long): ResponseEntity<Discount> {
        val result = discountService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/search/name")
    fun getDiscountsByMobileSearchName(@RequestParam name: String): ResponseEntity<List<Discount>> {
        val result = discountService.findByNameContaining(name)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = discountService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }
    
    @PostMapping
    fun createDiscount(@RequestBody request: DiscountCreateRequest): ResponseEntity<Discount> {
        return try {
            val result = discountService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PostMapping("/batch")
    fun createDiscounts(@RequestBody requests: List<DiscountCreateRequest>): ResponseEntity<List<Discount>> {
        return try {
            val result = discountService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PutMapping("/{id}")
    fun updateDiscount(
        @PathVariable id: Long,
        @RequestBody request: DiscountUpdateRequest
    ): ResponseEntity<Discount> {
        val updatedRequest = request.copy(id = id)
        val result = discountService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun deleteDiscount(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = discountService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }
    
    @DeleteMapping("/batch")
    fun deleteDiscounts(@RequestBody entities: List<Discount>): ResponseEntity<Map<String, Boolean>> {
        val success = discountService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}