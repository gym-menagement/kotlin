package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Memberqr
import com.gowoobro.gymspring.entity.MemberqrCreateRequest
import com.gowoobro.gymspring.entity.MemberqrUpdateRequest
import com.gowoobro.gymspring.repository.MemberqrRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.memberqr.Isactive


@Service
@Transactional
class MemberqrService(private val memberqrRepository: MemberqrRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Memberqr> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return memberqrRepository.findAll(pageable)
    }

    fun findById(id: Long): Memberqr? {
        return memberqrRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return memberqrRepository.count()
    }


    fun findByUser(user: Long): List<Memberqr> {
        return memberqrRepository.findByUserWithJoin(user)
    }

    fun findByCode(code: String): List<Memberqr> {
        return memberqrRepository.findByCodeWithJoin(code)
    }

    fun findByImageurl(imageurl: String): List<Memberqr> {
        return memberqrRepository.findByImageurlWithJoin(imageurl)
    }

    fun findByIsactive(isactive: Isactive): List<Memberqr> {
        return memberqrRepository.findByIsactiveWithJoin(isactive)
    }

    fun findByExpiredate(expiredate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByExpiredateWithJoin(expiredate)
    }

    fun findByGenerateddate(generateddate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByGenerateddateWithJoin(generateddate)
    }

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByLastuseddateWithJoin(lastuseddate)
    }

    fun findByUsecount(usecount: Int): List<Memberqr> {
        return memberqrRepository.findByUsecountWithJoin(usecount)
    }

    fun findByDate(date: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByDateWithJoin(date)
    }


    fun create(request: MemberqrCreateRequest): Memberqr {
        val entity = Memberqr(
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
        return memberqrRepository.save(entity)
    }

    fun createBatch(requests: List<MemberqrCreateRequest>): List<Memberqr> {
        val entities = requests.map { request ->
            Memberqr(
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
        return memberqrRepository.saveAll(entities)
    }

    fun update(request: MemberqrUpdateRequest): Memberqr? {
        val existing = memberqrRepository.findById(request.id).orElse(null) ?: return null

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
        return memberqrRepository.save(updated)
    }

    fun delete(entity: Memberqr): Boolean {
        return try {
            memberqrRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            memberqrRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Memberqr>): Boolean {
        return try {
            memberqrRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}