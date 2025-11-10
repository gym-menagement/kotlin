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
    override fun findAll(pageable: Pageable): Page<Memberbody>

    fun findByUser(user: Long): List<Memberbody>

    fun findByHeight(height: BigDecimal): List<Memberbody>

    fun findByWeight(weight: BigDecimal): List<Memberbody>

    fun findByBodyfat(bodyfat: BigDecimal): List<Memberbody>

    fun findByMusclemass(musclemass: BigDecimal): List<Memberbody>

    fun findByBmi(bmi: BigDecimal): List<Memberbody>

    fun findBySkeletalmuscle(skeletalmuscle: BigDecimal): List<Memberbody>

    fun findByBodywater(bodywater: BigDecimal): List<Memberbody>

    fun findByChest(chest: BigDecimal): List<Memberbody>

    fun findByWaist(waist: BigDecimal): List<Memberbody>

    fun findByHip(hip: BigDecimal): List<Memberbody>

    fun findByArm(arm: BigDecimal): List<Memberbody>

    fun findByThigh(thigh: BigDecimal): List<Memberbody>

    fun findByNote(note: String): List<Memberbody>

    fun findByMeasureddate(measureddate: LocalDateTime): List<Memberbody>

    fun findByMeasuredby(measuredby: Long): List<Memberbody>

    fun findByDate(date: LocalDateTime): List<Memberbody>
}