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
    @Column(name = "ru_rocker")
    val rocker: Long = 0L,
    @Column(name = "ru_user")
    val user: Long = 0L,
    @Column(name = "ru_membership")
    val membership: Long = 0L,
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
    @Column(name = "ru_assignedby")
    val assignedby: Long = 0L,
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
            return RockerusageResponse(
                id = rockerusage.id,
                rocker = rockerusage.rocker,
                user = rockerusage.user,
                membership = rockerusage.membership,
                startdate = rockerusage.startdate?.toString()?.replace("T", " ") ?: "",
                enddate = rockerusage.enddate?.toString()?.replace("T", " ") ?: "",
                status = rockerusage.status.ordinal,
                deposit = rockerusage.deposit,
                monthlyfee = rockerusage.monthlyfee,
                note = rockerusage.note,
                assignedby = rockerusage.assignedby,
                assigneddate = rockerusage.assigneddate?.toString()?.replace("T", " ") ?: "",
                date = rockerusage.date?.toString()?.replace("T", " ") ?: "",
                extra = RockerusageExtraInfo(
                    status = Status.getDisplayName(rockerusage.status),
                )
            )
        }
    }
}