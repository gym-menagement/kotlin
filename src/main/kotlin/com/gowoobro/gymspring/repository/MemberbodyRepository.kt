package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal


@Repository
interface MemberbodyRepository : JpaRepository<Memberbody, Long> {
    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    override fun findAll(pageable: Pageable): Page<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    override fun findById(id: Long): java.util.Optional<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByuserId(memberuser: Long): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByHeight(height: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByWeight(weight: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByBodyfat(bodyfat: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByMusclemass(musclemass: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByBmi(bmi: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findBySkeletalmuscle(skeletalmuscle: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByBodywater(bodywater: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByChest(chest: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByWaist(waist: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByHip(hip: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByArm(arm: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByThigh(thigh: BigDecimal): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByNote(note: String): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByMeasureddate(measureddate: LocalDateTime): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findBymeasuredbyId(measuredbyuser: Long): List<Memberbody>

    @EntityGraph(attributePaths = ["memberuser", "measuredbyuser"])
    fun findByDate(date: LocalDateTime): List<Memberbody>
}
