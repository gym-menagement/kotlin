package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Healthcategory
import com.gowoobro.gymspring.entity.HealthcategoryCreateRequest
import com.gowoobro.gymspring.entity.HealthcategoryUpdateRequest
import com.gowoobro.gymspring.repository.HealthcategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HealthcategoryService(private val healthcategoryRepository: HealthcategoryRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Healthcategory> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return healthcategoryRepository.findAll(pageable)
    }

    fun findById(id: Long): Healthcategory? {
        return healthcategoryRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return healthcategoryRepository.count()
    }







    fun create(request: HealthcategoryCreateRequest): Healthcategory {
        val entity = Healthcategory()
        return healthcategoryRepository.save(entity)
    }

    fun createBatch(requests: List<HealthcategoryCreateRequest>): List<Healthcategory> {
        val entities = requests.map { request ->
            Healthcategory()
        }
        return healthcategoryRepository.saveAll(entities)
    }

    fun update(request: HealthcategoryUpdateRequest): Healthcategory? {
        val existing = healthcategoryRepository.findById(request.id).orElse(null) ?: return null
        return healthcategoryRepository.save(existing)
    }

    fun delete(entity: Healthcategory): Boolean {
        return try {
            healthcategoryRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            healthcategoryRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Healthcategory>): Boolean {
        return try {
            healthcategoryRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}