package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Payment
import com.gowoobro.gymspring.entity.PaymentCreateRequest
import com.gowoobro.gymspring.entity.PaymentUpdateRequest
import com.gowoobro.gymspring.repository.PaymentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PaymentService(private val paymentRepository: PaymentRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Payment> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return paymentRepository.findAll(pageable)
    }

    fun findById(id: Long): Payment? {
        return paymentRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return paymentRepository.count()
    }


    fun findById(id: String): List<Payment> {
        return paymentRepository.findById(id)
    }

    fun findByGym(gym: String): List<Payment> {
        return paymentRepository.findByGym(gym)
    }

    fun findByOrder(order: String): List<Payment> {
        return paymentRepository.findByOrder(order)
    }

    fun findByMembership(membership: String): List<Payment> {
        return paymentRepository.findByMembership(membership)
    }

    fun findByCost(cost: String): List<Payment> {
        return paymentRepository.findByCost(cost)
    }

    fun findByDate(date: String): List<Payment> {
        return paymentRepository.findByDate(date)
    }


    fun create(request: PaymentCreateRequest): Payment {
        val entity = Payment()
        return paymentRepository.save(entity)
    }

    fun createBatch(requests: List<PaymentCreateRequest>): List<Payment> {
        val entities = requests.map { request ->
            Payment()
        }
        return paymentRepository.saveAll(entities)
    }

    fun update(request: PaymentUpdateRequest): Payment? {
        val existing = paymentRepository.findById(request.id).orElse(null) ?: return null
        return paymentRepository.save(existing)
    }

    fun delete(entity: Payment): Boolean {
        return try {
            paymentRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            paymentRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Payment>): Boolean {
        return try {
            paymentRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}