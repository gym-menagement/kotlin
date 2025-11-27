package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Paymentform
import com.gowoobro.gymspring.entity.PaymentformCreateRequest
import com.gowoobro.gymspring.entity.PaymentformUpdateRequest
import com.gowoobro.gymspring.entity.PaymentformPatchRequest
import com.gowoobro.gymspring.repository.PaymentformRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class PaymentformService(private val paymentformRepository: PaymentformRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Paymentform> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return paymentformRepository.findAll(pageable)
    }

    fun findById(id: Long): Paymentform? {
        return paymentformRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return paymentformRepository.count()
    }


    fun findByGym(gym: Long): List<Paymentform> {
        return paymentformRepository.findBygymId(gym)
    }

    fun findByPayment(payment: Long): List<Paymentform> {
        return paymentformRepository.findBypaymentId(payment)
    }

    fun findByType(paymenttype: Long): List<Paymentform> {
        return paymentformRepository.findBytypeId(paymenttype)
    }

    fun findByCost(cost: Int): List<Paymentform> {
        return paymentformRepository.findByCost(cost)
    }

    fun findByDate(date: LocalDateTime): List<Paymentform> {
        return paymentformRepository.findByDate(date)
    }


    fun create(request: PaymentformCreateRequest): Paymentform {
        val entity = Paymentform(
            gymId = request.gym,
            paymentId = request.payment,
            typeId = request.type,
            cost = request.cost,
            date = request.date,
        )
        return paymentformRepository.save(entity)
    }

    fun createBatch(requests: List<PaymentformCreateRequest>): List<Paymentform> {
        val entities = requests.map { request ->
            Paymentform(
                gymId = request.gym,
                paymentId = request.payment,
                typeId = request.type,
                cost = request.cost,
                date = request.date,
            )
        }
        return paymentformRepository.saveAll(entities)
    }

    fun update(request: PaymentformUpdateRequest): Paymentform? {
        val existing = paymentformRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            paymentId = request.payment,
            typeId = request.type,
            cost = request.cost,
            date = request.date,
        )
        return paymentformRepository.save(updated)
    }

    fun delete(entity: Paymentform): Boolean {
        return try {
            paymentformRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            paymentformRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Paymentform>): Boolean {
        return try {
            paymentformRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: PaymentformPatchRequest): Paymentform? {
        val existing = paymentformRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym ?: existing.gymId,
            paymentId = request.payment ?: existing.paymentId,
            typeId = request.type ?: existing.typeId,
            cost = request.cost ?: existing.cost,
            date = request.date ?: existing.date,
        )
        return paymentformRepository.save(updated)
    }
}