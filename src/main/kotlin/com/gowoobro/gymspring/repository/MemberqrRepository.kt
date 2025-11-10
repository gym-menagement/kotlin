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



@Repository
interface MemberqrRepository : JpaRepository<Memberqr, Long> {
    override fun findAll(pageable: Pageable): Page<Memberqr>


    fun findByUser(user: Long): List<Memberqr>

    fun findByCode(code: String): List<Memberqr>

    fun findByImageurl(imageurl: String): List<Memberqr>

    fun findByIsactive(isactive: Int): List<Memberqr>

    fun findByExpiredate(expiredate: LocalDateTime): List<Memberqr>

    fun findByGenerateddate(generateddate: LocalDateTime): List<Memberqr>

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Memberqr>

    fun findByUsecount(usecount: Int): List<Memberqr>
}