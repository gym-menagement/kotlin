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













    fun create(request: HealthCreateRequest): Health {
        val entity = Health()
        return healthRepository.save(entity)
    }

    fun createBatch(requests: List<HealthCreateRequest>): List<Health> {
        val entities = requests.map { request ->
            Health()
        }
        return healthRepository.saveAll(entities)
    }

    fun update(request: HealthUpdateRequest): Health? {
        val existing = healthRepository.findById(request.id).orElse(null) ?: return null
        return healthRepository.save(existing)
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