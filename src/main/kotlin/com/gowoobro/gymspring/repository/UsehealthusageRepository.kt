package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealthusage
import com.gowoobro.gymspring.entity.UsehealthusageCreateRequest
import com.gowoobro.gymspring.entity.UsehealthusageUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealthusage.Type



@Repository
interface UsehealthusageRepository : JpaRepository<Usehealthusage, Long> {
    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    override fun findAll(pageable: Pageable): Page<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    override fun findById(id: Long): java.util.Optional<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findBygymId(gym: Long): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByusehealthId(usehealth: Long): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findBymembershipId(membership: Long): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByuserId(user: Long): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByattendanceId(attendance: Long): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByType(type: Type): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByUsedcount(usedcount: Int): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByRemainingcount(remainingcount: Int): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByCheckintime(checkintime: LocalDateTime): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByCheckouttime(checkouttime: LocalDateTime): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByDuration(duration: Int): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByNote(note: String): List<Usehealthusage>

    @EntityGraph(attributePaths = [
        "gym",
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym"
    ])
    fun findByDate(date: LocalDateTime): List<Usehealthusage>
}
