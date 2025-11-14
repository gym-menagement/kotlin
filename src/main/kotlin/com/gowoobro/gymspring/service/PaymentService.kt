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
import java.time.LocalDateTime



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


    fun findByGym(gym: Long): List<Payment> {
        return paymentRepository.findBygymId(gym)
    }

    fun findByOrder(order: Long): List<Payment> {
        return paymentRepository.findByorderId(order)
    }

    fun findByMembership(membership: Long): List<Payment> {
        return paymentRepository.findBymembershipId(membership)
    }

    fun findByCost(cost: Int): List<Payment> {
        return paymentRepository.findByCost(cost)
    }

    fun findByDate(date: LocalDateTime): List<Payment> {
        return paymentRepository.findByDate(date)
    }


    fun create(request: PaymentCreateRequest): Payment {
        val entity = Payment(
            gymId = request.gym,
            orderId = request.order,
            membershipId = request.membership,
            cost = request.cost,
            date = request.date,
        )
        return paymentRepository.save(entity)
    }

    fun createBatch(requests: List<PaymentCreateRequest>): List<Payment> {
        val entities = requests.map { request ->
            Payment(
                gymId = request.gym,
                orderId = request.order,
                membershipId = request.membership,
                cost = request.cost,
                date = request.date,
            )
        }
        return paymentRepository.saveAll(entities)
    }

    fun update(request: PaymentUpdateRequest): Payment? {
        val existing = paymentRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            orderId = request.order,
            membershipId = request.membership,
            cost = request.cost,
            date = request.date,
        )
        return paymentRepository.save(updated)
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