package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import com.gowoobro.gymspring.service.PaymentformService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/paymentform")
class PaymentformController(private val paymentformService: PaymentformService) {

    @GetMapping
    fun getPaymentforms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Paymentform>> {
        val result = paymentformService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getPaymentform(@PathVariable id: Long): ResponseEntity<Paymentform> {
        val result = paymentformService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymentformByGym(@RequestParam gym: Long): ResponseEntity<List<Paymentform>> {
        val result = paymentformService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/payment")
    fun getPaymentformByPayment(@RequestParam payment: Long): ResponseEntity<List<Paymentform>> {
        val result = paymentformService.findByPayment(payment)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getPaymentformByType(@RequestParam type: Long): ResponseEntity<List<Paymentform>> {
        val result = paymentformService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/cost")
    fun getPaymentformByCost(@RequestParam cost: Int): ResponseEntity<List<Paymentform>> {
        val result = paymentformService.findByCost(cost)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getPaymentformByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Paymentform>> {
        val result = paymentformService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymentformService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPaymentform(@RequestBody request: PaymentformCreateRequest): ResponseEntity<Paymentform> {
        return try {
            val result = paymentformService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPaymentforms(@RequestBody requests: List<PaymentformCreateRequest>): ResponseEntity<List<Paymentform>> {
        return try {
            val result = paymentformService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePaymentform(
        @PathVariable id: Long,
        @RequestBody request: PaymentformUpdateRequest
    ): ResponseEntity<Paymentform> {
        val updatedRequest = request.copy(id = id)
        val result = paymentformService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePaymentform(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = paymentformService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deletePaymentforms(@RequestBody entities: List<Paymentform>): ResponseEntity<Map<String, Boolean>> {
        val success = paymentformService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}