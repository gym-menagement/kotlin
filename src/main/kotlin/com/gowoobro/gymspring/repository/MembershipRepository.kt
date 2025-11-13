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
    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user")
    override fun findAll(pageable: Pageable): Page<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.sex = :sex")
    fun findBySexWithJoin(sex: Sex): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.birth = :birth")
    fun findByBirthWithJoin(birth: LocalDateTime): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.phonenum = :phonenum")
    fun findByPhonenumWithJoin(phonenum: String): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.address = :address")
    fun findByAddressWithJoin(address: String): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.image = :image")
    fun findByImageWithJoin(image: String): List<Membership>

    @Query("SELECT m FROM Membership m LEFT JOIN FETCH m.gym LEFT JOIN FETCH m.user WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Membership>
}