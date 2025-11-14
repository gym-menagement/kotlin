package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Memberqr
import com.gowoobro.gymspring.entity.MemberqrCreateRequest
import com.gowoobro.gymspring.entity.MemberqrUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.memberqr.Isactive


@Repository
interface MemberqrRepository : JpaRepository<Memberqr, Long> {
    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.code = :code")
    fun findByCodeWithJoin(code: String): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.imageurl = :imageurl")
    fun findByImageurlWithJoin(imageurl: String): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.isactive = :isactive")
    fun findByIsactiveWithJoin(isactive: Isactive): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.expiredate = :expiredate")
    fun findByExpiredateWithJoin(expiredate: LocalDateTime): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.generateddate = :generateddate")
    fun findByGenerateddateWithJoin(generateddate: LocalDateTime): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.lastuseddate = :lastuseddate")
    fun findByLastuseddateWithJoin(lastuseddate: LocalDateTime): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.usecount = :usecount")
    fun findByUsecountWithJoin(usecount: Int): List<Memberqr>

    @Query("SELECT m FROM Memberqr m LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Memberqr>
}