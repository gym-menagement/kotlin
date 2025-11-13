package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import com.gowoobro.gymspring.service.PaymentService
import com.gowoobro.gymspring.entity.PaymentResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import com.gowoobro.gymspring.entity.OrderResponse
import com.gowoobro.gymspring.service.OrderService
import com.gowoobro.gymspring.entity.MembershipResponse
import com.gowoobro.gymspring.service.MembershipService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/payment")
class PaymentController(
    private val paymentService: PaymentService, private val gymService: GymService, private val orderService: OrderService, private val membershipService: MembershipService) {

    private fun toResponse(payment: Payment):
    PaymentResponse {
        
        val gym = gymService.findById(payment.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        val order = orderService.findById(payment.order)
        val orderResponse = order?.let{ OrderResponse.from(it) }
        
        val membership = membershipService.findById(payment.membership)
        val membershipResponse = membership?.let{ MembershipResponse.from(it) }
        
        return PaymentResponse.from(payment, gymResponse, orderResponse, membershipResponse)
    }

    @GetMapping
    fun getPayments(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PaymentResponse>> {
        val result = paymentService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): ResponseEntity<PaymentResponse> {
        val result = paymentService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymentByGym(@RequestParam gym: Long): ResponseEntity<List<PaymentResponse>> {
        val result = paymentService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/order")
    fun getPaymentByOrder(@RequestParam order: Long): ResponseEntity<List<PaymentResponse>> {
        val result = paymentService.findByOrder(order)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/membership")
    fun getPaymentByMembership(@RequestParam membership: Long): ResponseEntity<List<PaymentResponse>> {
        val result = paymentService.findByMembership(membership)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/cost")
    fun getPaymentByCost(@RequestParam cost: Int): ResponseEntity<List<PaymentResponse>> {
        val result = paymentService.findByCost(cost)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPaymentByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PaymentResponse>> {
        val result = paymentService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymentService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPayment(@RequestBody request: PaymentCreateRequest): ResponseEntity<PaymentResponse> {
        return try {
            val result = paymentService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPayments(@RequestBody requests: List<PaymentCreateRequest>): ResponseEntity<List<PaymentResponse>> {
        return try {
            val result = paymentService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = paymentService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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