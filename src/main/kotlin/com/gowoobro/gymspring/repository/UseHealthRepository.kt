package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealth.Status



@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByorderId(order: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByhealthId(health: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBymembershipId(membership: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByuserId(user: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBytermId(term: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBydiscountId(discount: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByStartday(startday: LocalDateTime): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByEndday(endday: LocalDateTime): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findBygymId(gym: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByStatus(status: Status): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByTotalcount(totalcount: Int): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByUsedcount(usedcount: Int): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByRemainingcount(remainingcount: Int): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByQrcode(qrcode: String): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.user",
        "order.gym",
        "order.health",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "membership",
        "membership.gym",
        "membership.user",
        "user",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Usehealth>
}
