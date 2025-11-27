package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import com.gowoobro.gymspring.entity.DiscountPatchRequest
import com.gowoobro.gymspring.repository.DiscountRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class DiscountService(private val discountRepository: DiscountRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Discount> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return discountRepository.findAll(pageable)
    }

    fun findById(id: Long): Discount? {
        return discountRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return discountRepository.count()
    }


    fun findByGym(gym: Long): List<Discount> {
        return discountRepository.findBygymId(gym)
    }

    fun findByName(name: String): List<Discount> {
        return discountRepository.findByName(name)
    }

    fun findByDiscount(discount: Int): List<Discount> {
        return discountRepository.findByDiscount(discount)
    }

    fun findByDate(date: LocalDateTime): List<Discount> {
        return discountRepository.findByDate(date)
    }


    fun create(request: DiscountCreateRequest): Discount {
        val entity = Discount(
            gymId = request.gym,
            name = request.name,
            discount = request.discount,
            date = request.date,
        )
        return discountRepository.save(entity)
    }

    fun createBatch(requests: List<DiscountCreateRequest>): List<Discount> {
        val entities = requests.map { request ->
            Discount(
                gymId = request.gym,
                name = request.name,
                discount = request.discount,
                date = request.date,
            )
        }
        return discountRepository.saveAll(entities)
    }

    fun update(request: DiscountUpdateRequest): Discount? {
        val existing = discountRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            name = request.name,
            discount = request.discount,
            date = request.date,
        )
        return discountRepository.save(updated)
    }

    fun delete(entity: Discount): Boolean {
        return try {
            discountRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            discountRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Discount>): Boolean {
        return try {
            discountRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: DiscountPatchRequest): Discount? {
        val existing = discountRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym ?: existing.gymId,
            name = request.name ?: existing.name,
            discount = request.discount ?: existing.discount,
            date = request.date ?: existing.date,
        )
        return discountRepository.save(updated)
    }
}