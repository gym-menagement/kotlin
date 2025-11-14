package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount")
    override fun findAll(pageable: Pageable): Page<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.orderId = :order")
    fun findByOrderWithJoin(order: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.healthId = :health")
    fun findByHealthWithJoin(health: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.rockerId = :rocker")
    fun findByRockerWithJoin(rocker: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.termId = :term")
    fun findByTermWithJoin(term: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.discountId = :discount")
    fun findByDiscountWithJoin(discount: Long): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.startday = :startday")
    fun findByStartdayWithJoin(startday: LocalDateTime): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.endday = :endday")
    fun findByEnddayWithJoin(endday: LocalDateTime): List<Usehealth>

    @Query("SELECT m FROM Usehealth m LEFT JOIN FETCH m.order LEFT JOIN FETCH m.health LEFT JOIN FETCH m.user LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.term LEFT JOIN FETCH m.discount WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Usehealth>
}