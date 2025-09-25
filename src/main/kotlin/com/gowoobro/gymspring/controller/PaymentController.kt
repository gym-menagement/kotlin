package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import com.gowoobro.gymspring.service.PaymentService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payment")
class PaymentController(private val paymentService: PaymentService) {
    
    @GetMapping
    fun getPayments(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Payment>> {
        val result = paymentService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): ResponseEntity<Payment> {
        val result = paymentService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymentService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }
    
    @PostMapping
    fun createPayment(@RequestBody request: PaymentCreateRequest): ResponseEntity<Payment> {
        return try {
            val result = paymentService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PostMapping("/batch")
    fun createPayments(@RequestBody requests: List<PaymentCreateRequest>): ResponseEntity<List<Payment>> {
        return try {
            val result = paymentService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PutMapping("/{id}")
    fun updatePayment(
        @PathVariable id: Long,
        @RequestBody request: PaymentUpdateRequest
    ): ResponseEntity<Payment> {
        val updatedRequest = request.copy(id = id)
        val result = paymentService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun deletePayment(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = paymentService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }
    
    @DeleteMapping("/batch")
    fun deletePayments(@RequestBody entities: List<Payment>): ResponseEntity<Map<String, Boolean>> {
        val success = paymentService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}