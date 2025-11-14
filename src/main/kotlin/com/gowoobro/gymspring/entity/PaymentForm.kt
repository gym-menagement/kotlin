package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "paymentform_tb")
data class Paymentform(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pf_id")
    val id: Long = 0,

    @Column(name = "pf_gym", insertable = false, updatable = false)
    val gymId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_gym")
    val gym: Gym? = null,

    @Column(name = "pf_payment", insertable = false, updatable = false)
    val paymentId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_payment")
    val payment: Payment? = null,

    @Column(name = "pf_type", insertable = false, updatable = false)
    val typeId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_type")
    val paymenttype: Paymenttype? = null,
    @Column(name = "pf_cost")
    val cost: Int = 0,
    @Column(name = "pf_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentformCreateRequest(
    val gym: Long = 0L,
    val payment: Long = 0L,
    val type: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentformUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val payment: Long = 0L,
    val type: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentformExtraInfo(

    val gym: GymResponse? = null,
    val payment: PaymentResponse? = null,
    val paymenttype: PaymenttypeResponse? = null,
)


data class PaymentformResponse(
    val id: Long,
    val gym: Long,
    val payment: Long,
    val type: Long,
    val cost: Int,
    val date: String?,

    val extra: PaymentformExtraInfo
){
    companion object {
        fun from(paymentform: Paymentform): PaymentformResponse {
            val gymResponse = paymentform.gym?.let { GymResponse.from(it) }
            val paymentResponse = paymentform.payment?.let { PaymentResponse.from(it) }
            val paymenttypeResponse = paymentform.paymenttype?.let { PaymenttypeResponse.from(it) }
            return PaymentformResponse(
                id = paymentform.id,
                gym = paymentform.gymId,
                payment = paymentform.paymentId,
                type = paymentform.typeId,
                cost = paymentform.cost,
                date = paymentform.date?.toString()?.replace("T", " ") ?: "",

                extra = PaymentformExtraInfo(
                    gym = gymResponse,payment = paymentResponse,paymenttype = paymenttypeResponse,)
                
            )
        }
    }
}