package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membershipusage.Type
import com.gowoobro.gymspring.enums.membershipusage.Status

@Entity
@Table(name = "membershipusage_tb")
data class Membershipusage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mu_id")
    val id: Long = 0,
    @Column(name = "mu_membership")
    val membership: Long = 0L,
    @Column(name = "mu_user")
    val user: Long = 0L,
    @Column(name = "mu_type")
    val type: Type = Type.PERIOD_BASED,
    @Column(name = "mu_totaldays")
    val totaldays: Int = 0,
    @Column(name = "mu_useddays")
    val useddays: Int = 0,
    @Column(name = "mu_remainingdays")
    val remainingdays: Int = 0,
    @Column(name = "mu_totalcount")
    val totalcount: Int = 0,
    @Column(name = "mu_usedcount")
    val usedcount: Int = 0,
    @Column(name = "mu_remainingcount")
    val remainingcount: Int = 0,
    @Column(name = "mu_startdate")
    val startdate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_enddate")
    val enddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_status")
    val status: Status = Status.IN_USE,
    @Column(name = "mu_pausedays")
    val pausedays: Int = 0,
    @Column(name = "mu_lastuseddate")
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mu_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipusageCreateRequest(
    val membership: Long = 0L,
    val user: Long = 0L,
    val type: Type = Type.PERIOD_BASED,
    val totaldays: Int = 0,
    val useddays: Int = 0,
    val remainingdays: Int = 0,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.IN_USE,
    val pausedays: Int = 0,
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipusageUpdateRequest(
    val id: Long = 0,
    val membership: Long = 0L,
    val user: Long = 0L,
    val type: Type = Type.PERIOD_BASED,
    val totaldays: Int = 0,
    val useddays: Int = 0,
    val remainingdays: Int = 0,
    val totalcount: Int = 0,
    val usedcount: Int = 0,
    val remainingcount: Int = 0,
    val startdate: LocalDateTime? = LocalDateTime.now(),
    val enddate: LocalDateTime? = LocalDateTime.now(),
    val status: Status = Status.IN_USE,
    val pausedays: Int = 0,
    val lastuseddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MembershipusageExtraInfo(
    val type: String = "",
    val status: String = "",
)


data class MembershipusageResponse(
    val id: Long,
    val membership: Long,
    val user: Long,
    val type: Type,
    val totaldays: Int,
    val useddays: Int,
    val remainingdays: Int,
    val totalcount: Int,
    val usedcount: Int,
    val remainingcount: Int,
    val startdate: LocalDateTime?,
    val enddate: LocalDateTime?,
    val status: Status,
    val pausedays: Int,
    val lastuseddate: LocalDateTime?,
    val date: LocalDateTime?,

    val extra: MembershipusageExtraInfo
){
    companion object {
        fun from(membershipusage: Membershipusage): MembershipusageResponse {
            return MembershipusageResponse(
                id = membershipusage.id,
                membership = membershipusage.membership,
                user = membershipusage.user,
                type = membershipusage.type,
                totaldays = membershipusage.totaldays,
                useddays = membershipusage.useddays,
                remainingdays = membershipusage.remainingdays,
                totalcount = membershipusage.totalcount,
                usedcount = membershipusage.usedcount,
                remainingcount = membershipusage.remainingcount,
                startdate = membershipusage.startdate,
                enddate = membershipusage.enddate,
                status = membershipusage.status,
                pausedays = membershipusage.pausedays,
                lastuseddate = membershipusage.lastuseddate,
                date = membershipusage.date,
                extra = MembershipusageExtraInfo(
                    type = Type.getDisplayName(membershipusage.type),
                    status = Status.getDisplayName(membershipusage.status),
                )
            )
        }
    }
}