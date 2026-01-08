package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Qrcode
import com.gowoobro.gymspring.entity.QrcodeCreateRequest
import com.gowoobro.gymspring.entity.QrcodeUpdateRequest
import com.gowoobro.gymspring.entity.QrcodePatchRequest
import com.gowoobro.gymspring.repository.QrcodeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.qrcode.Isactive


@Service
@Transactional
class QrcodeService(private val qrcodeRepository: QrcodeRepository) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<Qrcode> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return qrcodeRepository.findAll(pageable)
    }

    fun findById(id: Long): Qrcode? {
        return qrcodeRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return qrcodeRepository.count()
    }


    fun findByUser(user: Long): List<Qrcode> {
        return qrcodeRepository.findByuserId(user)
    }

    fun findByCode(code: String): List<Qrcode> {
        return qrcodeRepository.findByCode(code)
    }

    fun findByImageurl(imageurl: String): List<Qrcode> {
        return qrcodeRepository.findByImageurl(imageurl)
    }

    fun findByIsactive(isactive: Isactive): List<Qrcode> {
        return qrcodeRepository.findByIsactive(isactive)
    }

    fun findByExpiredate(expiredate: LocalDateTime): List<Qrcode> {
        return qrcodeRepository.findByExpiredate(expiredate)
    }

    fun findByGenerateddate(generateddate: LocalDateTime): List<Qrcode> {
        return qrcodeRepository.findByGenerateddate(generateddate)
    }

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Qrcode> {
        return qrcodeRepository.findByLastuseddate(lastuseddate)
    }

    fun findByUsecount(usecount: Int): List<Qrcode> {
        return qrcodeRepository.findByUsecount(usecount)
    }

    fun findByDate(date: LocalDateTime): List<Qrcode> {
        return qrcodeRepository.findByDate(date)
    }


    fun create(request: QrcodeCreateRequest): Qrcode {
        val entity = Qrcode(
            userId = request.user,
            code = request.code,
            imageurl = request.imageurl,
            isactive = request.isactive,
            expiredate = request.expiredate,
            generateddate = request.generateddate,
            lastuseddate = request.lastuseddate,
            usecount = request.usecount,
            date = request.date,
        )
        return qrcodeRepository.save(entity)
    }

    fun createBatch(requests: List<QrcodeCreateRequest>): List<Qrcode> {
        val entities = requests.map { request ->
            Qrcode(
                userId = request.user,
                code = request.code,
                imageurl = request.imageurl,
                isactive = request.isactive,
                expiredate = request.expiredate,
                generateddate = request.generateddate,
                lastuseddate = request.lastuseddate,
                usecount = request.usecount,
                date = request.date,
            )
        }
        return qrcodeRepository.saveAll(entities)
    }

    fun update(request: QrcodeUpdateRequest): Qrcode? {
        val existing = qrcodeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user,
            code = request.code,
            imageurl = request.imageurl,
            isactive = request.isactive,
            expiredate = request.expiredate,
            generateddate = request.generateddate,
            lastuseddate = request.lastuseddate,
            usecount = request.usecount,
            date = request.date,
        )
        return qrcodeRepository.save(updated)
    }

    fun delete(entity: Qrcode): Boolean {
        return try {
            qrcodeRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            qrcodeRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Qrcode>): Boolean {
        return try {
            qrcodeRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: QrcodePatchRequest): Qrcode? {
        val existing = qrcodeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user ?: existing.userId,
            code = request.code ?: existing.code,
            imageurl = request.imageurl ?: existing.imageurl,
            isactive = request.isactive ?: existing.isactive,
            expiredate = request.expiredate ?: existing.expiredate,
            generateddate = request.generateddate ?: existing.generateddate,
            lastuseddate = request.lastuseddate ?: existing.lastuseddate,
            usecount = request.usecount ?: existing.usecount,
            date = request.date ?: existing.date,
        )
        return qrcodeRepository.save(updated)
    }
}