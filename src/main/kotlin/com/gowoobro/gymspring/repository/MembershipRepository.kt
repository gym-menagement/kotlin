package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membership.Sex


@Repository
interface MembershipRepository : JpaRepository<Membership, Long> {
    override fun findAll(pageable: Pageable): Page<Membership>

    fun findByGym(gym: Long): List<Membership>

    fun findByUser(user: Long): List<Membership>

    fun findByName(name: String): List<Membership>

    fun findBySex(sex: Sex): List<Membership>

    fun findByBirth(birth: LocalDateTime): List<Membership>

    fun findByPhonenum(phonenum: String): List<Membership>

    fun findByAddress(address: String): List<Membership>

    fun findByImage(image: String): List<Membership>

    fun findByDate(date: LocalDateTime): List<Membership>
}