package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status

@Entity
@Table(name = "inquiry_tb")
data class Inquiry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iq_id")
    val id: Long = 0,
    @Column(name = "iq_user")
    val userId: Long = 0L,
    @Column(name = "iq_gym")
    val gymId: Long = 0L,
    @Column(name = "iq_type")
    val type: Type = Type.GENERAL,
    @Column(name = "iq_title")
    val title: String = "",
    @Column(name = "iq_content")
    val content: String = "",
    @Column(name = "iq_status")
    val status: Status = Status.WAITING,
    @Column(name = "iq_answer")
    val answer: String = "",
    @Column(name = "iq_answeredby")
    val answeredbyId: Long = 0L,
    @Column(name = "iq_answereddate")
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "iq_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "iq_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iq_user", insertable = false, updatable = false)
    var inquireruser: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iq_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iq_answeredby", insertable = false, updatable = false)
    var answeredbyuser: User? = null
}

data class InquiryCreateRequest(
    val user: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.GENERAL,
    val title: String = "",
    val content: String = "",
    val status: Status = Status.WAITING,
    val answer: String = "",
    val answeredby: Long = 0L,
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class InquiryUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val gym: Long = 0L,
    val type: Type = Type.GENERAL,
    val title: String = "",
    val content: String = "",
    val status: Status = Status.WAITING,
    val answer: String = "",
    val answeredby: Long = 0L,
    val answereddate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class InquiryPatchRequest(
    val id: Long = 0,
    val user: Long? = null,
    val gym: Long? = null,
    val type: Type? = null,
    val title: String? = null,
    val content: String? = null,
    val status: Status? = null,
    val answer: String? = null,
    val answeredby: Long? = null,
    val answereddate: LocalDateTime? = null,
    val createddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class InquiryExtraInfo(
    val type: String = "",
    val status: String = "",

    val inquireruser: UserResponse? = null,
    val gym: GymResponse? = null,
    val answeredbyuser: UserResponse? = null,
)


data class InquiryResponse(
    val id: Long,
    val user: Long,
    val gym: Long,
    val type: Int,
    val title: String,
    val content: String,
    val status: Int,
    val answer: String,
    val answeredby: Long,
    val answereddate: String?,
    val createddate: String?,
    val date: String?,

    val extra: InquiryExtraInfo
){
    companion object {
        fun from(inquiry: Inquiry): InquiryResponse {
            val inquireruserResponse = inquiry.inquireruser?.let { UserResponse.from(it) }
            val gymResponse = inquiry.gym?.let { GymResponse.from(it) }
            val answeredbyuserResponse = inquiry.answeredbyuser?.let { UserResponse.from(it) }
            return InquiryResponse(
                id = inquiry.id,
                user = inquiry.userId,
                gym = inquiry.gymId,
                type = inquiry.type.ordinal,
                title = inquiry.title,
                content = inquiry.content,
                status = inquiry.status.ordinal,
                answer = inquiry.answer,
                answeredby = inquiry.answeredbyId,
                answereddate = inquiry.answereddate?.toString()?.replace("T", " ") ?: "",
                createddate = inquiry.createddate?.toString()?.replace("T", " ") ?: "",
                date = inquiry.date?.toString()?.replace("T", " ") ?: "",

                extra = InquiryExtraInfo(
                    type = Type.getDisplayName(inquiry.type),status = Status.getDisplayName(inquiry.status),
                    inquireruser = inquireruserResponse,gym = gymResponse,answeredbyuser = answeredbyuserResponse,)
                
            )
        }
    }
}