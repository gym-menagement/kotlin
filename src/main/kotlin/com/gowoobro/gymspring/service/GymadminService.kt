package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.*
import com.gowoobro.gymspring.repository.GymadminRepository
import com.gowoobro.gymspring.repository.GymRepository
import com.gowoobro.gymspring.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class GymadminService(
    private val gymadminRepository: GymadminRepository
) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<Gymadmin> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return gymadminRepository.findAll(pageable)
    }

    fun findById(id: Long): Gymadmin? {
        return gymadminRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return gymadminRepository.count()
    }
    
    fun findByGymId(gymId: Long, page: Int = 0, pagesize: Int = 10): Page<Gymadmin> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return gymadminRepository.findByGymId(gymId, pageable)
    }

    fun findByUserId(userId: Long, page: Int = 0, pagesize: Int = 10): Page<Gymadmin> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return gymadminRepository.findByUserId(userId, pageable)
    }
    
    fun existsByGymIdAndUserId(gymId: Long, userId: Long): Boolean {
        return gymadminRepository.existsByGymIdAndUserId(gymId, userId)
    }

    fun create(request: GymadminCreateRequest): Gymadmin {
        // Check duplication logic can be added here or in validation
        if (gymadminRepository.existsByGymIdAndUserId(request.gym, request.user)) {
             throw IllegalArgumentException("User is already an admin of this gym")
        }

        val entity = Gymadmin(
            gymId = request.gym,
            userId = request.user,
            level = request.level,
            status = request.status,
            date = request.date,
        )
        return gymadminRepository.save(entity)
    }

    fun createBatch(requests: List<GymadminCreateRequest>): List<Gymadmin> {
        val entities = requests.map { request ->
            Gymadmin(
                gymId = request.gym,
                userId = request.user,
                level = request.level,
                status = request.status,
                date = request.date,
            )
        }
        return gymadminRepository.saveAll(entities)
    }

    fun update(request: GymadminUpdateRequest): Gymadmin? {
        val existing = gymadminRepository.findById(request.id).orElse(null) ?: return null
        
        val updated = existing.copy(
            gymId = request.gym,
            userId = request.user,
            level = request.level,
            status = request.status,
            date = request.date,
        )
        // Ensure relationships are properly set for JPA context if needed
        // but here we are saving using repository which handles id
        return gymadminRepository.save(updated)
    }
    
    fun delete(entity: Gymadmin): Boolean {
        return try {
            gymadminRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            gymadminRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Gymadmin>): Boolean {
        return try {
            gymadminRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: GymadminPatchRequest): Gymadmin? {
        val existing = gymadminRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym ?: existing.gymId,
            userId = request.user ?: existing.userId,
            level = request.level ?: existing.level,
            status = request.status ?: existing.status,
            date = request.date ?: existing.date,
        )
        return gymadminRepository.save(updated)
    }
}
