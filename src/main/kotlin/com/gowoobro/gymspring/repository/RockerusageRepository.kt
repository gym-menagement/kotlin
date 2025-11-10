package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal


@Repository
interface RockerusageRepository : JpaRepository<Rockerusage, Long> {
    override fun findAll(pageable: Pageable): Page<Rockerusage>

    fun findByRocker(rocker: Long): List<Rockerusage>

    fun findByUser(user: Long): List<Rockerusage>

    fun findByMembership(membership: Long): List<Rockerusage>

    fun findByStartdate(startdate: LocalDateTime): List<Rockerusage>

    fun findByEnddate(enddate: LocalDateTime): List<Rockerusage>

    fun findByStatus(status: Int): List<Rockerusage>

    fun findByDeposit(deposit: BigDecimal): List<Rockerusage>

    fun findByMonthlyfee(monthlyfee: BigDecimal): List<Rockerusage>

    fun findByNote(note: String): List<Rockerusage>

    fun findByAssignedby(assignedby: Long): List<Rockerusage>

    fun findByAssigneddate(assigneddate: LocalDateTime): List<Rockerusage>
}