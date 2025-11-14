package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Health
import com.gowoobro.gymspring.entity.HealthCreateRequest
import com.gowoobro.gymspring.entity.HealthUpdateRequest
import com.gowoobro.gymspring.repository.HealthRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class HealthService(private val healthRepository: HealthRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Health> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return healthRepository.findAll(pageable)
    }

    fun findById(id: Long): Health? {
        return healthRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return healthRepository.count()
    }


    fun findByCategory(healthcategory: Long): List<Health> {
        return healthRepository.findBycategoryId(healthcategory)
    }

    fun findByTerm(term: Long): List<Health> {
        return healthRepository.findBytermId(term)
    }

    fun findByName(name: String): List<Health> {
        return healthRepository.findByName(name)
    }

    fun findByCount(count: Int): List<Health> {
        return healthRepository.findByCount(count)
    }

    fun findByCost(cost: Int): List<Health> {
        return healthRepository.findByCost(cost)
    }

    fun findByDiscount(discount: Long): List<Health> {
        return healthRepository.findBydiscountId(discount)
    }

    fun findByCostdiscount(costdiscount: Int): List<Health> {
        return healthRepository.findByCostdiscount(costdiscount)
    }

    fun findByContent(content: String): List<Health> {
        return healthRepository.findByContent(content)
    }

    fun findByDate(date: LocalDateTime): List<Health> {
        return healthRepository.findByDate(date)
    }


    fun create(request: HealthCreateRequest): Health {
        val entity = Health(
            categoryId = request.category,
            termId = request.term,
            name = request.name,
            count = request.count,
            cost = request.cost,
            discountId = request.discount,
            costdiscount = request.costdiscount,
            content = request.content,
            date = request.date,
        )
        return healthRepository.save(entity)
    }

    fun createBatch(requests: List<HealthCreateRequest>): List<Health> {
        val entities = requests.map { request ->
            Health(
                categoryId = request.category,
                termId = request.term,
                name = request.name,
                count = request.count,
                cost = request.cost,
                discountId = request.discount,
                costdiscount = request.costdiscount,
                content = request.content,
                date = request.date,
            )
        }
        return healthRepository.saveAll(entities)
    }

    fun update(request: HealthUpdateRequest): Health? {
        val existing = healthRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            categoryId = request.category,
            termId = request.term,
            name = request.name,
            count = request.count,
            cost = request.cost,
            discountId = request.discount,
            costdiscount = request.costdiscount,
            content = request.content,
            date = request.date,
        )
        return healthRepository.save(updated)
    }

    fun delete(entity: Health): Boolean {
        return try {
            healthRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            healthRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Health>): Boolean {
        return try {
            healthRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}