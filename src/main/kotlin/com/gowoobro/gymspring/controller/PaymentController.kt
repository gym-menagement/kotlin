package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import com.gowoobro.gymspring.entity.PaymentPatchRequest
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

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getPayments(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) order: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) cost: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || order != null || user != null || cost != null || startdate != null || enddate != null || false) {
            var filtered = paymentService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gym == gym }
            }
            if (order != null) {
                filtered = filtered.filter { it.order == order }
            }
            if (user != null) {
                filtered = filtered.filter { it.user == user }
            }
            if (cost != null) {
                filtered = filtered.filter { it.cost == cost }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            paymentService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
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

    @GetMapping("/search/user")
    fun getPaymentByUser(@RequestParam user: Long): ResponseEntity<List<PaymentResponse>> {
        val res = paymentService.findByUser(user)
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

    @PatchMapping("/{id}")
    fun patchPayment(
        @PathVariable id: Long,
        @RequestBody request: PaymentPatchRequest
    ): ResponseEntity<PaymentResponse> {
        val patchRequest = request.copy(id = id)
        val res = paymentService.patch(patchRequest)
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