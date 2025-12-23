package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import com.gowoobro.gymspring.entity.StopPatchRequest
import com.gowoobro.gymspring.repository.StopRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class StopService(private val stopRepository: StopRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Stop> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return stopRepository.findAll(pageable)
    }

    fun findById(id: Long): Stop? {
        return stopRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return stopRepository.count()
    }


    fun findByUsehealth(usehealth: Long): List<Stop> {
        return stopRepository.findByusehealthId(usehealth)
    }

    fun findByStartday(startday: LocalDateTime): List<Stop> {
        return stopRepository.findByStartday(startday)
    }

    fun findByEndday(endday: LocalDateTime): List<Stop> {
        return stopRepository.findByEndday(endday)
    }

    fun findByCount(count: Int): List<Stop> {
        return stopRepository.findByCount(count)
    }

    fun findByDate(date: LocalDateTime): List<Stop> {
        return stopRepository.findByDate(date)
    }


    fun create(request: StopCreateRequest): Stop {
        val entity = Stop(
            usehealthId = request.usehealth,
            startday = request.startday,
            endday = request.endday,
            count = request.count,
            date = request.date,
        )
        return stopRepository.save(entity)
    }

    fun createBatch(requests: List<StopCreateRequest>): List<Stop> {
        val entities = requests.map { request ->
            Stop(
                usehealthId = request.usehealth,
                startday = request.startday,
                endday = request.endday,
                count = request.count,
                date = request.date,
            )
        }
        return stopRepository.saveAll(entities)
    }

    fun update(request: StopUpdateRequest): Stop? {
        val existing = stopRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            usehealthId = request.usehealth,
            startday = request.startday,
            endday = request.endday,
            count = request.count,
            date = request.date,
        )
        return stopRepository.save(updated)
    }

    fun delete(entity: Stop): Boolean {
        return try {
            stopRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            stopRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Stop>): Boolean {
        return try {
            stopRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: StopPatchRequest): Stop? {
        val existing = stopRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            usehealthId = request.usehealth ?: existing.usehealthId,
            startday = request.startday ?: existing.startday,
            endday = request.endday ?: existing.endday,
            count = request.count ?: existing.count,
            date = request.date ?: existing.date,
        )
        return stopRepository.save(updated)
    }
}