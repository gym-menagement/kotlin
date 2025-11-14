package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ipblock.Type
import com.gowoobro.gymspring.enums.ipblock.Policy
import com.gowoobro.gymspring.enums.ipblock.Use


@Repository
interface IpblockRepository : JpaRepository<Ipblock, Long> {
    @Query("SELECT m FROM Ipblock m")
    override fun findAll(pageable: Pageable): Page<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.address = :address")
    fun findByAddressWithJoin(address: String): List<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.policy = :policy")
    fun findByPolicyWithJoin(policy: Policy): List<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.use = :use")
    fun findByUseWithJoin(use: Use): List<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.order = :order")
    fun findByOrderWithJoin(order: Int): List<Ipblock>

    @Query("SELECT m FROM Ipblock m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Ipblock>
}