package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membership
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status

@Repository
interface MembershipRepository : JpaRepository<Membership, Long> {
    
    fun findByGym(gym: Long): List<Membership>
    
    fun findByUser(user: Long): List<Membership>
    
    fun findByNameContaining(name: String): List<Membership>
    
    fun findBySex(sex: Int): List<Membership>
    
    fun findByPhonenumContaining(phonenum: String): List<Membership>
    
    fun findByAddressContaining(address: String): List<Membership>
    
    fun findByImageContaining(image: String): List<Membership>
    
    override fun findAll(pageable: Pageable): Page<Membership>
}