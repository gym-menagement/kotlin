package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.entity.UsehealthPatchRequest
import com.gowoobro.gymspring.repository.UsehealthRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.usehealth.Status


@Service
@Transactional
class UsehealthService(private val usehealthRepository: UsehealthRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Usehealth> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return usehealthRepository.findAll(pageable)
    }

    fun findById(id: Long): Usehealth? {
        return usehealthRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return usehealthRepository.count()
    }


    fun findByOrder(order: Long): List<Usehealth> {
        return usehealthRepository.findByOrder(order)
    }

    fun findByHealth(health: Long): List<Usehealth> {
        return usehealthRepository.findByHealth(health)
    }

    fun findByMembership(membership: Long): List<Usehealth> {
        return usehealthRepository.findByMembership(membership)
    }

    fun findByUser(user: Long): List<Usehealth> {
        return usehealthRepository.findByUser(user)
    }

    fun findByTerm(term: Long): List<Usehealth> {
        return usehealthRepository.findByTerm(term)
    }

    fun findByDiscount(discount: Long): List<Usehealth> {
        return usehealthRepository.findByDiscount(discount)
    }

    fun findByStartday(startday: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByStartday(startday)
    }

    fun findByEndday(endday: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByEndday(endday)
    }

    fun findByGym(gym: Long): List<Usehealth> {
        return usehealthRepository.findByGym(gym)
    }

    fun findByStatus(status: Status): List<Usehealth> {
        return usehealthRepository.findByStatus(status)
    }

    fun findByTotalcount(totalcount: Int): List<Usehealth> {
        return usehealthRepository.findByTotalcount(totalcount)
    }

    fun findByUsedcount(usedcount: Int): List<Usehealth> {
        return usehealthRepository.findByUsedcount(usedcount)
    }

    fun findByRemainingcount(remainingcount: Int): List<Usehealth> {
        return usehealthRepository.findByRemainingcount(remainingcount)
    }

    fun findByQrcode(qrcode: String): List<Usehealth> {
        return usehealthRepository.findByQrcode(qrcode)
    }

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByLastuseddate(lastuseddate)
    }

    fun findByDate(date: LocalDateTime): List<Usehealth> {
        return usehealthRepository.findByDate(date)
    }


    fun create(request: UsehealthCreateRequest): Usehealth {
        val entity = Usehealth(
            order = request.order,
            health = request.health,
            membership = request.membership,
            user = request.user,
            term = request.term,
            discount = request.discount,
            startday = request.startday,
            endday = request.endday,
            gym = request.gym,
            status = request.status,
            totalcount = request.totalcount,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            qrcode = request.qrcode,
            lastuseddate = request.lastuseddate,
            date = request.date,
        )
        return usehealthRepository.save(entity)
    }

    fun createBatch(requests: List<UsehealthCreateRequest>): List<Usehealth> {
        val entities = requests.map { request ->
            Usehealth(
                order = request.order,
                health = request.health,
                membership = request.membership,
                user = request.user,
                term = request.term,
                discount = request.discount,
                startday = request.startday,
                endday = request.endday,
                gym = request.gym,
                status = request.status,
                totalcount = request.totalcount,
                usedcount = request.usedcount,
                remainingcount = request.remainingcount,
                qrcode = request.qrcode,
                lastuseddate = request.lastuseddate,
                date = request.date,
            )
        }
        return usehealthRepository.saveAll(entities)
    }

    fun update(request: UsehealthUpdateRequest): Usehealth? {
        val existing = usehealthRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            order = request.order,
            health = request.health,
            membership = request.membership,
            user = request.user,
            term = request.term,
            discount = request.discount,
            startday = request.startday,
            endday = request.endday,
            gym = request.gym,
            status = request.status,
            totalcount = request.totalcount,
            usedcount = request.usedcount,
            remainingcount = request.remainingcount,
            qrcode = request.qrcode,
            lastuseddate = request.lastuseddate,
            date = request.date,
        )
        return usehealthRepository.save(updated)
    }

    fun delete(entity: Usehealth): Boolean {
        return try {
            usehealthRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            usehealthRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Usehealth>): Boolean {
        return try {
            usehealthRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: UsehealthPatchRequest): Usehealth? {
        val existing = usehealthRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            order = request.order ?: existing.order,
            health = request.health ?: existing.health,
            membership = request.membership ?: existing.membership,
            user = request.user ?: existing.user,
            term = request.term ?: existing.term,
            discount = request.discount ?: existing.discount,
            startday = request.startday ?: existing.startday,
            endday = request.endday ?: existing.endday,
            gym = request.gym ?: existing.gym,
            status = request.status ?: existing.status,
            totalcount = request.totalcount ?: existing.totalcount,
            usedcount = request.usedcount ?: existing.usedcount,
            remainingcount = request.remainingcount ?: existing.remainingcount,
            qrcode = request.qrcode ?: existing.qrcode,
            lastuseddate = request.lastuseddate ?: existing.lastuseddate,
            date = request.date ?: existing.date,
        )
        return usehealthRepository.save(updated)
    }
}