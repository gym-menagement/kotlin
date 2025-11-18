package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Memberqr
import com.gowoobro.gymspring.entity.MemberqrCreateRequest
import com.gowoobro.gymspring.entity.MemberqrUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.memberqr.Isactive



@Repository
interface MemberqrRepository : JpaRepository<Memberqr, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByuserId(user: Long): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByCode(code: String): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByImageurl(imageurl: String): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByIsactive(isactive: Isactive): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByExpiredate(expiredate: LocalDateTime): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByGenerateddate(generateddate: LocalDateTime): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUsecount(usecount: Int): List<Memberqr>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Memberqr>
}
