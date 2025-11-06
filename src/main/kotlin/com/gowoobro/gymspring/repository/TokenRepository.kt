package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    override fun findAll(pageable: Pageable): Page<Token>




}