package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notice.Type
import com.gowoobro.gymspring.enums.notice.Ispopup
import com.gowoobro.gymspring.enums.notice.Ispush
import com.gowoobro.gymspring.enums.notice.Target
import com.gowoobro.gymspring.enums.notice.Status



@Repository
interface NoticeRepository : JpaRepository<Notice, Long> {
    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findBygymId(gym: Long): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByTitle(title: String): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByContent(content: String): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByType(type: Type): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByIspopup(ispopup: Ispopup): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByIspush(ispush: Ispush): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByTarget(target: Target): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByViewcount(viewcount: Int): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByStartdate(startdate: LocalDateTime): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByEnddate(enddate: LocalDateTime): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByStatus(status: Status): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findBycreatedbyId(user: Long): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByCreateddate(createddate: LocalDateTime): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByUpdateddate(updateddate: LocalDateTime): List<Notice>

    @EntityGraph(attributePaths = [
        "gym",
        "user"
    ])
    fun findByDate(date: LocalDateTime): List<Notice>
}
