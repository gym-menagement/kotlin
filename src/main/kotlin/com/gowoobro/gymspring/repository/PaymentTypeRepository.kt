package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Paymenttype
import com.gowoobro.gymspring.entity.PaymenttypeCreateRequest
import com.gowoobro.gymspring.entity.PaymenttypeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PaymenttypeRepository : JpaRepository<Paymenttype, Long> {
    override fun findAll(pageable: Pageable): Page<Paymenttype>

    override fun findById(id: String): List<Paymenttype>

    override fun findByGym(gym: String): List<Paymenttype>

    override fun findByName(name: String): List<Paymenttype>

    override fun findByDate(date: String): List<Paymenttype>
}