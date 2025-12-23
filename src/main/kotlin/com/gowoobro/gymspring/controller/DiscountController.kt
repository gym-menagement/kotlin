package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import com.gowoobro.gymspring.entity.DiscountPatchRequest
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

    private fun toResponse(discount: Discount): DiscountResponse {
        return DiscountResponse.from(discount)
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
    fun getDiscounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) discount: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || name != null || discount != null || startdate != null || enddate != null || false) {
            var filtered = discountService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (discount != null) {
                filtered = filtered.filter { it.discount == discount }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            discountService.findAll(0, Int.MAX_VALUE).content
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
    fun getDiscount(@PathVariable id: Long): ResponseEntity<DiscountResponse> {
        val res = discountService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getDiscountByGym(@RequestParam gym: Long): ResponseEntity<List<DiscountResponse>> {
        val res = discountService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getDiscountByName(@RequestParam name: String): ResponseEntity<List<DiscountResponse>> {
        val res = discountService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getDiscountByDiscount(@RequestParam discount: Int): ResponseEntity<List<DiscountResponse>> {
        val res = discountService.findByDiscount(discount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getDiscountByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<DiscountResponse>> {
        val res = discountService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = discountService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDiscount(@RequestBody request: DiscountCreateRequest): ResponseEntity<DiscountResponse> {
        return try {
            val res = discountService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDiscounts(@RequestBody requests: List<DiscountCreateRequest>): ResponseEntity<List<DiscountResponse>> {
        return try {
            val res = discountService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = discountService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchDiscount(
        @PathVariable id: Long,
        @RequestBody request: DiscountPatchRequest
    ): ResponseEntity<DiscountResponse> {
        val patchRequest = request.copy(id = id)
        val res = discountService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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