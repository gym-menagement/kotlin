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




@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    @EntityGraph(attributePaths = [
        "order",
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByrockerId(rocker: Long): List<Usehealth>

    @EntityGraph(attributePaths = [
        "order",
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
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
        "order.membership",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym",
        "user",
        "rocker",
        "rocker.gym",
        "rocker.rockergroup",
        "term",
        "term.gym",
        "term.daytype",
        "discount",
        "discount.gym",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Usehealth>
}
