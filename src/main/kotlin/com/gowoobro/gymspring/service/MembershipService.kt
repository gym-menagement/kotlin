package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.repository.MembershipRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MembershipService(private val membershipRepository: MembershipRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Membership> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return membershipRepository.findAll(pageable)
    }

    fun findById(id: Long): Membership? {
        return membershipRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return membershipRepository.count()
    }













    fun create(request: MembershipCreateRequest): Membership {
        val entity = Membership()
        return membershipRepository.save(entity)
    }

    fun createBatch(requests: List<MembershipCreateRequest>): List<Membership> {
        val entities = requests.map { request ->
            Membership()
        }
        return membershipRepository.saveAll(entities)
    }

    fun update(request: MembershipUpdateRequest): Membership? {
        val existing = membershipRepository.findById(request.id).orElse(null) ?: return null
        return membershipRepository.save(existing)
    }

    fun delete(entity: Membership): Boolean {
        return try {
            membershipRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            membershipRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Membership>): Boolean {
        return try {
            membershipRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}