package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Systemlog
import com.gowoobro.gymspring.entity.SystemlogCreateRequest
import com.gowoobro.gymspring.entity.SystemlogUpdateRequest
import com.gowoobro.gymspring.repository.SystemlogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.systemlog.Type
import com.gowoobro.gymspring.enums.systemlog.Result


@Service
@Transactional
class SystemlogService(private val systemlogRepository: SystemlogRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Systemlog> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return systemlogRepository.findAll(pageable)
    }

    fun findById(id: Long): Systemlog? {
        return systemlogRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return systemlogRepository.count()
    }


    fun findByType(type: Type): List<Systemlog> {
        return systemlogRepository.findByTypeWithJoin(type)
    }

    fun findByContent(content: String): List<Systemlog> {
        return systemlogRepository.findByContentWithJoin(content)
    }

    fun findByResult(result: Result): List<Systemlog> {
        return systemlogRepository.findByResultWithJoin(result)
    }

    fun findByDate(date: LocalDateTime): List<Systemlog> {
        return systemlogRepository.findByDateWithJoin(date)
    }


    fun create(request: SystemlogCreateRequest): Systemlog {
        val entity = Systemlog(
            type = request.type,
            content = request.content,
            result = request.result,
            date = request.date,
        )
        return systemlogRepository.save(entity)
    }

    fun createBatch(requests: List<SystemlogCreateRequest>): List<Systemlog> {
        val entities = requests.map { request ->
            Systemlog(
                type = request.type,
                content = request.content,
                result = request.result,
                date = request.date,
            )
        }
        return systemlogRepository.saveAll(entities)
    }

    fun update(request: SystemlogUpdateRequest): Systemlog? {
        val existing = systemlogRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            type = request.type,
            content = request.content,
            result = request.result,
            date = request.date,
        )
        return systemlogRepository.save(updated)
    }

    fun delete(entity: Systemlog): Boolean {
        return try {
            systemlogRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            systemlogRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Systemlog>): Boolean {
        return try {
            systemlogRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}