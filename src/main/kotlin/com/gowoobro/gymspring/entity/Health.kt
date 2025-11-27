package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "health_tb")
data class Health(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    val id: Long = 0,
    @Column(name = "h_category")
    val categoryId: Long = 0L,
    @Column(name = "h_term")
    val termId: Long = 0L,
    @Column(name = "h_name")
    val name: String = "",
    @Column(name = "h_count")
    val count: Int = 0,
    @Column(name = "h_cost")
    val cost: Int = 0,
    @Column(name = "h_discount")
    val discountId: Long = 0L,
    @Column(name = "h_costdiscount")
    val costdiscount: Int = 0,
    @Column(name = "h_content")
    val content: String = "",
    @Column(name = "h_gym")
    val gymId: Long = 0L,
    @Column(name = "h_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_category", insertable = false, updatable = false)
    var healthcategory: Healthcategory? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_term", insertable = false, updatable = false)
    var term: Term? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_discount", insertable = false, updatable = false)
    var discount: Discount? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class HealthCreateRequest(
    val category: Long = 0L,
    val term: Long = 0L,
    val name: String = "",
    val count: Int = 0,
    val cost: Int = 0,
    val discount: Long = 0L,
    val costdiscount: Int = 0,
    val content: String = "",
    val gym: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthUpdateRequest(
    val id: Long = 0,
    val category: Long = 0L,
    val term: Long = 0L,
    val name: String = "",
    val count: Int = 0,
    val cost: Int = 0,
    val discount: Long = 0L,
    val costdiscount: Int = 0,
    val content: String = "",
    val gym: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthExtraInfo(

    val healthcategory: HealthcategoryResponse? = null,
    val term: TermResponse? = null,
    val discount: DiscountResponse? = null,
    val gym: GymResponse? = null,
)


data class HealthResponse(
    val id: Long,
    val category: Long,
    val term: Long,
    val name: String,
    val count: Int,
    val cost: Int,
    val discount: Long,
    val costdiscount: Int,
    val content: String,
    val gym: Long,
    val date: String?,

    val extra: HealthExtraInfo
){
    companion object {
        fun from(health: Health): HealthResponse {
            val healthcategoryResponse = health.healthcategory?.let { HealthcategoryResponse.from(it) }
            val termResponse = health.term?.let { TermResponse.from(it) }
            val discountResponse = health.discount?.let { DiscountResponse.from(it) }
            val gymResponse = health.gym?.let { GymResponse.from(it) }
            return HealthResponse(
                id = health.id,
                category = health.categoryId,
                term = health.termId,
                name = health.name,
                count = health.count,
                cost = health.cost,
                discount = health.discountId,
                costdiscount = health.costdiscount,
                content = health.content,
                gym = health.gymId,
                date = health.date?.toString()?.replace("T", " ") ?: "",

                extra = HealthExtraInfo(
                    healthcategory = healthcategoryResponse,term = termResponse,discount = discountResponse,gym = gymResponse,)
                
            )
        }
    }
}