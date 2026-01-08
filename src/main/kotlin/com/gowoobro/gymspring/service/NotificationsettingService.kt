package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Notificationsetting
import com.gowoobro.gymspring.entity.NotificationsettingCreateRequest
import com.gowoobro.gymspring.entity.NotificationsettingUpdateRequest
import com.gowoobro.gymspring.entity.NotificationsettingPatchRequest
import com.gowoobro.gymspring.repository.NotificationsettingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationsetting.Enabled
import com.gowoobro.gymspring.enums.notificationsetting.Membershipexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Membershipnear
import com.gowoobro.gymspring.enums.notificationsetting.Attendanceenc
import com.gowoobro.gymspring.enums.notificationsetting.Gymannounce
import com.gowoobro.gymspring.enums.notificationsetting.Systemnotice
import com.gowoobro.gymspring.enums.notificationsetting.Paymentconfirm
import com.gowoobro.gymspring.enums.notificationsetting.Pauseexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Weeklygoal
import com.gowoobro.gymspring.enums.notificationsetting.Personalrecord
import com.gowoobro.gymspring.enums.notificationsetting.Quietenabled


@Service
@Transactional
class NotificationsettingService(private val notificationsettingRepository: NotificationsettingRepository) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<Notificationsetting> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return notificationsettingRepository.findAll(pageable)
    }

    fun findById(id: Long): Notificationsetting? {
        return notificationsettingRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return notificationsettingRepository.count()
    }


    fun findByUser(user: Long): List<Notificationsetting> {
        return notificationsettingRepository.findByuserId(user)
    }

    fun findByEnabled(enabled: Enabled): List<Notificationsetting> {
        return notificationsettingRepository.findByEnabled(enabled)
    }

    fun findByMembershipexpiry(membershipexpiry: Membershipexpiry): List<Notificationsetting> {
        return notificationsettingRepository.findByMembershipexpiry(membershipexpiry)
    }

    fun findByMembershipnear(membershipnear: Membershipnear): List<Notificationsetting> {
        return notificationsettingRepository.findByMembershipnear(membershipnear)
    }

    fun findByAttendanceenc(attendanceenc: Attendanceenc): List<Notificationsetting> {
        return notificationsettingRepository.findByAttendanceenc(attendanceenc)
    }

    fun findByGymannounce(gymannounce: Gymannounce): List<Notificationsetting> {
        return notificationsettingRepository.findByGymannounce(gymannounce)
    }

    fun findBySystemnotice(systemnotice: Systemnotice): List<Notificationsetting> {
        return notificationsettingRepository.findBySystemnotice(systemnotice)
    }

    fun findByPaymentconfirm(paymentconfirm: Paymentconfirm): List<Notificationsetting> {
        return notificationsettingRepository.findByPaymentconfirm(paymentconfirm)
    }

    fun findByPauseexpiry(pauseexpiry: Pauseexpiry): List<Notificationsetting> {
        return notificationsettingRepository.findByPauseexpiry(pauseexpiry)
    }

    fun findByWeeklygoal(weeklygoal: Weeklygoal): List<Notificationsetting> {
        return notificationsettingRepository.findByWeeklygoal(weeklygoal)
    }

    fun findByPersonalrecord(personalrecord: Personalrecord): List<Notificationsetting> {
        return notificationsettingRepository.findByPersonalrecord(personalrecord)
    }

    fun findByQuietenabled(quietenabled: Quietenabled): List<Notificationsetting> {
        return notificationsettingRepository.findByQuietenabled(quietenabled)
    }

    fun findByQuietstart(quietstart: String): List<Notificationsetting> {
        return notificationsettingRepository.findByQuietstart(quietstart)
    }

    fun findByQuietend(quietend: String): List<Notificationsetting> {
        return notificationsettingRepository.findByQuietend(quietend)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Notificationsetting> {
        return notificationsettingRepository.findByCreateddate(createddate)
    }

    fun findByUpdateddate(updateddate: LocalDateTime): List<Notificationsetting> {
        return notificationsettingRepository.findByUpdateddate(updateddate)
    }

    fun findByDate(date: LocalDateTime): List<Notificationsetting> {
        return notificationsettingRepository.findByDate(date)
    }


    fun create(request: NotificationsettingCreateRequest): Notificationsetting {
        val entity = Notificationsetting(
            userId = request.user,
            enabled = request.enabled,
            membershipexpiry = request.membershipexpiry,
            membershipnear = request.membershipnear,
            attendanceenc = request.attendanceenc,
            gymannounce = request.gymannounce,
            systemnotice = request.systemnotice,
            paymentconfirm = request.paymentconfirm,
            pauseexpiry = request.pauseexpiry,
            weeklygoal = request.weeklygoal,
            personalrecord = request.personalrecord,
            quietenabled = request.quietenabled,
            quietstart = request.quietstart,
            quietend = request.quietend,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return notificationsettingRepository.save(entity)
    }

    fun createBatch(requests: List<NotificationsettingCreateRequest>): List<Notificationsetting> {
        val entities = requests.map { request ->
            Notificationsetting(
                userId = request.user,
                enabled = request.enabled,
                membershipexpiry = request.membershipexpiry,
                membershipnear = request.membershipnear,
                attendanceenc = request.attendanceenc,
                gymannounce = request.gymannounce,
                systemnotice = request.systemnotice,
                paymentconfirm = request.paymentconfirm,
                pauseexpiry = request.pauseexpiry,
                weeklygoal = request.weeklygoal,
                personalrecord = request.personalrecord,
                quietenabled = request.quietenabled,
                quietstart = request.quietstart,
                quietend = request.quietend,
                createddate = request.createddate,
                updateddate = request.updateddate,
                date = request.date,
            )
        }
        return notificationsettingRepository.saveAll(entities)
    }

    fun update(request: NotificationsettingUpdateRequest): Notificationsetting? {
        val existing = notificationsettingRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user,
            enabled = request.enabled,
            membershipexpiry = request.membershipexpiry,
            membershipnear = request.membershipnear,
            attendanceenc = request.attendanceenc,
            gymannounce = request.gymannounce,
            systemnotice = request.systemnotice,
            paymentconfirm = request.paymentconfirm,
            pauseexpiry = request.pauseexpiry,
            weeklygoal = request.weeklygoal,
            personalrecord = request.personalrecord,
            quietenabled = request.quietenabled,
            quietstart = request.quietstart,
            quietend = request.quietend,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return notificationsettingRepository.save(updated)
    }

    fun delete(entity: Notificationsetting): Boolean {
        return try {
            notificationsettingRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            notificationsettingRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Notificationsetting>): Boolean {
        return try {
            notificationsettingRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: NotificationsettingPatchRequest): Notificationsetting? {
        val existing = notificationsettingRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user ?: existing.userId,
            enabled = request.enabled ?: existing.enabled,
            membershipexpiry = request.membershipexpiry ?: existing.membershipexpiry,
            membershipnear = request.membershipnear ?: existing.membershipnear,
            attendanceenc = request.attendanceenc ?: existing.attendanceenc,
            gymannounce = request.gymannounce ?: existing.gymannounce,
            systemnotice = request.systemnotice ?: existing.systemnotice,
            paymentconfirm = request.paymentconfirm ?: existing.paymentconfirm,
            pauseexpiry = request.pauseexpiry ?: existing.pauseexpiry,
            weeklygoal = request.weeklygoal ?: existing.weeklygoal,
            personalrecord = request.personalrecord ?: existing.personalrecord,
            quietenabled = request.quietenabled ?: existing.quietenabled,
            quietstart = request.quietstart ?: existing.quietstart,
            quietend = request.quietend ?: existing.quietend,
            createddate = request.createddate ?: existing.createddate,
            updateddate = request.updateddate ?: existing.updateddate,
            date = request.date ?: existing.date,
        )
        return notificationsettingRepository.save(updated)
    }
}