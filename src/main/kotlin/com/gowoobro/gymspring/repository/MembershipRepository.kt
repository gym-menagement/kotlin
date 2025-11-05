package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MembershipRepository : JpaRepository<Membership, Long> {
    override fun findAll(pageable: Pageable): Page<Membership>

    override fun findById(id: String): List<Membership>

    override fun findByGym(gym: String): List<Membership>

    override fun findByUser(user: String): List<Membership>

    override fun findByName(name: String): List<Membership>

    override fun findBySex(sex: String): List<Membership>

    override fun findByBirth(birth: String): List<Membership>

    override fun findByPhonenum(phonenum: String): List<Membership>

    override fun findByAddress(address: String): List<Membership>

    override fun findByImage(image: String): List<Membership>

    override fun findByDate(date: String): List<Membership>
}