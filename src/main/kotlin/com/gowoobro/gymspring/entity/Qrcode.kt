package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.qrcode.Isactive

@Entity
@Table(name = "qrcode_tb")
data class Qrcode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_id")
    val id: Long = 0,
    @Column(name = "qr_user")
    val userId: Long = 0L,
    @Column(name = "qr_code")
    val code: String = "",
    @Column(name = "qr_imageurl")
    val imageurl: String = "",
    @Column(name = "qr_isactive")
    val isactive: Isactive = Isactive.INACTIVE,
    @Column(name = "qr_expiredate")
    val expiredate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "qr_generateddate")
    val generateddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "qr_lastuseddate")
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "qr_usecount")
    val usecount: Int = 0,
    @Column(name = "qr_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_user", insertable = false, updatable = false)
    var user: User? = null
}

data class QrcodeCreateRequest(
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

data class QrcodeUpdateRequest(
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

data class QrcodePatchRequest(
    val id: Long = 0,
    val user: Long? = null,
    val code: String? = null,
    val imageurl: String? = null,
    val isactive: Isactive? = null,
    val expiredate: LocalDateTime? = null,
    val generateddate: LocalDateTime? = null,
    val lastuseddate: LocalDateTime? = null,
    val usecount: Int? = null,
    val date: LocalDateTime? = null,
)

data class QrcodeExtraInfo(
    val isactive: String = "",

    val user: UserResponse? = null,
)


data class QrcodeResponse(
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

    val extra: QrcodeExtraInfo
){
    companion object {
        fun from(qrcode: Qrcode): QrcodeResponse {
            val userResponse = qrcode.user?.let { UserResponse.from(it) }
            return QrcodeResponse(
                id = qrcode.id,
                user = qrcode.userId,
                code = qrcode.code,
                imageurl = qrcode.imageurl,
                isactive = qrcode.isactive.ordinal,
                expiredate = qrcode.expiredate?.toString()?.replace("T", " ") ?: "",
                generateddate = qrcode.generateddate?.toString()?.replace("T", " ") ?: "",
                lastuseddate = qrcode.lastuseddate?.toString()?.replace("T", " ") ?: "",
                usecount = qrcode.usecount,
                date = qrcode.date?.toString()?.replace("T", " ") ?: "",

                extra = QrcodeExtraInfo(
                    isactive = Isactive.getDisplayName(qrcode.isactive),
                    user = userResponse,)
                
            )
        }
    }
}