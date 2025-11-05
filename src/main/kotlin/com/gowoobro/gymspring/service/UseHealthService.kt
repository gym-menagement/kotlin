package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.repository.UsehealthRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UsehealthService(private val usehealthRepository: UsehealthRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Usehealth> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return usehealthRepository.findAll(pageable)
    }

    fun findById(id: Long): Usehealth? {
        return usehealthRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return usehealthRepository.count()
    }


    fun findById(id: String): List<Usehealth> {
        return usehealthRepository.findById(id)
    }

    fun findByOrder(order: String): List<Usehealth> {
        return usehealthRepository.findByOrder(order)
    }

    fun findByHealth(health: String): List<Usehealth> {
        return usehealthRepository.findByHealth(health)
    }

    fun findByUser(user: String): List<Usehealth> {
        return usehealthRepository.findByUser(user)
    }

    fun findByRocker(rocker: String): List<Usehealth> {
        return usehealthRepository.findByRocker(rocker)
    }

    fun findByTerm(term: String): List<Usehealth> {
        return usehealthRepository.findByTerm(term)
    }

    fun findByDiscount(discount: String): List<Usehealth> {
        return usehealthRepository.findByDiscount(discount)
    }

    fun findByStartday(startday: String): List<Usehealth> {
        return usehealthRepository.findByStartday(startday)
    }

    fun findByEndday(endday: String): List<Usehealth> {
        return usehealthRepository.findByEndday(endday)
    }

    fun findByDate(date: String): List<Usehealth> {
        return usehealthRepository.findByDate(date)
    }


    fun create(request: UsehealthCreateRequest): Usehealth {
        val entity = Usehealth()
        return usehealthRepository.save(entity)
    }

    fun createBatch(requests: List<UsehealthCreateRequest>): List<Usehealth> {
        val entities = requests.map { request ->
            Usehealth()
        }
        return usehealthRepository.saveAll(entities)
    }

    fun update(request: UsehealthUpdateRequest): Usehealth? {
        val existing = usehealthRepository.findById(request.id).orElse(null) ?: return null
        return usehealthRepository.save(existing)
    }

    fun delete(entity: Usehealth): Boolean {
        return try {
            usehealthRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            usehealthRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Usehealth>): Boolean {
        return try {
            usehealthRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}