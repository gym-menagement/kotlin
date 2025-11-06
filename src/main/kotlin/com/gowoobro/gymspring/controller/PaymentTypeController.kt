package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import com.gowoobro.gymspring.service.PaymenttypeService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/paymenttype")
class PaymenttypeController(private val paymenttypeService: PaymenttypeService) {

    @GetMapping
    fun getPaymenttypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Paymenttype>> {
        val result = paymenttypeService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getPaymenttype(@PathVariable id: Long): ResponseEntity<Paymenttype> {
        val result = paymenttypeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }







    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymenttypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPaymenttype(@RequestBody request: PaymenttypeCreateRequest): ResponseEntity<Paymenttype> {
        return try {
            val result = paymenttypeService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPaymenttypes(@RequestBody requests: List<PaymenttypeCreateRequest>): ResponseEntity<List<Paymenttype>> {
        return try {
            val result = paymenttypeService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePaymenttype(
        @PathVariable id: Long,
        @RequestBody request: PaymenttypeUpdateRequest
    ): ResponseEntity<Paymenttype> {
        val updatedRequest = request.copy(id = id)
        val result = paymenttypeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePaymenttype(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = paymenttypeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deletePaymenttypes(@RequestBody entities: List<Paymenttype>): ResponseEntity<Map<String, Boolean>> {
        val success = paymenttypeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}