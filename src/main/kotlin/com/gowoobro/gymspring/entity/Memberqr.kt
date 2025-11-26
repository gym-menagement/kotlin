package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


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
    val isactive: Int = 0,
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
    val isactive: Int = 0,
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
    val isactive: Int = 0,
    val expiredate: LocalDateTime? = LocalDateTime.now(),
    val generateddate: LocalDateTime? = LocalDateTime.now(),
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val usecount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class MemberqrResponse(
    val id: Long,
    val user: Long,
    val code: String,
    val imageurl: String,
    val isactive: Int,
    val expiredate: String?,
    val generateddate: String?,
    val lastuseddate: String?,
    val usecount: Int,
    val date: String?,

    val extra: Map<String, Any?> = emptyMap()
){
    companion object {
        fun from(memberqr: Memberqr): MemberqrResponse {
            return MemberqrResponse(
                id = memberqr.id,
                user = memberqr.user,
                code = memberqr.code,
                imageurl = memberqr.imageurl,
                isactive = memberqr.isactive,
                expiredate = memberqr.expiredate?.toString()?.replace("T", " ") ?: "",
                generateddate = memberqr.generateddate?.toString()?.replace("T", " ") ?: "",
                lastuseddate = memberqr.lastuseddate?.toString()?.replace("T", " ") ?: "",
                usecount = memberqr.usecount,
                date = memberqr.date?.toString()?.replace("T", " ") ?: "",

                extra =  emptyMap()
            )
        }
    }
}