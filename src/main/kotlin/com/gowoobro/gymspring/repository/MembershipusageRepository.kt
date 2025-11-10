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



@Repository
interface MembershipusageRepository : JpaRepository<Membershipusage, Long> {
    override fun findAll(pageable: Pageable): Page<Membershipusage>


    fun findByMembership(membership: Long): List<Membershipusage>

    fun findByUser(user: Long): List<Membershipusage>

    fun findByType(type: Int): List<Membershipusage>

    fun findByTotaldays(totaldays: Int): List<Membershipusage>

    fun findByUseddays(useddays: Int): List<Membershipusage>

    fun findByRemainingdays(remainingdays: Int): List<Membershipusage>

    fun findByTotalcount(totalcount: Int): List<Membershipusage>

    fun findByUsedcount(usedcount: Int): List<Membershipusage>

    fun findByRemainingcount(remainingcount: Int): List<Membershipusage>

    fun findByStartdate(startdate: LocalDateTime): List<Membershipusage>

    fun findByEnddate(enddate: LocalDateTime): List<Membershipusage>

    fun findByStatus(status: Int): List<Membershipusage>

    fun findByPausedays(pausedays: Int): List<Membershipusage>

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Membershipusage>

    fun findByDate(date: LocalDateTime): List<Membershipusage>
}