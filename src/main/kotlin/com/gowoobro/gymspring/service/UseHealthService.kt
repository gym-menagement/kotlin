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
import java.time.LocalDateTime



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


    fun findByOrder(order: Long): List<Usehealth> {
        return usehealthRepository.findByOrder(order)
    }

    fun findByHealth(health: Long): List<Usehealth> {
        return usehealthRepository.findByHealth(health)
    }

    fun findByUser(user: Long): List<Usehealth> {
        return usehealthRepository.findByUser(user)
    }

    fun findByRocker(rocker: Long): List<Usehealth> {
        return usehealthRepository.findByRocker(rocker)
    }

    fun findByTerm(term: Long): List<Usehealth> {
        return usehealthRepository.findByTerm(term)
    }

    fun findByDiscount(discount: Long): List<Usehealth> {
        return usehealthRepository.findByDiscount(discount)
    }

    fun findByStartday(startday: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByStartday(startday)
    }

    fun findByEndday(endday: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByEndday(endday)
    }

    fun findByDate(date: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByDate(date)
    }


    fun create(request: UsehealthCreateRequest): Usehealth {
        val entity = Usehealth(
            order = request.order,
            health = request.health,
            user = request.user,
            rocker = request.rocker,
            term = request.term,
            discount = request.discount,
            startday = request.startday,
            endday = request.endday,
            date = request.date,
        )
        return usehealthRepository.save(entity)
    }

    fun createBatch(requests: List<UsehealthCreateRequest>): List<Usehealth> {
        val entities = requests.map { request ->
            Usehealth(
                order = request.order,
                health = request.health,
                user = request.user,
                rocker = request.rocker,
                term = request.term,
                discount = request.discount,
                startday = request.startday,
                endday = request.endday,
                date = request.date,
            )
        }
        return usehealthRepository.saveAll(entities)
    }

    fun update(request: UsehealthUpdateRequest): Usehealth? {
        val existing = usehealthRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            order = request.order,
            health = request.health,
            user = request.user,
            rocker = request.rocker,
            term = request.term,
            discount = request.discount,
            startday = request.startday,
            endday = request.endday,
            date = request.date,
        )
        return usehealthRepository.save(updated)
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