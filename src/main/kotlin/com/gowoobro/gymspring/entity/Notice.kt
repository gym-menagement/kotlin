package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notice.Type
import com.gowoobro.gymspring.enums.notice.Ispopup
import com.gowoobro.gymspring.enums.notice.Ispush
import com.gowoobro.gymspring.enums.notice.Target
import com.gowoobro.gymspring.enums.notice.Status

@Entity
@Table(name = "notice_tb")
data class Notice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nt_id")
    val id: Long = 0,
    @Column(name = "nt_gym")
    val gym: Long = 0L,
    @Column(name = "nt_title")
    val title: String = "",
    @Column(name = "nt_content")
    val content: String = "",
    @Column(name = "nt_type")
    val type: Type = Type.0,
    @Column(name = "nt_ispopup")
    val ispopup: Ispopup = Ispopup.0,
    @Column(name = "nt_ispush")
    val ispush: Ispush = Ispush.0,
    @Column(name = "nt_target")
    val target: Target = Target.0,
    @Column(name = "nt_viewcount")
    val viewcount: Int = 0,
    @Column(name = "nt_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_status")
    val status: Status = Status.0,
    @Column(name = "nt_createdby")
    val createdby: Long = 0L,
    @Column(name = "nt_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_updateddate")
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NoticeCreateRequest(
    val gym: Long = 0L,
    val title: String = "",
    val content: String = "",
    val type: Type = Type.0,
    val ispopup: Ispopup = Ispopup.0,
    val ispush: Ispush = Ispush.0,
    val target: Target = Target.0,
    val viewcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.0,
    val createdby: Long = 0L,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NoticeUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val title: String = "",
    val content: String = "",
    val type: Type = Type.0,
    val ispopup: Ispopup = Ispopup.0,
    val ispush: Ispush = Ispush.0,
    val target: Target = Target.0,
    val viewcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.0,
    val createdby: Long = 0L,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)