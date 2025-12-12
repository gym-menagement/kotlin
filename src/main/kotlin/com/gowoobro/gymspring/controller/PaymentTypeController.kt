package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import com.gowoobro.gymspring.entity.PaymenttypePatchRequest
import com.gowoobro.gymspring.service.PaymenttypeService
import com.gowoobro.gymspring.entity.PaymenttypeResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/paymenttype")
class PaymenttypeController(
    private val paymenttypeService: PaymenttypeService) {

    private fun toResponse(paymenttype: Paymenttype): PaymenttypeResponse {
        return PaymenttypeResponse.from(paymenttype)
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
    fun getPaymenttypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || name != null || startdate != null || enddate != null || false) {
            var filtered = paymenttypeService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gym == gym }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            paymenttypeService.findAll(0, Int.MAX_VALUE).content
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
    fun getPaymenttype(@PathVariable id: Long): ResponseEntity<PaymenttypeResponse> {
        val res = paymenttypeService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getPaymenttypeByGym(@RequestParam gym: Long): ResponseEntity<List<PaymenttypeResponse>> {
        val res = paymenttypeService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getPaymenttypeByName(@RequestParam name: String): ResponseEntity<List<PaymenttypeResponse>> {
        val res = paymenttypeService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPaymenttypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PaymenttypeResponse>> {
        val res = paymenttypeService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = paymenttypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPaymenttype(@RequestBody request: PaymenttypeCreateRequest): ResponseEntity<PaymenttypeResponse> {
        return try {
            val res = paymenttypeService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPaymenttypes(@RequestBody requests: List<PaymenttypeCreateRequest>): ResponseEntity<List<PaymenttypeResponse>> {
        return try {
            val res = paymenttypeService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePaymenttype(
        @PathVariable id: Long,
        @RequestBody request: PaymenttypeUpdateRequest
    ): ResponseEntity<PaymenttypeResponse> {
        val updatedRequest = request.copy(id = id)
        val res = paymenttypeService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchPaymenttype(
        @PathVariable id: Long,
        @RequestBody request: PaymenttypePatchRequest
    ): ResponseEntity<PaymenttypeResponse> {
        val patchRequest = request.copy(id = id)
        val res = paymenttypeService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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