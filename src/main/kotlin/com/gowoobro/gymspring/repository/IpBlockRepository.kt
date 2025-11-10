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
    override fun findAll(pageable: Pageable): Page<Ipblock>

    fun findByAddress(address: String): List<Ipblock>

    fun findByType(type: Type): List<Ipblock>

    fun findByPolicy(policy: Policy): List<Ipblock>

    fun findByUse(use: Use): List<Ipblock>

    fun findByOrder(order: Int): List<Ipblock>

    fun findByDate(date: LocalDateTime): List<Ipblock>
}