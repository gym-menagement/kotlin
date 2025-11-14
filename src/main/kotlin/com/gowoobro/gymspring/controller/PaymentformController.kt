package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import com.gowoobro.gymspring.service.PaymentformService
import com.gowoobro.gymspring.entity.PaymentformResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/paymentform")
class PaymentformController(
    private val paymentformService: PaymentformService) {

    private fun toResponse(paymentform: Paymentform): PaymentformResponse {
        return PaymentformResponse.from(paymentform)
    }

    @GetMapping
    fun getPaymentforms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PaymentformResponse>> {
        val res = paymentformService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPaymentform(@PathVariable id: Long): ResponseEntity<PaymentformResponse> {
        val res = paymentformService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymentformByGym(@RequestParam gym: Long): ResponseEntity<List<PaymentformResponse>> {
        val res = paymentformService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/payment")
    fun getPaymentformByPayment(@RequestParam payment: Long): ResponseEntity<List<PaymentformResponse>> {
        val res = paymentformService.findByPayment(payment)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getPaymentformByType(@RequestParam type: Long): ResponseEntity<List<PaymentformResponse>> {
        val res = paymentformService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/cost")
    fun getPaymentformByCost(@RequestParam cost: Int): ResponseEntity<List<PaymentformResponse>> {
        val res = paymentformService.findByCost(cost)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPaymentformByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PaymentformResponse>> {
        val res = paymentformService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymentformService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPaymentform(@RequestBody request: PaymentformCreateRequest): ResponseEntity<PaymentformResponse> {
        return try {
            val res = paymentformService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPaymentforms(@RequestBody requests: List<PaymentformCreateRequest>): ResponseEntity<List<PaymentformResponse>> {
        return try {
            val res = paymentformService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePaymentform(
        @PathVariable id: Long,
        @RequestBody request: PaymentformUpdateRequest
    ): ResponseEntity<PaymentformResponse> {
        val updatedRequest = request.copy(id = id)
        val res = paymentformService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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