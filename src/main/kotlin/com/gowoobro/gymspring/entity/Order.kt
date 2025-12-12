package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "order_tb")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    val id: Long = 0,
    @Column(name = "o_user")
    val user: Long = 0L,
    @Column(name = "o_gym")
    val gym: Long = 0L,
    @Column(name = "o_health")
    val health: Long = 0L,
    @Column(name = "o_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user", insertable = false, updatable = false)
    var user: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_health", insertable = false, updatable = false)
    var health: Health? = null
}

data class OrderCreateRequest(
    val user: Long = 0L,
    val gym: Long = 0L,
    val health: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class OrderUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val gym: Long = 0L,
    val health: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class OrderPatchRequest(
    val id: Long = 0,
    val user: Long? = null,
    val gym: Long? = null,
    val health: Long? = null,
    val date: LocalDateTime? = null,
)

data class OrderExtraInfo(

    val user: UserResponse? = null,
    val gym: GymResponse? = null,
    val health: HealthResponse? = null,
)


data class OrderResponse(
    val id: Long,
    val user: Long,
    val gym: Long,
    val health: Long,
    val date: String?,

    val extra: OrderExtraInfo
){
    companion object {
        fun from(order: Order): OrderResponse {
            val userResponse = order.user?.let { UserResponse.from(it) }
            val gymResponse = order.gym?.let { GymResponse.from(it) }
            val healthResponse = order.health?.let { HealthResponse.from(it) }
            return OrderResponse(
                id = order.id,
                user = order.user,
                gym = order.gym,
                health = order.health,
                date = order.date?.toString()?.replace("T", " ") ?: "",

                extra = OrderExtraInfo(
                    user = userResponse,gym = gymResponse,health = healthResponse,)
                
            )
        }
    }
}