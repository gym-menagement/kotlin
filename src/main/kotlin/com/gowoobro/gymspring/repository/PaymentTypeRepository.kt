package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface PaymenttypeRepository : JpaRepository<Paymenttype, Long> {
    override fun findAll(pageable: Pageable): Page<Paymenttype>


    fun findByGym(gym: Long): List<Paymenttype>

    fun findByName(name: String): List<Paymenttype>

    fun findByDate(date: LocalDateTime): List<Paymenttype>
}