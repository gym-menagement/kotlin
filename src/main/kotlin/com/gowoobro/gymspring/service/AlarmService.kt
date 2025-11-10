package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import com.gowoobro.gymspring.repository.AlarmRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status


@Service
@Transactional
class AlarmService(private val alarmRepository: AlarmRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Alarm> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return alarmRepository.findAll(pageable)
    }

    fun findById(id: Long): Alarm? {
        return alarmRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return alarmRepository.count()
    }


    fun findByTitle(title: String): List<Alarm> {
        return alarmRepository.findByTitle(title)
    }

    fun findByContent(content: String): List<Alarm> {
        return alarmRepository.findByContent(content)
    }

    fun findByType(type: Type): List<Alarm> {
        return alarmRepository.findByType(type)
    }

    fun findByStatus(status: Status): List<Alarm> {
        return alarmRepository.findByStatus(status)
    }

    fun findByUser(user: Long): List<Alarm> {
        return alarmRepository.findByUser(user)
    }

    fun findByDate(date: LocalDateTime): List<Alarm> {
        return alarmRepository.findByDate(date)
    }


    fun create(request: AlarmCreateRequest): Alarm {
        val entity = Alarm(
            title = request.title,
            content = request.content,
            type = request.type,
            status = request.status,
            user = request.user,
            date = request.date,
        )
        return alarmRepository.save(entity)
    }

    fun createBatch(requests: List<AlarmCreateRequest>): List<Alarm> {
        val entities = requests.map { request ->
            Alarm(
                title = request.title,
                content = request.content,
                type = request.type,
                status = request.status,
                user = request.user,
                date = request.date,
            )
        }
        return alarmRepository.saveAll(entities)
    }

    fun update(request: AlarmUpdateRequest): Alarm? {
        val existing = alarmRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            title = request.title,
            content = request.content,
            type = request.type,
            status = request.status,
            user = request.user,
            date = request.date,
        )
        return alarmRepository.save(updated)
    }

    fun delete(entity: Alarm): Boolean {
        return try {
            alarmRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            alarmRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Alarm>): Boolean {
        return try {
            alarmRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}