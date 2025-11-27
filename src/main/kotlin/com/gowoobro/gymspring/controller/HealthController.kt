package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import com.gowoobro.gymspring.entity.HealthPatchRequest
import com.gowoobro.gymspring.service.HealthService
import com.gowoobro.gymspring.entity.HealthResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/health")
class HealthController(
    private val healthService: HealthService) {

    private fun toResponse(health: Health): HealthResponse {
        return HealthResponse.from(health)
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
    fun getHealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) category: Long?,
        @RequestParam(required = false) term: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) count: Int?,
        @RequestParam(required = false) cost: Int?,
        @RequestParam(required = false) discount: Long?,
        @RequestParam(required = false) costdiscount: Int?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (category != null || term != null || name != null || count != null || cost != null || discount != null || costdiscount != null || content != null || gym != null || startdate != null || enddate != null || false) {
            var filtered = healthService.findAll(0, Int.MAX_VALUE).content
            if (category != null) {
                filtered = filtered.filter { it.categoryId == category }
            }
            if (term != null) {
                filtered = filtered.filter { it.termId == term }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (count != null) {
                filtered = filtered.filter { it.count == count }
            }
            if (cost != null) {
                filtered = filtered.filter { it.cost == cost }
            }
            if (discount != null) {
                filtered = filtered.filter { it.discountId == discount }
            }
            if (costdiscount != null) {
                filtered = filtered.filter { it.costdiscount == costdiscount }
            }
            if (content != null) {
                filtered = filtered.filter { it.content == content }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            healthService.findAll(0, Int.MAX_VALUE).content
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
    fun getHealth(@PathVariable id: Long): ResponseEntity<HealthResponse> {
        val res = healthService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/category")
    fun getHealthByCategory(@RequestParam category: Long): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByCategory(category)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getHealthByTerm(@RequestParam term: Long): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByTerm(term)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getHealthByName(@RequestParam name: String): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/count")
    fun getHealthByCount(@RequestParam count: Int): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByCount(count)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/cost")
    fun getHealthByCost(@RequestParam cost: Int): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByCost(cost)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getHealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByDiscount(discount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/costdiscount")
    fun getHealthByCostdiscount(@RequestParam costdiscount: Int): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByCostdiscount(costdiscount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getHealthByContent(@RequestParam content: String): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByContent(content)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getHealthByGym(@RequestParam gym: Long): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getHealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<HealthResponse>> {
        val res = healthService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = healthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createHealth(@RequestBody request: HealthCreateRequest): ResponseEntity<HealthResponse> {
        return try {
            val res = healthService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createHealths(@RequestBody requests: List<HealthCreateRequest>): ResponseEntity<List<HealthResponse>> {
        return try {
            val res = healthService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateHealth(
        @PathVariable id: Long,
        @RequestBody request: HealthUpdateRequest
    ): ResponseEntity<HealthResponse> {
        val updatedRequest = request.copy(id = id)
        val res = healthService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchHealth(
        @PathVariable id: Long,
        @RequestBody request: HealthPatchRequest
    ): ResponseEntity<HealthResponse> {
        val patchRequest = request.copy(id = id)
        val res = healthService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteHealth(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = healthService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteHealths(@RequestBody entities: List<Health>): ResponseEntity<Map<String, Boolean>> {
        val success = healthService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}