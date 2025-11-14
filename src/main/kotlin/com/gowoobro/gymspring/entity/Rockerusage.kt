package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal
import com.gowoobro.gymspring.enums.rockerusage.Status

@Entity
@Table(name = "rockerusage_tb")
data class Rockerusage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ru_id")
    val id: Long = 0,

    @Column(name = "ru_rocker", insertable = false, updatable = false)
    val rockerId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ru_rocker")
    val rocker: Rocker? = null,

    @Column(name = "ru_user", insertable = false, updatable = false)
    val userId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ru_user")
    val memberuser: User? = null,

    @Column(name = "ru_membership", insertable = false, updatable = false)
    val membershipId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ru_membership")
    val membership: Membership? = null,
    @Column(name = "ru_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "ru_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "ru_status")
    val status: Status = Status.TERMINATED,
    @Column(name = "ru_deposit")
    val deposit: BigDecimal = BigDecimal.ZERO,
    @Column(name = "ru_monthlyfee")
    val monthlyfee: BigDecimal = BigDecimal.ZERO,
    @Column(name = "ru_note")
    val note: String = "",

    @Column(name = "ru_assignedby", insertable = false, updatable = false)
    val assignedbyId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ru_assignedby")
    val assignedbyuser: User? = null,
    @Column(name = "ru_assigneddate")
    val assigneddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "ru_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockerusageCreateRequest(
    val rocker: Long = 0L,
    val user: Long = 0L,
    val membership: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val deposit: BigDecimal = BigDecimal.ZERO,
    val monthlyfee: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val assignedby: Long = 0L,
    val assigneddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockerusageUpdateRequest(
    val id: Long = 0,
    val rocker: Long = 0L,
    val user: Long = 0L,
    val membership: Long = 0L,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.TERMINATED,
    val deposit: BigDecimal = BigDecimal.ZERO,
    val monthlyfee: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val assignedby: Long = 0L,
    val assigneddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockerusageExtraInfo(
    val status: String = "",

    val rocker: RockerResponse? = null,
    val memberuser: UserResponse? = null,
    val membership: MembershipResponse? = null,
    val assignedbyuser: UserResponse? = null,
)


data class RockerusageResponse(
    val id: Long,
    val rocker: Long,
    val user: Long,
    val membership: Long,
    val startdate: String?,
    val enddate: String?,
    val status: Int,
    val deposit: BigDecimal,
    val monthlyfee: BigDecimal,
    val note: String,
    val assignedby: Long,
    val assigneddate: String?,
    val date: String?,

    val extra: RockerusageExtraInfo
){
    companion object {
        fun from(rockerusage: Rockerusage): RockerusageResponse {
            val rockerResponse = rockerusage.rocker?.let { RockerResponse.from(it) }
            val memberuserResponse = rockerusage.memberuser?.let { UserResponse.from(it) }
            val membershipResponse = rockerusage.membership?.let { MembershipResponse.from(it) }
            val assignedbyuserResponse = rockerusage.assignedbyuser?.let { UserResponse.from(it) }
            return RockerusageResponse(
                id = rockerusage.id,
                rocker = rockerusage.rockerId,
                user = rockerusage.userId,
                membership = rockerusage.membershipId,
                startdate = rockerusage.startdate?.toString()?.replace("T", " ") ?: "",
                enddate = rockerusage.enddate?.toString()?.replace("T", " ") ?: "",
                status = rockerusage.status.ordinal,
                deposit = rockerusage.deposit,
                monthlyfee = rockerusage.monthlyfee,
                note = rockerusage.note,
                assignedby = rockerusage.assignedbyId,
                assigneddate = rockerusage.assigneddate?.toString()?.replace("T", " ") ?: "",
                date = rockerusage.date?.toString()?.replace("T", " ") ?: "",

                extra = RockerusageExtraInfo(
                    status = Status.getDisplayName(rockerusage.status),
                    rocker = rockerResponse,memberuser = memberuserResponse,membership = membershipResponse,assignedbyuser = assignedbyuserResponse,)
                
            )
        }
    }
}