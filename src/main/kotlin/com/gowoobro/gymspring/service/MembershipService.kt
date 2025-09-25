package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.repository.MembershipRepository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status
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
    
    fun findByGym(gym: Long): List<Membership> {
        return membershipRepository.findByGym(gym)
    }
    
    fun findByUser(user: Long): List<Membership> {
        return membershipRepository.findByUser(user)
    }
    
    fun findByNameContaining(name: String): List<Membership> {
        return membershipRepository.findByNameContaining(name)
    }
    
    fun findBySex(sex: Int): List<Membership> {
        return membershipRepository.findBySex(sex)
    }
    
    fun findByPhonenumContaining(phonenum: String): List<Membership> {
        return membershipRepository.findByPhonenumContaining(phonenum)
    }
    
    fun findByAddressContaining(address: String): List<Membership> {
        return membershipRepository.findByAddressContaining(address)
    }
    
    fun findByImageContaining(image: String): List<Membership> {
        return membershipRepository.findByImageContaining(image)
    }
    
    fun count(): Long {
        return membershipRepository.count()
    }
    
    fun create(request: MembershipCreateRequest): Membership {
        val entity = Membership(
            gym = request.gym,
            user = request.user,
            name = request.name,
            sex = request.sex,
            birth = request.birth,
            phonenum = request.phonenum,
            address = request.address,
            image = request.image,
            date = request.date,
        )
        return membershipRepository.save(entity)
    }
    
    fun createBatch(requests: List<MembershipCreateRequest>): List<Membership> {
        val entities = requests.map { request ->
            Membership(
                gym = request.gym,
                user = request.user,
                name = request.name,
                sex = request.sex,
                birth = request.birth,
                phonenum = request.phonenum,
                address = request.address,
                image = request.image,
                date = request.date,
            )
        }
        return membershipRepository.saveAll(entities)
    }
    
    fun update(request: MembershipUpdateRequest): Membership? {
        val existing = membershipRepository.findById(request.id).orElse(null) ?: return null
        
        val updated = existing.copy(
            gym = request.gym,
            user = request.user,
            name = request.name,
            sex = request.sex,
            birth = request.birth,
            phonenum = request.phonenum,
            address = request.address,
            image = request.image,
            date = request.date,
        )
        return membershipRepository.save(updated)
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