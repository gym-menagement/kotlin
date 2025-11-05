package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Rockergroup
import com.gowoobro.gymspring.entity.RockergroupCreateRequest
import com.gowoobro.gymspring.entity.RockergroupUpdateRequest
import com.gowoobro.gymspring.repository.RockergroupRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RockergroupService(private val rockergroupRepository: RockergroupRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Rockergroup> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return rockergroupRepository.findAll(pageable)
    }

    fun findById(id: Long): Rockergroup? {
        return rockergroupRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return rockergroupRepository.count()
    }


    fun findById(id: String): List<Rockergroup> {
        return rockergroupRepository.findById(id)
    }

    fun findByGym(gym: String): List<Rockergroup> {
        return rockergroupRepository.findByGym(gym)
    }

    fun findByName(name: String): List<Rockergroup> {
        return rockergroupRepository.findByName(name)
    }

    fun findByDate(date: String): List<Rockergroup> {
        return rockergroupRepository.findByDate(date)
    }


    fun create(request: RockergroupCreateRequest): Rockergroup {
        val entity = Rockergroup()
        return rockergroupRepository.save(entity)
    }

    fun createBatch(requests: List<RockergroupCreateRequest>): List<Rockergroup> {
        val entities = requests.map { request ->
            Rockergroup()
        }
        return rockergroupRepository.saveAll(entities)
    }

    fun update(request: RockergroupUpdateRequest): Rockergroup? {
        val existing = rockergroupRepository.findById(request.id).orElse(null) ?: return null
        return rockergroupRepository.save(existing)
    }

    fun delete(entity: Rockergroup): Boolean {
        return try {
            rockergroupRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            rockergroupRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Rockergroup>): Boolean {
        return try {
            rockergroupRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}