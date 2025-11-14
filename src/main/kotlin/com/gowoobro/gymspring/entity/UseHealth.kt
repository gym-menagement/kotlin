package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "usehealth_tb")
data class Usehealth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uh_id")
    val id: Long = 0,

    @Column(name = "uh_order", insertable = false, updatable = false)
    val orderId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_order")
    val order: Order? = null,

    @Column(name = "uh_health", insertable = false, updatable = false)
    val healthId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_health")
    val health: Health? = null,

    @Column(name = "uh_user", insertable = false, updatable = false)
    val userId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_user")
    val user: User? = null,

    @Column(name = "uh_rocker", insertable = false, updatable = false)
    val rockerId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_rocker")
    val rocker: Rocker? = null,

    @Column(name = "uh_term", insertable = false, updatable = false)
    val termId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_term")
    val term: Term? = null,

    @Column(name = "uh_discount", insertable = false, updatable = false)
    val discountId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_discount")
    val discount: Discount? = null,
    @Column(name = "uh_startday")
    val startday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_endday")
    val endday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthCreateRequest(
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthUpdateRequest(
    val id: Long = 0,
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthExtraInfo(

    val order: OrderResponse? = null,
    val health: HealthResponse? = null,
    val user: UserResponse? = null,
    val rocker: RockerResponse? = null,
    val term: TermResponse? = null,
    val discount: DiscountResponse? = null,
)


data class UsehealthResponse(
    val id: Long,
    val order: Long,
    val health: Long,
    val user: Long,
    val rocker: Long,
    val term: Long,
    val discount: Long,
    val startday: String?,
    val endday: String?,
    val date: String?,

    val extra: UsehealthExtraInfo
){
    companion object {
        fun from(usehealth: Usehealth): UsehealthResponse {
            val orderResponse = usehealth.order?.let { OrderResponse.from(it) }
            val healthResponse = usehealth.health?.let { HealthResponse.from(it) }
            val userResponse = usehealth.user?.let { UserResponse.from(it) }
            val rockerResponse = usehealth.rocker?.let { RockerResponse.from(it) }
            val termResponse = usehealth.term?.let { TermResponse.from(it) }
            val discountResponse = usehealth.discount?.let { DiscountResponse.from(it) }
            return UsehealthResponse(
                id = usehealth.id,
                order = usehealth.orderId,
                health = usehealth.healthId,
                user = usehealth.userId,
                rocker = usehealth.rockerId,
                term = usehealth.termId,
                discount = usehealth.discountId,
                startday = usehealth.startday?.toString()?.replace("T", " ") ?: "",
                endday = usehealth.endday?.toString()?.replace("T", " ") ?: "",
                date = usehealth.date?.toString()?.replace("T", " ") ?: "",

                extra = UsehealthExtraInfo(
                    order = orderResponse,health = healthResponse,user = userResponse,rocker = rockerResponse,term = termResponse,discount = discountResponse,)
                
            )
        }
    }
}