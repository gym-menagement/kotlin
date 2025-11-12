package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "memberbody_tb")
data class Memberbody(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_id")
    val id: Long = 0,
    @Column(name = "mb_user")
    val user: Long = 0L,
    @Column(name = "mb_height")
    val height: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_weight")
    val weight: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bodyfat")
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_musclemass")
    val musclemass: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bmi")
    val bmi: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_skeletalmuscle")
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_bodywater")
    val bodywater: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_chest")
    val chest: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_waist")
    val waist: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_hip")
    val hip: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_arm")
    val arm: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_thigh")
    val thigh: BigDecimal = BigDecimal.ZERO,
    @Column(name = "mb_note")
    val note: String = "",
    @Column(name = "mb_measureddate")
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "mb_measuredby")
    val measuredby: Long = 0L,
    @Column(name = "mb_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberbodyCreateRequest(
    val user: Long = 0L,
    val height: BigDecimal = BigDecimal.ZERO,
    val weight: BigDecimal = BigDecimal.ZERO,
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    val musclemass: BigDecimal = BigDecimal.ZERO,
    val bmi: BigDecimal = BigDecimal.ZERO,
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    val bodywater: BigDecimal = BigDecimal.ZERO,
    val chest: BigDecimal = BigDecimal.ZERO,
    val waist: BigDecimal = BigDecimal.ZERO,
    val hip: BigDecimal = BigDecimal.ZERO,
    val arm: BigDecimal = BigDecimal.ZERO,
    val thigh: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    val measuredby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class MemberbodyUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val height: BigDecimal = BigDecimal.ZERO,
    val weight: BigDecimal = BigDecimal.ZERO,
    val bodyfat: BigDecimal = BigDecimal.ZERO,
    val musclemass: BigDecimal = BigDecimal.ZERO,
    val bmi: BigDecimal = BigDecimal.ZERO,
    val skeletalmuscle: BigDecimal = BigDecimal.ZERO,
    val bodywater: BigDecimal = BigDecimal.ZERO,
    val chest: BigDecimal = BigDecimal.ZERO,
    val waist: BigDecimal = BigDecimal.ZERO,
    val hip: BigDecimal = BigDecimal.ZERO,
    val arm: BigDecimal = BigDecimal.ZERO,
    val thigh: BigDecimal = BigDecimal.ZERO,
    val note: String = "",
    val measureddate: LocalDateTime? = LocalDateTime.now(),
    val measuredby: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class MemberbodyResponse(
    val id: Long,
    val user: Long,
    val height: BigDecimal,
    val weight: BigDecimal,
    val bodyfat: BigDecimal,
    val musclemass: BigDecimal,
    val bmi: BigDecimal,
    val skeletalmuscle: BigDecimal,
    val bodywater: BigDecimal,
    val chest: BigDecimal,
    val waist: BigDecimal,
    val hip: BigDecimal,
    val arm: BigDecimal,
    val thigh: BigDecimal,
    val note: String,
    val measureddate: String?,
    val measuredby: Long,
    val date: String?,

){
    companion object {
        fun from(memberbody: Memberbody): MemberbodyResponse {
            return MemberbodyResponse(
                id = memberbody.id,
                user = memberbody.user,
                height = memberbody.height,
                weight = memberbody.weight,
                bodyfat = memberbody.bodyfat,
                musclemass = memberbody.musclemass,
                bmi = memberbody.bmi,
                skeletalmuscle = memberbody.skeletalmuscle,
                bodywater = memberbody.bodywater,
                chest = memberbody.chest,
                waist = memberbody.waist,
                hip = memberbody.hip,
                arm = memberbody.arm,
                thigh = memberbody.thigh,
                note = memberbody.note,
                measureddate = memberbody.measureddate?.toString()?.replace("T", " ") ?: "",
                measuredby = memberbody.measuredby,
                date = memberbody.date?.toString()?.replace("T", " ") ?: "",
            )
        }
    }
}