package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "discount_tb")
data class Discount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_id")
    val id: Long = 0,
    @Column(name = "d_name")
    val name: String = "",
    @Column(name = "d_discount")
    val discount: Int = 0,
    @Column(name = "d_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DiscountCreateRequest(
    val name: String = "",
    val discount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DiscountUpdateRequest(
    val id: Long = 0,
    val name: String = "",
    val discount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class DiscountResponse(
    val id: Long,
    val name: String,
    val discount: Int,
    val date: String?,

    val extra: Map<String, Any?> = emptyMap()
){
    companion object {
        fun from(discount: Discount): DiscountResponse {
            return DiscountResponse(
                id = discount.id,
                name = discount.name,
                discount = discount.discount,
                date = discount.date?.toString()?.replace("T", " ") ?: "",

                extra =  emptyMap()
            )
        }
    }
}