package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import com.gowoobro.gymspring.entity.PaymentformPatchRequest
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
    fun getPaymentforms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) payment: Long?,
        @RequestParam(required = false) type: Long?,
        @RequestParam(required = false) cost: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || payment != null || type != null || cost != null || startdate != null || enddate != null || false) {
            var filtered = paymentformService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (payment != null) {
                filtered = filtered.filter { it.paymentId == payment }
            }
            if (type != null) {
                filtered = filtered.filter { it.typeId == type }
            }
            if (cost != null) {
                filtered = filtered.filter { it.cost == cost }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            paymentformService.findAll(0, Int.MAX_VALUE).content
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

    @PatchMapping("/{id}")
    fun patchPaymentform(
        @PathVariable id: Long,
        @RequestBody request: PaymentformPatchRequest
    ): ResponseEntity<PaymentformResponse> {
        val patchRequest = request.copy(id = id)
        val res = paymentformService.patch(patchRequest)
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