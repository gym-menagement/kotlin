package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal


@Repository
interface MemberbodyRepository : JpaRepository<Memberbody, Long> {
    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser")
    override fun findAll(pageable: Pageable): Page<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.userId = :memberuser")
    fun findByUserWithJoin(memberuser: Long): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.height = :height")
    fun findByHeightWithJoin(height: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.weight = :weight")
    fun findByWeightWithJoin(weight: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.bodyfat = :bodyfat")
    fun findByBodyfatWithJoin(bodyfat: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.musclemass = :musclemass")
    fun findByMusclemassWithJoin(musclemass: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.bmi = :bmi")
    fun findByBmiWithJoin(bmi: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.skeletalmuscle = :skeletalmuscle")
    fun findBySkeletalmuscleWithJoin(skeletalmuscle: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.bodywater = :bodywater")
    fun findByBodywaterWithJoin(bodywater: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.chest = :chest")
    fun findByChestWithJoin(chest: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.waist = :waist")
    fun findByWaistWithJoin(waist: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.hip = :hip")
    fun findByHipWithJoin(hip: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.arm = :arm")
    fun findByArmWithJoin(arm: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.thigh = :thigh")
    fun findByThighWithJoin(thigh: BigDecimal): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.measureddate = :measureddate")
    fun findByMeasureddateWithJoin(measureddate: LocalDateTime): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.measuredbyId = :measuredbyuser")
    fun findByMeasuredbyWithJoin(measuredbyuser: Long): List<Memberbody>

    @Query("SELECT m FROM Memberbody m LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.measuredbyuser WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Memberbody>
}