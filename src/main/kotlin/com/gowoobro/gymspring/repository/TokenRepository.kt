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
    override fun findAll(pageable: Pageable): Page<Token>

    fun findByUser(user: Long): List<Token>

    fun findByToken(token: String): List<Token>

    fun findByStatus(status: Status): List<Token>

    fun findByDate(date: LocalDateTime): List<Token>
}