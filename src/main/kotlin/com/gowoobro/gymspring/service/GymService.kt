package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Gym
import com.gowoobro.gymspring.entity.GymCreateRequest
import com.gowoobro.gymspring.entity.GymUpdateRequest
import com.gowoobro.gymspring.repository.GymRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GymService(private val gymRepository: GymRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Gym> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return gymRepository.findAll(pageable)
    }

    fun findById(id: Long): Gym? {
        return gymRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return gymRepository.count()
    }






    fun create(request: GymCreateRequest): Gym {
        val entity = Gym()
        return gymRepository.save(entity)
    }

    fun createBatch(requests: List<GymCreateRequest>): List<Gym> {
        val entities = requests.map { request ->
            Gym()
        }
        return gymRepository.saveAll(entities)
    }

    fun update(request: GymUpdateRequest): Gym? {
        val existing = gymRepository.findById(request.id).orElse(null) ?: return null
        return gymRepository.save(existing)
    }

    fun delete(entity: Gym): Boolean {
        return try {
            gymRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            gymRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Gym>): Boolean {
        return try {
            gymRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}