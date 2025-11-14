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
import com.gowoobro.gymspring.enums.rockerusage.Status


@Repository
interface RockerusageRepository : JpaRepository<Rockerusage, Long> {
    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser")
    override fun findAll(pageable: Pageable): Page<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.rockerId = :rocker")
    fun findByRockerWithJoin(rocker: Long): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.userId = :memberuser")
    fun findByUserWithJoin(memberuser: Long): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.membershipId = :membership")
    fun findByMembershipWithJoin(membership: Long): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.startdate = :startdate")
    fun findByStartdateWithJoin(startdate: LocalDateTime): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.enddate = :enddate")
    fun findByEnddateWithJoin(enddate: LocalDateTime): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.deposit = :deposit")
    fun findByDepositWithJoin(deposit: BigDecimal): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.monthlyfee = :monthlyfee")
    fun findByMonthlyfeeWithJoin(monthlyfee: BigDecimal): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.assignedbyId = :assignedbyuser")
    fun findByAssignedbyWithJoin(assignedbyuser: Long): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.assigneddate = :assigneddate")
    fun findByAssigneddateWithJoin(assigneddate: LocalDateTime): List<Rockerusage>

    @Query("SELECT m FROM Rockerusage m LEFT JOIN FETCH m.rocker LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.assignedbyuser WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Rockerusage>
}