package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "payment_tb")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    val id: Long = 0,

    @Column(name = "p_gym", insertable = false, updatable = false)
    val gymId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_gym")
    val gym: Gym? = null,

    @Column(name = "p_order", insertable = false, updatable = false)
    val orderId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_order")
    val order: Order? = null,

    @Column(name = "p_membership", insertable = false, updatable = false)
    val membershipId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_membership")
    val membership: Membership? = null,
    @Column(name = "p_cost")
    val cost: Int = 0,
    @Column(name = "p_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentCreateRequest(
    val gym: Long = 0L,
    val order: Long = 0L,
    val membership: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val order: Long = 0L,
    val membership: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentExtraInfo(

    val gym: GymResponse? = null,
    val order: OrderResponse? = null,
    val membership: MembershipResponse? = null,
)


data class PaymentResponse(
    val id: Long,
    val gym: Long,
    val order: Long,
    val membership: Long,
    val cost: Int,
    val date: String?,

    val extra: PaymentExtraInfo
){
    companion object {
        fun from(payment: Payment): PaymentResponse {
            val gymResponse = payment.gym?.let { GymResponse.from(it) }
            val orderResponse = payment.order?.let { OrderResponse.from(it) }
            val membershipResponse = payment.membership?.let { MembershipResponse.from(it) }
            return PaymentResponse(
                id = payment.id,
                gym = payment.gymId,
                order = payment.orderId,
                membership = payment.membershipId,
                cost = payment.cost,
                date = payment.date?.toString()?.replace("T", " ") ?: "",

                extra = PaymentExtraInfo(
                    gym = gymResponse,order = orderResponse,membership = membershipResponse,)
                
            )
        }
    }
}