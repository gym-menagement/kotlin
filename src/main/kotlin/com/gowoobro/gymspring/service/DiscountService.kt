package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Discount
import com.gowoobro.gymspring.entity.DiscountCreateRequest
import com.gowoobro.gymspring.entity.DiscountUpdateRequest
import com.gowoobro.gymspring.repository.DiscountRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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







    fun create(request: DiscountCreateRequest): Discount {
        val entity = Discount()
        return discountRepository.save(entity)
    }

    fun createBatch(requests: List<DiscountCreateRequest>): List<Discount> {
        val entities = requests.map { request ->
            Discount()
        }
        return discountRepository.saveAll(entities)
    }

    fun update(request: DiscountUpdateRequest): Discount? {
        val existing = discountRepository.findById(request.id).orElse(null) ?: return null
        return discountRepository.save(existing)
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
}