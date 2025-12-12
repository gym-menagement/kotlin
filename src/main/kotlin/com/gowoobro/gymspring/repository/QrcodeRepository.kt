package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Qrcode
import com.gowoobro.gymspring.entity.QrcodeCreateRequest
import com.gowoobro.gymspring.entity.QrcodeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.qrcode.Isactive



@Repository
interface QrcodeRepository : JpaRepository<Qrcode, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUser(user: Long): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByCode(code: String): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByImageurl(imageurl: String): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByIsactive(isactive: Isactive): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByExpiredate(expiredate: LocalDateTime): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByGenerateddate(generateddate: LocalDateTime): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUsecount(usecount: Int): List<Qrcode>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Qrcode>
}
