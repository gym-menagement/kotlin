package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membershipusage
import com.gowoobro.gymspring.entity.MembershipusageCreateRequest
import com.gowoobro.gymspring.entity.MembershipusageUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membershipusage.Type
import com.gowoobro.gymspring.enums.membershipusage.Status


@Repository
interface MembershipusageRepository : JpaRepository<Membershipusage, Long> {
    @EntityGraph(attributePaths = ["membership", "user"])
    override fun findAll(pageable: Pageable): Page<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    override fun findById(id: Long): java.util.Optional<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findBymembershipId(membership: Long): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByuserId(user: Long): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByType(type: Type): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByTotaldays(totaldays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByUseddays(useddays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByRemainingdays(remainingdays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByTotalcount(totalcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByUsedcount(usedcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByRemainingcount(remainingcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByStartdate(startdate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByEnddate(enddate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByStatus(status: Status): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByPausedays(pausedays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = ["membership", "user"])
    fun findByDate(date: LocalDateTime): List<Membershipusage>
}
