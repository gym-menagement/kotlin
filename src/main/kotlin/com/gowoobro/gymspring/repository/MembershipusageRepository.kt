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
    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findBygymId(gym: Long): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findBymembershipId(membership: Long): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByuserId(user: Long): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByType(type: Type): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByTotaldays(totaldays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByUseddays(useddays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByRemainingdays(remainingdays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByTotalcount(totalcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByUsedcount(usedcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByRemainingcount(remainingcount: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByStartdate(startdate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByEnddate(enddate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByStatus(status: Status): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByPausedays(pausedays: Int): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Membershipusage>

    @EntityGraph(attributePaths = [
        "gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Membershipusage>
}
