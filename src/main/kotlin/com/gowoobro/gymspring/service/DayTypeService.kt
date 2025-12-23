package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import com.gowoobro.gymspring.entity.DaytypePatchRequest
import com.gowoobro.gymspring.repository.DaytypeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class DaytypeService(private val daytypeRepository: DaytypeRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Daytype> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return daytypeRepository.findAll(pageable)
    }

    fun findById(id: Long): Daytype? {
        return daytypeRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return daytypeRepository.count()
    }


    fun findByGym(gym: Long): List<Daytype> {
        return daytypeRepository.findBygymId(gym)
    }

    fun findByName(name: String): List<Daytype> {
        return daytypeRepository.findByName(name)
    }

    fun findByDate(date: LocalDateTime): List<Daytype> {
        return daytypeRepository.findByDate(date)
    }


    fun create(request: DaytypeCreateRequest): Daytype {
        val entity = Daytype(
            gymId = request.gym,
            name = request.name,
            date = request.date,
        )
        return daytypeRepository.save(entity)
    }

    fun createBatch(requests: List<DaytypeCreateRequest>): List<Daytype> {
        val entities = requests.map { request ->
            Daytype(
                gymId = request.gym,
                name = request.name,
                date = request.date,
            )
        }
        return daytypeRepository.saveAll(entities)
    }

    fun update(request: DaytypeUpdateRequest): Daytype? {
        val existing = daytypeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            name = request.name,
            date = request.date,
        )
        return daytypeRepository.save(updated)
    }

    fun delete(entity: Daytype): Boolean {
        return try {
            daytypeRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            daytypeRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Daytype>): Boolean {
        return try {
            daytypeRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: DaytypePatchRequest): Daytype? {
        val existing = daytypeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym ?: existing.gymId,
            name = request.name ?: existing.name,
            date = request.date ?: existing.date,
        )
        return daytypeRepository.save(updated)
    }
}