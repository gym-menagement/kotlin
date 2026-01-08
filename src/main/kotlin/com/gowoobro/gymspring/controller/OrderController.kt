package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import com.gowoobro.gymspring.entity.OrderPatchRequest
import com.gowoobro.gymspring.service.OrderService
import com.gowoobro.gymspring.entity.OrderResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/order")
class OrderController(
    private val orderService: OrderService) {

    private fun toResponse(order: Order): OrderResponse {
        return OrderResponse.from(order)
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
    fun getOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) health: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || gym != null || health != null || startdate != null || enddate != null || false) {
            var filtered = orderService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (health != null) {
                filtered = filtered.filter { it.healthId == health }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            orderService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        val res = orderService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getOrderByUser(@RequestParam user: Long): ResponseEntity<List<OrderResponse>> {
        val res = orderService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getOrderByGym(@RequestParam gym: Long): ResponseEntity<List<OrderResponse>> {
        val res = orderService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/health")
    fun getOrderByHealth(@RequestParam health: Long): ResponseEntity<List<OrderResponse>> {
        val res = orderService.findByHealth(health)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getOrderByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<OrderResponse>> {
        val res = orderService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = orderService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createOrder(@RequestBody request: OrderCreateRequest): ResponseEntity<OrderResponse> {
        return try {
            val res = orderService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createOrders(@RequestBody requests: List<OrderCreateRequest>): ResponseEntity<List<OrderResponse>> {
        return try {
            val res = orderService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateOrder(
        @PathVariable id: Long,
        @RequestBody request: OrderUpdateRequest
    ): ResponseEntity<OrderResponse> {
        val updatedRequest = request.copy(id = id)
        val res = orderService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchOrder(
        @PathVariable id: Long,
        @RequestBody request: OrderPatchRequest
    ): ResponseEntity<OrderResponse> {
        val patchRequest = request.copy(id = id)
        val res = orderService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = orderService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteOrders(@RequestBody entities: List<Order>): ResponseEntity<Map<String, Boolean>> {
        val success = orderService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}