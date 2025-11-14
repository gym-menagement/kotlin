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
    @Query("SELECT m FROM Paymenttype m LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Paymenttype>

    @Query("SELECT m FROM Paymenttype m LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Paymenttype>

    @Query("SELECT m FROM Paymenttype m LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Paymenttype>

    @Query("SELECT m FROM Paymenttype m LEFT JOIN FETCH m.gym WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Paymenttype>

    @Query("SELECT m FROM Paymenttype m LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Paymenttype>
}