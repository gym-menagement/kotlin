package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import com.gowoobro.gymspring.entity.PushtokenPatchRequest
import com.gowoobro.gymspring.repository.PushtokenRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive


@Service
@Transactional
class PushtokenService(private val pushtokenRepository: PushtokenRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Pushtoken> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return pushtokenRepository.findAll(pageable)
    }

    fun findById(id: Long): Pushtoken? {
        return pushtokenRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return pushtokenRepository.count()
    }


    fun findByUser(user: Long): List<Pushtoken> {
        return pushtokenRepository.findByUser(user)
    }

    fun findByToken(token: String): List<Pushtoken> {
        return pushtokenRepository.findByToken(token)
    }

    fun findByDevicetype(devicetype: String): List<Pushtoken> {
        return pushtokenRepository.findByDevicetype(devicetype)
    }

    fun findByDeviceid(deviceid: String): List<Pushtoken> {
        return pushtokenRepository.findByDeviceid(deviceid)
    }

    fun findByAppversion(appversion: String): List<Pushtoken> {
        return pushtokenRepository.findByAppversion(appversion)
    }

    fun findByIsactive(isactive: Isactive): List<Pushtoken> {
        return pushtokenRepository.findByIsactive(isactive)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Pushtoken> {
        return pushtokenRepository.findByCreateddate(createddate)
    }

    fun findByUpdateddate(updateddate: LocalDateTime): List<Pushtoken> {
        return pushtokenRepository.findByUpdateddate(updateddate)
    }

    fun findByDate(date: LocalDateTime): List<Pushtoken> {
        return pushtokenRepository.findByDate(date)
    }


    fun create(request: PushtokenCreateRequest): Pushtoken {
        val entity = Pushtoken(
            user = request.user,
            token = request.token,
            devicetype = request.devicetype,
            deviceid = request.deviceid,
            appversion = request.appversion,
            isactive = request.isactive,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return pushtokenRepository.save(entity)
    }

    fun createBatch(requests: List<PushtokenCreateRequest>): List<Pushtoken> {
        val entities = requests.map { request ->
            Pushtoken(
                user = request.user,
                token = request.token,
                devicetype = request.devicetype,
                deviceid = request.deviceid,
                appversion = request.appversion,
                isactive = request.isactive,
                createddate = request.createddate,
                updateddate = request.updateddate,
                date = request.date,
            )
        }
        return pushtokenRepository.saveAll(entities)
    }

    fun update(request: PushtokenUpdateRequest): Pushtoken? {
        val existing = pushtokenRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            user = request.user,
            token = request.token,
            devicetype = request.devicetype,
            deviceid = request.deviceid,
            appversion = request.appversion,
            isactive = request.isactive,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return pushtokenRepository.save(updated)
    }

    fun delete(entity: Pushtoken): Boolean {
        return try {
            pushtokenRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            pushtokenRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Pushtoken>): Boolean {
        return try {
            pushtokenRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: PushtokenPatchRequest): Pushtoken? {
        val existing = pushtokenRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            user = request.user ?: existing.user,
            token = request.token ?: existing.token,
            devicetype = request.devicetype ?: existing.devicetype,
            deviceid = request.deviceid ?: existing.deviceid,
            appversion = request.appversion ?: existing.appversion,
            isactive = request.isactive ?: existing.isactive,
            createddate = request.createddate ?: existing.createddate,
            updateddate = request.updateddate ?: existing.updateddate,
            date = request.date ?: existing.date,
        )
        return pushtokenRepository.save(updated)
    }
}