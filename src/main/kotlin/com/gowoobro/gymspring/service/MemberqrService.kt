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
        return memberqrRepository.findByUser(user)
    }

    fun findByCode(code: String): List<Memberqr> {
        return memberqrRepository.findByCode(code)
    }

    fun findByImageurl(imageurl: String): List<Memberqr> {
        return memberqrRepository.findByImageurl(imageurl)
    }

    fun findByIsactive(isactive: Int): List<Memberqr> {
        return memberqrRepository.findByIsactive(isactive)
    }

    fun findByExpiredate(expiredate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByExpiredate(expiredate)
    }

    fun findByGenerateddate(generateddate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByGenerateddate(generateddate)
    }

    fun findByLastuseddate(lastuseddate: LocalDateTime): List<Memberqr> {
        return memberqrRepository.findByLastuseddate(lastuseddate)
    }

    fun findByUsecount(usecount: Int): List<Memberqr> {
        return memberqrRepository.findByUsecount(usecount)
    }


    fun create(request: MemberqrCreateRequest): Memberqr {
        val entity = Memberqr(
            user = request.user,
            code = request.code,
            imageurl = request.imageurl,
            isactive = request.isactive,
            expiredate = request.expiredate,
            generateddate = request.generateddate,
            lastuseddate = request.lastuseddate,
            usecount = request.usecount,
        )
        return memberqrRepository.save(entity)
    }

    fun createBatch(requests: List<MemberqrCreateRequest>): List<Memberqr> {
        val entities = requests.map { request ->
            Memberqr(
                user = request.user,
                code = request.code,
                imageurl = request.imageurl,
                isactive = request.isactive,
                expiredate = request.expiredate,
                generateddate = request.generateddate,
                lastuseddate = request.lastuseddate,
                usecount = request.usecount,
            )
        }
        return memberqrRepository.saveAll(entities)
    }

    fun update(request: MemberqrUpdateRequest): Memberqr? {
        val existing = memberqrRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            user = request.user,
            code = request.code,
            imageurl = request.imageurl,
            isactive = request.isactive,
            expiredate = request.expiredate,
            generateddate = request.generateddate,
            lastuseddate = request.lastuseddate,
            usecount = request.usecount,
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