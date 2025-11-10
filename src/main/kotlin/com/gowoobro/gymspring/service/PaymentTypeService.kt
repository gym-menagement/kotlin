package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import com.gowoobro.gymspring.repository.PaymenttypeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class PaymenttypeService(private val paymenttypeRepository: PaymenttypeRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Paymenttype> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return paymenttypeRepository.findAll(pageable)
    }

    fun findById(id: Long): Paymenttype? {
        return paymenttypeRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return paymenttypeRepository.count()
    }


    fun findByGym(gym: Long): List<Paymenttype> {
        return paymenttypeRepository.findByGym(gym)
    }

    fun findByName(name: String): List<Paymenttype> {
        return paymenttypeRepository.findByName(name)
    }

    fun findByDate(date: LocalDateTime): List<Paymenttype> {
        return paymenttypeRepository.findByDate(date)
    }


    fun create(request: PaymenttypeCreateRequest): Paymenttype {
        val entity = Paymenttype(
            gym = request.gym,
            name = request.name,
            date = request.date,
        )
        return paymenttypeRepository.save(entity)
    }

    fun createBatch(requests: List<PaymenttypeCreateRequest>): List<Paymenttype> {
        val entities = requests.map { request ->
            Paymenttype(
                gym = request.gym,
                name = request.name,
                date = request.date,
            )
        }
        return paymenttypeRepository.saveAll(entities)
    }

    fun update(request: PaymenttypeUpdateRequest): Paymenttype? {
        val existing = paymenttypeRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            gym = request.gym,
            name = request.name,
            date = request.date,
        )
        return paymenttypeRepository.save(updated)
    }

    fun delete(entity: Paymenttype): Boolean {
        return try {
            paymenttypeRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            paymenttypeRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Paymenttype>): Boolean {
        return try {
            paymenttypeRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}