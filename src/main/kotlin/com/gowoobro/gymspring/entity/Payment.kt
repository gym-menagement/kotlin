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
    @Column(name = "p_gym")
    val gymId: Long = 0L,
    @Column(name = "p_order")
    val orderId: Long = 0L,
    @Column(name = "p_user")
    val userId: Long = 0L,
    @Column(name = "p_cost")
    val cost: Int = 0,
    @Column(name = "p_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_order", insertable = false, updatable = false)
    var order: Order? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_user", insertable = false, updatable = false)
    var user: User? = null
}

data class PaymentCreateRequest(
    val gym: Long = 0L,
    val order: Long = 0L,
    val user: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val order: Long = 0L,
    val user: Long = 0L,
    val cost: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class PaymentExtraInfo(

    val gym: GymResponse? = null,
    val order: OrderResponse? = null,
    val user: UserResponse? = null,
)


data class PaymentResponse(
    val id: Long,
    val gym: Long,
    val order: Long,
    val user: Long,
    val cost: Int,
    val date: String?,

    val extra: PaymentExtraInfo
){
    companion object {
        fun from(payment: Payment): PaymentResponse {
            val gymResponse = payment.gym?.let { GymResponse.from(it) }
            val orderResponse = payment.order?.let { OrderResponse.from(it) }
            val userResponse = payment.user?.let { UserResponse.from(it) }
            return PaymentResponse(
                id = payment.id,
                gym = payment.gymId,
                order = payment.orderId,
                user = payment.userId,
                cost = payment.cost,
                date = payment.date?.toString()?.replace("T", " ") ?: "",

                extra = PaymentExtraInfo(
                    gym = gymResponse,order = orderResponse,user = userResponse,)
                
            )
        }
    }
}