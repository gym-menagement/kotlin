package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import com.gowoobro.gymspring.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/order")
class OrderController(private val orderService: OrderService) {

    @GetMapping
    fun getOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Order>> {
        val result = orderService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): ResponseEntity<Order> {
        val result = orderService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getOrderById(@RequestParam id: String): ResponseEntity<List<Order>> {
        val result = orderService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/membership")
    fun getOrderByMembership(@RequestParam membership: String): ResponseEntity<List<Order>> {
        val result = orderService.findByMembership(membership)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getOrderByDate(@RequestParam date: String): ResponseEntity<List<Order>> {
        val result = orderService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = orderService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createOrder(@RequestBody request: OrderCreateRequest): ResponseEntity<Order> {
        return try {
            val result = orderService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createOrders(@RequestBody requests: List<OrderCreateRequest>): ResponseEntity<List<Order>> {
        return try {
            val result = orderService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateOrder(
        @PathVariable id: Long,
        @RequestBody request: OrderUpdateRequest
    ): ResponseEntity<Order> {
        val updatedRequest = request.copy(id = id)
        val result = orderService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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