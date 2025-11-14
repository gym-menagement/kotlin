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
    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.title = :title")
    fun findByTitleWithJoin(title: String): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.content = :content")
    fun findByContentWithJoin(content: String): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.ispopup = :ispopup")
    fun findByIspopupWithJoin(ispopup: Ispopup): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.ispush = :ispush")
    fun findByIspushWithJoin(ispush: Ispush): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.target = :target")
    fun findByTargetWithJoin(target: Target): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.viewcount = :viewcount")
    fun findByViewcountWithJoin(viewcount: Int): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.startdate = :startdate")
    fun findByStartdateWithJoin(startdate: LocalDateTime): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.enddate = :enddate")
    fun findByEnddateWithJoin(enddate: LocalDateTime): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.createdbyId = :user")
    fun findByCreatedbyWithJoin(user: Long): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.createddate = :createddate")
    fun findByCreateddateWithJoin(createddate: LocalDateTime): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.updateddate = :updateddate")
    fun findByUpdateddateWithJoin(updateddate: LocalDateTime): List<Notice>

    @Query("SELECT m FROM Notice m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Notice>
}