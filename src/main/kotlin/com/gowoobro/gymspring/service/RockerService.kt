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
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.rocker.Available


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


    fun findByGroup(rockergroup: Long): List<Rocker> {
        return rockerRepository.findBygroupId(rockergroup)
    }

    fun findByName(name: String): List<Rocker> {
        return rockerRepository.findByName(name)
    }

    fun findByAvailable(available: Available): List<Rocker> {
        return rockerRepository.findByAvailable(available)
    }

    fun findByDate(date: LocalDateTime): List<Rocker> {
        return rockerRepository.findByDate(date)
    }


    fun create(request: RockerCreateRequest): Rocker {
        val entity = Rocker(
            groupId = request.group,
            name = request.name,
            available = request.available,
            date = request.date,
        )
        return rockerRepository.save(entity)
    }

    fun createBatch(requests: List<RockerCreateRequest>): List<Rocker> {
        val entities = requests.map { request ->
            Rocker(
                groupId = request.group,
                name = request.name,
                available = request.available,
                date = request.date,
            )
        }
        return rockerRepository.saveAll(entities)
    }

    fun update(request: RockerUpdateRequest): Rocker? {
        val existing = rockerRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            groupId = request.group,
            name = request.name,
            available = request.available,
            date = request.date,
        )
        return rockerRepository.save(updated)
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