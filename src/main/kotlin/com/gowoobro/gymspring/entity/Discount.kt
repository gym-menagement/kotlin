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
    @Column(name = "d_gym")
    val gym: Long = 0L,
    @Column(name = "d_name")
    val name: String = "",
    @Column(name = "d_discount")
    val discount: Int = 0,
    @Column(name = "d_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class DiscountCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val discount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DiscountUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val discount: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DiscountPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val name: String? = null,
    val discount: Int? = null,
    val date: LocalDateTime? = null,
)

data class DiscountExtraInfo(

    val gym: GymResponse? = null,
)


data class DiscountResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val discount: Int,
    val date: String?,

    val extra: DiscountExtraInfo
){
    companion object {
        fun from(discount: Discount): DiscountResponse {
            val gymResponse = discount.gym?.let { GymResponse.from(it) }
            return DiscountResponse(
                id = discount.id,
                gym = discount.gym,
                name = discount.name,
                discount = discount.discount,
                date = discount.date?.toString()?.replace("T", " ") ?: "",

                extra = DiscountExtraInfo(
                    gym = gymResponse,)
                
            )
        }
    }
}