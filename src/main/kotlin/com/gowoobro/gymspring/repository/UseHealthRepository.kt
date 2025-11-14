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
    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    override fun findAll(pageable: Pageable): Page<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    override fun findById(id: Long): java.util.Optional<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByorderId(order: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByhealthId(health: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByuserId(user: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByrockerId(rocker: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findBytermId(term: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findBydiscountId(discount: Long): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByStartday(startday: LocalDateTime): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByEndday(endday: LocalDateTime): List<Usehealth>

    @EntityGraph(attributePaths = ["order", "health", "user", "rocker", "term", "discount"])
    fun findByDate(date: LocalDateTime): List<Usehealth>
}
