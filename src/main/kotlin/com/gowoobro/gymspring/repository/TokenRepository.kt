package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.token.Status


@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Token>

    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Token>

    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Token>

    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user WHERE m.token = :token")
    fun findByTokenWithJoin(token: String): List<Token>

    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Token>

    @Query("SELECT m FROM Token m LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Token>
}