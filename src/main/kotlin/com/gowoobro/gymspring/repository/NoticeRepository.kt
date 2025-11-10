package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    override fun findAll(pageable: Pageable): Page<Notice>

    fun findByGym(gym: Long): List<Notice>

    fun findByTitle(title: String): List<Notice>

    fun findByContent(content: String): List<Notice>

    fun findByType(type: Type): List<Notice>

    fun findByIspopup(ispopup: Ispopup): List<Notice>

    fun findByIspush(ispush: Ispush): List<Notice>

    fun findByTarget(target: Target): List<Notice>

    fun findByViewcount(viewcount: Int): List<Notice>

    fun findByStartdate(startdate: LocalDateTime): List<Notice>

    fun findByEnddate(enddate: LocalDateTime): List<Notice>

    fun findByStatus(status: Status): List<Notice>

    fun findByCreatedby(createdby: Long): List<Notice>

    fun findByCreateddate(createddate: LocalDateTime): List<Notice>

    fun findByUpdateddate(updateddate: LocalDateTime): List<Notice>
}