package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membershipusage
import com.gowoobro.gymspring.entity.MembershipusageCreateRequest
import com.gowoobro.gymspring.entity.MembershipusageUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membershipusage.Type
import com.gowoobro.gymspring.enums.membershipusage.Status


@Repository
interface MembershipusageRepository : JpaRepository<Membershipusage, Long> {
    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.membershipId = :membership")
    fun findByMembershipWithJoin(membership: Long): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.totaldays = :totaldays")
    fun findByTotaldaysWithJoin(totaldays: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.useddays = :useddays")
    fun findByUseddaysWithJoin(useddays: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.remainingdays = :remainingdays")
    fun findByRemainingdaysWithJoin(remainingdays: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.totalcount = :totalcount")
    fun findByTotalcountWithJoin(totalcount: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.usedcount = :usedcount")
    fun findByUsedcountWithJoin(usedcount: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.remainingcount = :remainingcount")
    fun findByRemainingcountWithJoin(remainingcount: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.startdate = :startdate")
    fun findByStartdateWithJoin(startdate: LocalDateTime): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.enddate = :enddate")
    fun findByEnddateWithJoin(enddate: LocalDateTime): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.pausedays = :pausedays")
    fun findByPausedaysWithJoin(pausedays: Int): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.lastuseddate = :lastuseddate")
    fun findByLastuseddateWithJoin(lastuseddate: LocalDateTime): List<Membershipusage>

    @Query("SELECT m FROM Membershipusage m LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Membershipusage>
}