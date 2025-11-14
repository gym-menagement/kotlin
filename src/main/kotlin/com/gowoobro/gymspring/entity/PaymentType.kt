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

    @Column(name = "pt_gym", insertable = false, updatable = false)
    val gymId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_gym")
    val gym: Gym? = null,
    @Column(name = "pt_name")
    val name: String = "",
    @Column(name = "pt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

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
                gym = paymenttype.gymId,
                name = paymenttype.name,
                date = paymenttype.date?.toString()?.replace("T", " ") ?: "",

                extra = PaymenttypeExtraInfo(
                    gym = gymResponse,)
                
            )
        }
    }
}