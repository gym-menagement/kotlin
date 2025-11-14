package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import com.gowoobro.gymspring.service.PaymentService
import com.gowoobro.gymspring.entity.PaymentResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/payment")
class PaymentController(
    private val paymentService: PaymentService) {

    private fun toResponse(payment: Payment): PaymentResponse {
        return PaymentResponse.from(payment)
    }

    @GetMapping
    fun getPayments(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PaymentResponse>> {
        val res = paymentService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): ResponseEntity<PaymentResponse> {
        val res = paymentService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymentByGym(@RequestParam gym: Long): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/order")
    fun getPaymentByOrder(@RequestParam order: Long): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByOrder(order)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/membership")
    fun getPaymentByMembership(@RequestParam membership: Long): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByMembership(membership)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/cost")
    fun getPaymentByCost(@RequestParam cost: Int): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByCost(cost)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPaymentByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymentService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPayment(@RequestBody request: PaymentCreateRequest): ResponseEntity<PaymentResponse> {
        return try {
            val res = paymentService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPayments(@RequestBody requests: List<PaymentCreateRequest>): ResponseEntity<List<PaymentResponse>> {
        return try {
            val res = paymentService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePayment(
        @PathVariable id: Long,
        @RequestBody request: PaymentUpdateRequest
    ): ResponseEntity<PaymentResponse> {
        val updatedRequest = request.copy(id = id)
        val res = paymentService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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