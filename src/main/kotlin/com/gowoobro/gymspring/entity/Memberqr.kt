package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.memberqr.Isactive

@Entity
@Table(name = "memberqr_tb")
data class Memberqr(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mq_id")
    val id: Long = 0,
    @Column(name = "mq_user")
    val user: Long = 0L,
    @Column(name = "mq_code")
    val code: String = "",
    @Column(name = "mq_imageurl")
    val imageurl: String = "",
    @Column(name = "mq_isactive")
    val isactive: Isactive = Isactive.INACTIVE,
    @Column(name = "mq_expiredate")
    val expiredate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mq_generateddate")
    val generateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mq_lastuseddate")
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mq_usecount")
    val usecount: Int = 0,
    @Column(name = "mq_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberqrCreateRequest(
    val user: Long = 0L,
    val code: String = "",
    val imageurl: String = "",
    val isactive: Isactive = Isactive.INACTIVE,
    val expiredate: LocalDateTime? = LocalDateTime.now(),
    val generateddate: LocalDateTime? = LocalDateTime.now(),
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val usecount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberqrUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val code: String = "",
    val imageurl: String = "",
    val isactive: Isactive = Isactive.INACTIVE,
    val expiredate: LocalDateTime? = LocalDateTime.now(),
    val generateddate: LocalDateTime? = LocalDateTime.now(),
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val usecount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberqrExtraInfo(
    val isactive: String = "",
)


data class MemberqrResponse(
    val id: Long,
    val user: Long,
    val code: String,
    val imageurl: String,
    val isactive: Isactive,
    val expiredate: LocalDateTime?,
    val generateddate: LocalDateTime?,
    val lastuseddate: LocalDateTime?,
    val usecount: Int,
    val date: LocalDateTime?,

    val extra: MemberqrExtraInfo
){
    companion object {
        fun from(memberqr: Memberqr): MemberqrResponse {
            return MemberqrResponse(
                id = memberqr.id,
                user = memberqr.user,
                code = memberqr.code,
                imageurl = memberqr.imageurl,
                isactive = memberqr.isactive,
                expiredate = memberqr.expiredate,
                generateddate = memberqr.generateddate,
                lastuseddate = memberqr.lastuseddate,
                usecount = memberqr.usecount,
                date = memberqr.date,
                extra = MemberqrExtraInfo(
                    isactive = Isactive.getDisplayName(memberqr.isactive),
                )
            )
        }
    }
}