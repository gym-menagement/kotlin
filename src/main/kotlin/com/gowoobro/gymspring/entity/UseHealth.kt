package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealth.Status

@Entity
@Table(name = "usehealth_tb")
data class Usehealth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uh_id")
    val id: Long = 0,
    @Column(name = "uh_order")
    val orderId: Long = 0L,
    @Column(name = "uh_health")
    val healthId: Long = 0L,
    @Column(name = "uh_membership")
    val membershipId: Long = 0L,
    @Column(name = "uh_user")
    val userId: Long = 0L,
    @Column(name = "uh_term")
    val termId: Long = 0L,
    @Column(name = "uh_discount")
    val discountId: Long = 0L,
    @Column(name = "uh_startday")
    val startday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_endday")
    val endday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_gym")
    val gymId: Long = 0L,
    @Column(name = "uh_status")
    val status: Status = Status.TERMINATED,
    @Column(name = "uh_totalcount")
    val totalcount: Int = 0,
    @Column(name = "uh_usedcount")
    val usedcount: Int = 0,
    @Column(name = "uh_remainingcount")
    val remainingcount: Int = 0,
    @Column(name = "uh_qrcode")
    val qrcode: String = "",
    @Column(name = "uh_lastuseddate")
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_order", insertable = false, updatable = false)
    var order: Order? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_health", insertable = false, updatable = false)
    var health: Health? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_membership", insertable = false, updatable = false)
    var membership: Membership? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_user", insertable = false, updatable = false)
    var user: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_rocker", insertable = false, updatable = false)
    var rocker: Rocker? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_term", insertable = false, updatable = false)
    var term: Term? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_discount", insertable = false, updatable = false)
    var discount: Discount? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uh_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class UsehealthCreateRequest(
    val order: Long = 0L,
    val health: Long = 0L,
    val membership: Long = 0L,
    val user: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val gym: Long = 0L,
    val status: Status = Status.TERMINATED,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val qrcode: String = "",
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthUpdateRequest(
    val id: Long = 0,
    val order: Long = 0L,
    val health: Long = 0L,
    val membership: Long = 0L,
    val user: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val gym: Long = 0L,
    val status: Status = Status.TERMINATED,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val qrcode: String = "",
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthPatchRequest(
    val id: Long = 0,
    val order: Long? = null,
    val health: Long? = null,
    val membership: Long? = null,
    val user: Long? = null,
    val term: Long? = null,
    val discount: Long? = null,
    val startday: LocalDateTime? = null,
    val endday: LocalDateTime? = null,
    val gym: Long? = null,
    val status: Status? = null,
    val totalcount: Int? = null,
    val usedcount: Int? = null,
    val remainingcount: Int? = null,
    val qrcode: String? = null,
    val lastuseddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class UsehealthExtraInfo(
    val status: String = "",

    val order: OrderResponse? = null,
    val health: HealthResponse? = null,
    val membership: MembershipResponse? = null,
    val user: UserResponse? = null,
    val rocker: RockerResponse? = null,
    val term: TermResponse? = null,
    val discount: DiscountResponse? = null,
    val gym: GymResponse? = null,
)


data class UsehealthResponse(
    val id: Long,
    val order: Long,
    val health: Long,
    val membership: Long,
    val user: Long,
    val term: Long,
    val discount: Long,
    val startday: String?,
    val endday: String?,
    val gym: Long,
    val status: Int,
    val totalcount: Int,
    val usedcount: Int,
    val remainingcount: Int,
    val qrcode: String,
    val lastuseddate: String?,
    val date: String?,

    val extra: UsehealthExtraInfo
){
    companion object {
        fun from(usehealth: Usehealth): UsehealthResponse {
            val orderResponse = usehealth.order?.let { OrderResponse.from(it) }
            val healthResponse = usehealth.health?.let { HealthResponse.from(it) }
            val membershipResponse = usehealth.membership?.let { MembershipResponse.from(it) }
            val userResponse = usehealth.user?.let { UserResponse.from(it) }
            val rockerResponse = usehealth.rocker?.let { RockerResponse.from(it) }
            val termResponse = usehealth.term?.let { TermResponse.from(it) }
            val discountResponse = usehealth.discount?.let { DiscountResponse.from(it) }
            val gymResponse = usehealth.gym?.let { GymResponse.from(it) }
            return UsehealthResponse(
                id = usehealth.id,
                order = usehealth.orderId,
                health = usehealth.healthId,
                membership = usehealth.membershipId,
                user = usehealth.userId,
                term = usehealth.termId,
                discount = usehealth.discountId,
                startday = usehealth.startday?.toString()?.replace("T", " ") ?: "",
                endday = usehealth.endday?.toString()?.replace("T", " ") ?: "",
                gym = usehealth.gymId,
                status = usehealth.status.ordinal,
                totalcount = usehealth.totalcount,
                usedcount = usehealth.usedcount,
                remainingcount = usehealth.remainingcount,
                qrcode = usehealth.qrcode,
                lastuseddate = usehealth.lastuseddate?.toString()?.replace("T", " ") ?: "",
                date = usehealth.date?.toString()?.replace("T", " ") ?: "",

                extra = UsehealthExtraInfo(
                    status = Status.getDisplayName(usehealth.status),
                    order = orderResponse,health = healthResponse,membership = membershipResponse,user = userResponse,rocker = rockerResponse,term = termResponse,discount = discountResponse,gym = gymResponse,)
                
            )
        }
    }
}