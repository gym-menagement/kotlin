package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import com.gowoobro.gymspring.service.DiscountService
import com.gowoobro.gymspring.entity.DiscountResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/discount")
class DiscountController(
    private val discountService: DiscountService) {

    private fun toResponse(discount: Discount):
    DiscountResponse {
        
        return DiscountResponse.from(discount)
    }

    @GetMapping
    fun getDiscounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<DiscountResponse>> {
        val result = discountService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getDiscount(@PathVariable id: Long): ResponseEntity<DiscountResponse> {
        val result = discountService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/name")
    fun getDiscountByName(@RequestParam name: String): ResponseEntity<List<DiscountResponse>> {
        val result = discountService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getDiscountByDiscount(@RequestParam discount: Int): ResponseEntity<List<DiscountResponse>> {
        val result = discountService.findByDiscount(discount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getDiscountByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<DiscountResponse>> {
        val result = discountService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = discountService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDiscount(@RequestBody request: DiscountCreateRequest): ResponseEntity<DiscountResponse> {
        return try {
            val result = discountService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDiscounts(@RequestBody requests: List<DiscountCreateRequest>): ResponseEntity<List<DiscountResponse>> {
        return try {
            val result = discountService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateDiscount(
        @PathVariable id: Long,
        @RequestBody request: DiscountUpdateRequest
    ): ResponseEntity<DiscountResponse> {
        val updatedRequest = request.copy(id = id)
        val result = discountService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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