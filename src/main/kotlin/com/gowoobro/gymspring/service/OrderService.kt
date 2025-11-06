package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Order
import com.gowoobro.gymspring.entity.OrderCreateRequest
import com.gowoobro.gymspring.entity.OrderUpdateRequest
import com.gowoobro.gymspring.repository.OrderRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(private val orderRepository: OrderRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Order> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return orderRepository.findAll(pageable)
    }

    fun findById(id: Long): Order? {
        return orderRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return orderRepository.count()
    }






    fun create(request: OrderCreateRequest): Order {
        val entity = Order()
        return orderRepository.save(entity)
    }

    fun createBatch(requests: List<OrderCreateRequest>): List<Order> {
        val entities = requests.map { request ->
            Order()
        }
        return orderRepository.saveAll(entities)
    }

    fun update(request: OrderUpdateRequest): Order? {
        val existing = orderRepository.findById(request.id).orElse(null) ?: return null
        return orderRepository.save(existing)
    }

    fun delete(entity: Order): Boolean {
        return try {
            orderRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            orderRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Order>): Boolean {
        return try {
            orderRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}