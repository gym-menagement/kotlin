package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal
import com.gowoobro.gymspring.enums.rockerusage.Status


@Repository
interface RockerusageRepository : JpaRepository<Rockerusage, Long> {
    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    override fun findAll(pageable: Pageable): Page<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    override fun findById(id: Long): java.util.Optional<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByrockerId(rocker: Long): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByuserId(memberuser: Long): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findBymembershipId(membership: Long): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByStartdate(startdate: LocalDateTime): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByEnddate(enddate: LocalDateTime): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByStatus(status: Status): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByDeposit(deposit: BigDecimal): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByMonthlyfee(monthlyfee: BigDecimal): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByNote(note: String): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByassignedbyId(assignedbyuser: Long): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByAssigneddate(assigneddate: LocalDateTime): List<Rockerusage>

    @EntityGraph(attributePaths = ["rocker", "memberuser", "membership", "assignedbyuser"])
    fun findByDate(date: LocalDateTime): List<Rockerusage>
}
