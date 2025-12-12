package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "paymenttype_tb")
data class Paymenttype(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    val id: Long = 0,
    @Column(name = "pt_gym")
    val gym: Long = 0L,
    @Column(name = "pt_name")
    val name: String = "",
    @Column(name = "pt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class PaymenttypeCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymenttypeUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymenttypePatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val name: String? = null,
    val date: LocalDateTime? = null,
)

data class PaymenttypeExtraInfo(

    val gym: GymResponse? = null,
)


data class PaymenttypeResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val date: String?,

    val extra: PaymenttypeExtraInfo
){
    companion object {
        fun from(paymenttype: Paymenttype): PaymenttypeResponse {
            val gymResponse = paymenttype.gym?.let { GymResponse.from(it) }
            return PaymenttypeResponse(
                id = paymenttype.id,
                gym = paymenttype.gym,
                name = paymenttype.name,
                date = paymenttype.date?.toString()?.replace("T", " ") ?: "",

                extra = PaymenttypeExtraInfo(
                    gym = gymResponse,)
                
            )
        }
    }
}