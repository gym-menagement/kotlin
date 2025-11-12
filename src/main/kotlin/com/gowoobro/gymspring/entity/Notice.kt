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
    val type: Type = Type.GENERAL,
    @Column(name = "nt_ispopup")
    val ispopup: Ispopup = Ispopup.NO,
    @Column(name = "nt_ispush")
    val ispush: Ispush = Ispush.NO,
    @Column(name = "nt_target")
    val target: Target = Target.ALL,
    @Column(name = "nt_viewcount")
    val viewcount: Int = 0,
    @Column(name = "nt_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "nt_status")
    val status: Status = Status.PRIVATE,
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
    val type: Type = Type.GENERAL,
    val ispopup: Ispopup = Ispopup.NO,
    val ispush: Ispush = Ispush.NO,
    val target: Target = Target.ALL,
    val viewcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.PRIVATE,
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
    val type: Type = Type.GENERAL,
    val ispopup: Ispopup = Ispopup.NO,
    val ispush: Ispush = Ispush.NO,
    val target: Target = Target.ALL,
    val viewcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.PRIVATE,
    val createdby: Long = 0L,
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val updateddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class NoticeExtraInfo(
    val type: String = "",
    val ispopup: String = "",
    val ispush: String = "",
    val target: String = "",
    val status: String = "",
)


data class NoticeResponse(
    val id: Long,
    val gym: Long,
    val title: String,
    val content: String,
    val type: Int,
    val ispopup: Int,
    val ispush: Int,
    val target: Int,
    val viewcount: Int,
    val startdate: String?,
    val enddate: String?,
    val status: Int,
    val createdby: Long,
    val createddate: String?,
    val updateddate: String?,
    val date: String?,

    val extra: NoticeExtraInfo
){
    companion object {
        fun from(notice: Notice): NoticeResponse {
            return NoticeResponse(
                id = notice.id,
                gym = notice.gym,
                title = notice.title,
                content = notice.content,
                type = notice.type.ordinal,
                ispopup = notice.ispopup.ordinal,
                ispush = notice.ispush.ordinal,
                target = notice.target.ordinal,
                viewcount = notice.viewcount,
                startdate = notice.startdate?.toString()?.replace("T", " ") ?: "",
                enddate = notice.enddate?.toString()?.replace("T", " ") ?: "",
                status = notice.status.ordinal,
                createdby = notice.createdby,
                createddate = notice.createddate?.toString()?.replace("T", " ") ?: "",
                updateddate = notice.updateddate?.toString()?.replace("T", " ") ?: "",
                date = notice.date?.toString()?.replace("T", " ") ?: "",
                extra = NoticeExtraInfo(
                    type = Type.getDisplayName(notice.type),
                    ispopup = Ispopup.getDisplayName(notice.ispopup),
                    ispush = Ispush.getDisplayName(notice.ispush),
                    target = Target.getDisplayName(notice.target),
                    status = Status.getDisplayName(notice.status),
                )
            )
        }
    }
}