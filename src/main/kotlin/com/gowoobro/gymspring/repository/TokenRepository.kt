package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Token
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    
    fun findByUser(user: Long): List<Token>
    
    fun findByTokenContaining(token: String): List<Token>
    
    fun findByStatus(status: Int): List<Token>
    
    override fun findAll(pageable: Pageable): Page<Token>
}