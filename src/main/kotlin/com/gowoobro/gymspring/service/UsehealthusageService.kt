package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Usehealthusage
import com.gowoobro.gymspring.entity.UsehealthusageCreateRequest
import com.gowoobro.gymspring.entity.UsehealthusageUpdateRequest
import com.gowoobro.gymspring.repository.UsehealthusageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealthusage.Type


@Service
@Transactional
class UsehealthusageService(private val usehealthusageRepository: UsehealthusageRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Usehealthusage> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return usehealthusageRepository.findAll(pageable)
    }

    fun findById(id: Long): Usehealthusage? {
        return usehealthusageRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return usehealthusageRepository.count()
    }


    fun findByGym(gym: Long): List<Usehealthusage> {
        return usehealthusageRepository.findBygymId(gym)
    }

    fun findByUsehealth(usehealth: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByusehealthId(usehealth)
    }

    fun findByUser(user: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByuserId(user)
    }

    fun findByAttendance(attendance: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByattendanceId(attendance)
    }

    fun findByType(type: Type): List<Usehealthusage> {
        return usehealthusageRepository.findByType(type)
    }

    fun findByUsedcount(usedcount: Int): List<Usehealthusage> {
        return usehealthusageRepository.findByUsedcount(usedcount)
    }

    fun findByRemainingcount(remainingcount: Int): List<Usehealthusage> {
        return usehealthusageRepository.findByRemainingcount(remainingcount)
    }

    fun findByCheckintime(checkintime: LocalDateTime): List<Usehealthusage> {
        return usehealthusageRepository.findByCheckintime(checkintime)
    }

    fun findByCheckouttime(checkouttime: LocalDateTime): List<Usehealthusage> {
        return usehealthusageRepository.findByCheckouttime(checkouttime)
    }

    fun findByDuration(duration: Int): List<Usehealthusage> {
        return usehealthusageRepository.findByDuration(duration)
    }

    fun findByNote(note: String): List<Usehealthusage> {
        return usehealthusageRepository.findByNote(note)
    }

    fun findByDate(date: LocalDateTime): List<Usehealthusage> {
        return usehealthusageRepository.findByDate(date)
    }


    fun create(request: UsehealthusageCreateRequest): Usehealthusage {
        val entity = Usehealthusage(
            gymId = request.gym,
            usehealthId = request.usehealth,
            userId = request.user,
            attendanceId = request.attendance,
            type = request.type,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            checkintime = request.checkintime,
            checkouttime = request.checkouttime,
            duration = request.duration,
            note = request.note,
            date = request.date,
        )
        return usehealthusageRepository.save(entity)
    }

    fun createBatch(requests: List<UsehealthusageCreateRequest>): List<Usehealthusage> {
        val entities = requests.map { request ->
            Usehealthusage(
                gymId = request.gym,
                usehealthId = request.usehealth,
                userId = request.user,
                attendanceId = request.attendance,
                type = request.type,
                usedcount = request.usedcount,
                remainingcount = request.remainingcount,
                checkintime = request.checkintime,
                checkouttime = request.checkouttime,
                duration = request.duration,
                note = request.note,
                date = request.date,
            )
        }
        return usehealthusageRepository.saveAll(entities)
    }

    fun update(request: UsehealthusageUpdateRequest): Usehealthusage? {
        val existing = usehealthusageRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            usehealthId = request.usehealth,
            userId = request.user,
            attendanceId = request.attendance,
            type = request.type,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            checkintime = request.checkintime,
            checkouttime = request.checkouttime,
            duration = request.duration,
            note = request.note,
            date = request.date,
        )
        return usehealthusageRepository.save(updated)
    }

    fun delete(entity: Usehealthusage): Boolean {
        return try {
            usehealthusageRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            usehealthusageRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Usehealthusage>): Boolean {
        return try {
            usehealthusageRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}