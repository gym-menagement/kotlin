package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Token
import com.gowoobro.gymspring.entity.TokenCreateRequest
import com.gowoobro.gymspring.entity.TokenUpdateRequest
import com.gowoobro.gymspring.repository.TokenRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService(private val tokenRepository: TokenRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Token> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return tokenRepository.findAll(pageable)
    }

    fun findById(id: Long): Token? {
        return tokenRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return tokenRepository.count()
    }








    fun create(request: TokenCreateRequest): Token {
        val entity = Token()
        return tokenRepository.save(entity)
    }

    fun createBatch(requests: List<TokenCreateRequest>): List<Token> {
        val entities = requests.map { request ->
            Token()
        }
        return tokenRepository.saveAll(entities)
    }

    fun update(request: TokenUpdateRequest): Token? {
        val existing = tokenRepository.findById(request.id).orElse(null) ?: return null
        return tokenRepository.save(existing)
    }

    fun delete(entity: Token): Boolean {
        return try {
            tokenRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            tokenRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Token>): Boolean {
        return try {
            tokenRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}