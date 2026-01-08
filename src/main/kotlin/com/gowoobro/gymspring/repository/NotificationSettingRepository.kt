package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Notificationsetting
import com.gowoobro.gymspring.entity.NotificationsettingCreateRequest
import com.gowoobro.gymspring.entity.NotificationsettingUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
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



@Repository
interface NotificationsettingRepository : JpaRepository<Notificationsetting, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByuserId(user: Long): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByEnabled(enabled: Enabled): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByMembershipexpiry(membershipexpiry: Membershipexpiry): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByMembershipnear(membershipnear: Membershipnear): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByAttendanceenc(attendanceenc: Attendanceenc): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByGymannounce(gymannounce: Gymannounce): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findBySystemnotice(systemnotice: Systemnotice): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByPaymentconfirm(paymentconfirm: Paymentconfirm): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByPauseexpiry(pauseexpiry: Pauseexpiry): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByWeeklygoal(weeklygoal: Weeklygoal): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByPersonalrecord(personalrecord: Personalrecord): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByQuietenabled(quietenabled: Quietenabled): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByQuietstart(quietstart: String): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByQuietend(quietend: String): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByCreateddate(createddate: LocalDateTime): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUpdateddate(updateddate: LocalDateTime): List<Notificationsetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Notificationsetting>
}
