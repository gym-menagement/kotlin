package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import com.gowoobro.gymspring.repository.MemberbodyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.math.BigDecimal


@Service
@Transactional
class MemberbodyService(private val memberbodyRepository: MemberbodyRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Memberbody> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return memberbodyRepository.findAll(pageable)
    }

    fun findById(id: Long): Memberbody? {
        return memberbodyRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return memberbodyRepository.count()
    }


    fun findByUser(memberuser: Long): List<Memberbody> {
        return memberbodyRepository.findByuserId(memberuser)
    }

    fun findByHeight(height: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByHeight(height)
    }

    fun findByWeight(weight: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByWeight(weight)
    }

    fun findByBodyfat(bodyfat: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByBodyfat(bodyfat)
    }

    fun findByMusclemass(musclemass: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByMusclemass(musclemass)
    }

    fun findByBmi(bmi: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByBmi(bmi)
    }

    fun findBySkeletalmuscle(skeletalmuscle: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findBySkeletalmuscle(skeletalmuscle)
    }

    fun findByBodywater(bodywater: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByBodywater(bodywater)
    }

    fun findByChest(chest: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByChest(chest)
    }

    fun findByWaist(waist: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByWaist(waist)
    }

    fun findByHip(hip: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByHip(hip)
    }

    fun findByArm(arm: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByArm(arm)
    }

    fun findByThigh(thigh: BigDecimal): List<Memberbody> {
        return memberbodyRepository.findByThigh(thigh)
    }

    fun findByNote(note: String): List<Memberbody> {
        return memberbodyRepository.findByNote(note)
    }

    fun findByMeasureddate(measureddate: LocalDateTime): List<Memberbody> {
        return memberbodyRepository.findByMeasureddate(measureddate)
    }

    fun findByMeasuredby(measuredbyuser: Long): List<Memberbody> {
        return memberbodyRepository.findBymeasuredbyId(measuredbyuser)
    }

    fun findByDate(date: LocalDateTime): List<Memberbody> {
        return memberbodyRepository.findByDate(date)
    }


    fun create(request: MemberbodyCreateRequest): Memberbody {
        val entity = Memberbody(
            userId = request.user,
            height = request.height,
            weight = request.weight,
            bodyfat = request.bodyfat,
            musclemass = request.musclemass,
            bmi = request.bmi,
            skeletalmuscle = request.skeletalmuscle,
            bodywater = request.bodywater,
            chest = request.chest,
            waist = request.waist,
            hip = request.hip,
            arm = request.arm,
            thigh = request.thigh,
            note = request.note,
            measureddate = request.measureddate,
            measuredbyId = request.measuredby,
            date = request.date,
        )
        return memberbodyRepository.save(entity)
    }

    fun createBatch(requests: List<MemberbodyCreateRequest>): List<Memberbody> {
        val entities = requests.map { request ->
            Memberbody(
                userId = request.user,
                height = request.height,
                weight = request.weight,
                bodyfat = request.bodyfat,
                musclemass = request.musclemass,
                bmi = request.bmi,
                skeletalmuscle = request.skeletalmuscle,
                bodywater = request.bodywater,
                chest = request.chest,
                waist = request.waist,
                hip = request.hip,
                arm = request.arm,
                thigh = request.thigh,
                note = request.note,
                measureddate = request.measureddate,
                measuredbyId = request.measuredby,
                date = request.date,
            )
        }
        return memberbodyRepository.saveAll(entities)
    }

    fun update(request: MemberbodyUpdateRequest): Memberbody? {
        val existing = memberbodyRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user,
            height = request.height,
            weight = request.weight,
            bodyfat = request.bodyfat,
            musclemass = request.musclemass,
            bmi = request.bmi,
            skeletalmuscle = request.skeletalmuscle,
            bodywater = request.bodywater,
            chest = request.chest,
            waist = request.waist,
            hip = request.hip,
            arm = request.arm,
            thigh = request.thigh,
            note = request.note,
            measureddate = request.measureddate,
            measuredbyId = request.measuredby,
            date = request.date,
        )
        return memberbodyRepository.save(updated)
    }

    fun delete(entity: Memberbody): Boolean {
        return try {
            memberbodyRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            memberbodyRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Memberbody>): Boolean {
        return try {
            memberbodyRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}