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
    @Column(name = "o_membership")
    val membership: Long = 0L,
    @Column(name = "o_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class OrderCreateRequest(
    val membership: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class OrderUpdateRequest(
    val id: Long = 0,
    val membership: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class OrderResponse(
    val id: Long,
    val membership: Long,
    val date: String?,

){
    companion object {
        fun from(order: Order): OrderResponse {
            return OrderResponse(
                id = order.id,
                membership = order.membership,
                date = order.date?.toString()?.replace("T", " ") ?: "",
            )
        }
    }
}