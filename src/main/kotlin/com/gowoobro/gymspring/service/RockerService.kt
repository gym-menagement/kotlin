package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Rocker
import com.gowoobro.gymspring.entity.RockerCreateRequest
import com.gowoobro.gymspring.entity.RockerUpdateRequest
import com.gowoobro.gymspring.repository.RockerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RockerService(private val rockerRepository: RockerRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Rocker> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return rockerRepository.findAll(pageable)
    }

    fun findById(id: Long): Rocker? {
        return rockerRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return rockerRepository.count()
    }








    fun create(request: RockerCreateRequest): Rocker {
        val entity = Rocker()
        return rockerRepository.save(entity)
    }

    fun createBatch(requests: List<RockerCreateRequest>): List<Rocker> {
        val entities = requests.map { request ->
            Rocker()
        }
        return rockerRepository.saveAll(entities)
    }

    fun update(request: RockerUpdateRequest): Rocker? {
        val existing = rockerRepository.findById(request.id).orElse(null) ?: return null
        return rockerRepository.save(existing)
    }

    fun delete(entity: Rocker): Boolean {
        return try {
            rockerRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            rockerRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Rocker>): Boolean {
        return try {
            rockerRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}