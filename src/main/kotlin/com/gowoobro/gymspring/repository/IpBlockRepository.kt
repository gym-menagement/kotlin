package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IpblockRepository : JpaRepository<Ipblock, Long> {
    override fun findAll(pageable: Pageable): Page<Ipblock>

    override fun findById(id: String): List<Ipblock>

    override fun findByAddress(address: String): List<Ipblock>

    override fun findByType(type: String): List<Ipblock>

    override fun findByPolicy(policy: String): List<Ipblock>

    override fun findByUse(use: String): List<Ipblock>

    override fun findByOrder(order: String): List<Ipblock>

    override fun findByDate(date: String): List<Ipblock>
}