package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Usehealthusage
import com.gowoobro.gymspring.entity.UsehealthusageCreateRequest
import com.gowoobro.gymspring.entity.UsehealthusageUpdateRequest
import com.gowoobro.gymspring.entity.UsehealthusagePatchRequest
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
        return usehealthusageRepository.findByGym(gym)
    }

    fun findByUsehealth(usehealth: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByUsehealth(usehealth)
    }

    fun findByMembership(membership: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByMembership(membership)
    }

    fun findByUser(user: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByUser(user)
    }

    fun findByAttendance(attendance: Long): List<Usehealthusage> {
        return usehealthusageRepository.findByAttendance(attendance)
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
            gym = request.gym,
            usehealth = request.usehealth,
            membership = request.membership,
            user = request.user,
            attendance = request.attendance,
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
                gym = request.gym,
                usehealth = request.usehealth,
                membership = request.membership,
                user = request.user,
                attendance = request.attendance,
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
            gym = request.gym,
            usehealth = request.usehealth,
            membership = request.membership,
            user = request.user,
            attendance = request.attendance,
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

    fun patch(request: UsehealthusagePatchRequest): Usehealthusage? {
        val existing = usehealthusageRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gym = request.gym ?: existing.gym,
            usehealth = request.usehealth ?: existing.usehealth,
            membership = request.membership ?: existing.membership,
            user = request.user ?: existing.user,
            attendance = request.attendance ?: existing.attendance,
            type = request.type ?: existing.type,
            usedcount = request.usedcount ?: existing.usedcount,
            remainingcount = request.remainingcount ?: existing.remainingcount,
            checkintime = request.checkintime ?: existing.checkintime,
            checkouttime = request.checkouttime ?: existing.checkouttime,
            duration = request.duration ?: existing.duration,
            note = request.note ?: existing.note,
            date = request.date ?: existing.date,
        )
        return usehealthusageRepository.save(updated)
    }
}