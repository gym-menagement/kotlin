package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.token.Status


@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    @EntityGraph(attributePaths = ["user"])
    override fun findAll(pageable: Pageable): Page<Token>

    @EntityGraph(attributePaths = ["user"])
    override fun findById(id: Long): java.util.Optional<Token>

    @EntityGraph(attributePaths = ["user"])
    fun findByuserId(user: Long): List<Token>

    @EntityGraph(attributePaths = ["user"])
    fun findByToken(token: String): List<Token>

    @EntityGraph(attributePaths = ["user"])
    fun findByStatus(status: Status): List<Token>

    @EntityGraph(attributePaths = ["user"])
    fun findByDate(date: LocalDateTime): List<Token>
}
