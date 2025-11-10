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



@Repository
interface NoticeRepository : JpaRepository<Notice, Long> {
    override fun findAll(pageable: Pageable): Page<Notice>

    fun findByGym(gym: Long): List<Notice>

    fun findByTitle(title: String): List<Notice>

    fun findByContent(content: String): List<Notice>

    fun findByType(type: Int): List<Notice>

    fun findByIspopup(ispopup: Int): List<Notice>

    fun findByIspush(ispush: Int): List<Notice>

    fun findByTarget(target: Int): List<Notice>

    fun findByViewcount(viewcount: Int): List<Notice>

    fun findByStartdate(startdate: LocalDateTime): List<Notice>

    fun findByEnddate(enddate: LocalDateTime): List<Notice>

    fun findByStatus(status: Int): List<Notice>

    fun findByCreatedby(createdby: Long): List<Notice>

    fun findByCreateddate(createddate: LocalDateTime): List<Notice>

    fun findByUpdateddate(updateddate: LocalDateTime): List<Notice>
}