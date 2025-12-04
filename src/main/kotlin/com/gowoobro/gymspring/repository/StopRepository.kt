package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime




@Repository
interface StopRepository : JpaRepository<Stop, Long> {
    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    override fun findAll(pageable: Pageable): Page<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    override fun findById(id: Long): java.util.Optional<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    fun findByusehealthId(usehealth: Long): List<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    fun findByStartday(startday: LocalDateTime): List<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    fun findByEndday(endday: LocalDateTime): List<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    fun findByCount(count: Int): List<Stop>

    @EntityGraph(attributePaths = [
        "usehealth",
        "usehealth.order",
        "usehealth.health",
        "usehealth.membership",
        "usehealth.user",
        "usehealth.rocker",
        "usehealth.term",
        "usehealth.discount",
        "usehealth.gym"
    ])
    fun findByDate(date: LocalDateTime): List<Stop>
}
