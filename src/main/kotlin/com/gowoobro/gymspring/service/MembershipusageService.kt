package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Membershipusage
import com.gowoobro.gymspring.entity.MembershipusageCreateRequest
import com.gowoobro.gymspring.entity.MembershipusageUpdateRequest
import com.gowoobro.gymspring.repository.MembershipusageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membershipusage.Type
import com.gowoobro.gymspring.enums.membershipusage.Status


@Service
@Transactional
class MembershipusageService(private val membershipusageRepository: MembershipusageRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Membershipusage> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return membershipusageRepository.findAll(pageable)
    }

    fun findById(id: Long): Membershipusage? {
        return membershipusageRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return membershipusageRepository.count()
    }


    fun findByGym(gym: Long): List<Membershipusage> {
        return membershipusageRepository.findBygymId(gym)
    }

    fun findByMembership(membership: Long): List<Membershipusage> {
        return membershipusageRepository.findBymembershipId(membership)
    }

    fun findByUser(user: Long): List<Membershipusage> {
        return membershipusageRepository.findByuserId(user)
    }

    fun findByType(type: Type): List<Membershipusage> {
        return membershipusageRepository.findByType(type)
    }

    fun findByTotaldays(totaldays: Int): List<Membershipusage> {
        return membershipusageRepository.findByTotaldays(totaldays)
    }

    fun findByUseddays(useddays: Int): List<Membershipusage> {
        return membershipusageRepository.findByUseddays(useddays)
    }

    fun findByRemainingdays(remainingdays: Int): List<Membershipusage> {
        return membershipusageRepository.findByRemainingdays(remainingdays)
    }

    fun findByTotalcount(totalcount: Int): List<Membershipusage> {
        return membershipusageRepository.findByTotalcount(totalcount)
    }

    fun findByUsedcount(usedcount: Int): List<Membershipusage> {
        return membershipusageRepository.findByUsedcount(usedcount)
    }

    fun findByRemainingcount(remainingcount: Int): List<Membershipusage> {
        return membershipusageRepository.findByRemainingcount(remainingcount)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Membershipusage> {
        return membershipusageRepository.findByStartdate(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Membershipusage> {
        return membershipusageRepository.findByEnddate(enddate)
    }

    fun findByStatus(status: Status): List<Membershipusage> {
        return membershipusageRepository.findByStatus(status)
    }

    fun findByPausedays(pausedays: Int): List<Membershipusage> {
        return membershipusageRepository.findByPausedays(pausedays)
    }

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Membershipusage> {
        return membershipusageRepository.findByLastuseddate(lastuseddate)
    }

    fun findByDate(date: LocalDateTime): List<Membershipusage> {
        return membershipusageRepository.findByDate(date)
    }


    fun create(request: MembershipusageCreateRequest): Membershipusage {
        val entity = Membershipusage(
            gymId = request.gym,
            membershipId = request.membership,
            userId = request.user,
            type = request.type,
            totaldays = request.totaldays,
            useddays = request.useddays,
            remainingdays = request.remainingdays,
            totalcount = request.totalcount,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            pausedays = request.pausedays,
            lastuseddate = request.lastuseddate,
            date = request.date,
        )
        return membershipusageRepository.save(entity)
    }

    fun createBatch(requests: List<MembershipusageCreateRequest>): List<Membershipusage> {
        val entities = requests.map { request ->
            Membershipusage(
                gymId = request.gym,
                membershipId = request.membership,
                userId = request.user,
                type = request.type,
                totaldays = request.totaldays,
                useddays = request.useddays,
                remainingdays = request.remainingdays,
                totalcount = request.totalcount,
                usedcount = request.usedcount,
                remainingcount = request.remainingcount,
                startdate = request.startdate,
                enddate = request.enddate,
                status = request.status,
                pausedays = request.pausedays,
                lastuseddate = request.lastuseddate,
                date = request.date,
            )
        }
        return membershipusageRepository.saveAll(entities)
    }

    fun update(request: MembershipusageUpdateRequest): Membershipusage? {
        val existing = membershipusageRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            membershipId = request.membership,
            userId = request.user,
            type = request.type,
            totaldays = request.totaldays,
            useddays = request.useddays,
            remainingdays = request.remainingdays,
            totalcount = request.totalcount,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            pausedays = request.pausedays,
            lastuseddate = request.lastuseddate,
            date = request.date,
        )
        return membershipusageRepository.save(updated)
    }

    fun delete(entity: Membershipusage): Boolean {
        return try {
            membershipusageRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            membershipusageRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Membershipusage>): Boolean {
        return try {
            membershipusageRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}