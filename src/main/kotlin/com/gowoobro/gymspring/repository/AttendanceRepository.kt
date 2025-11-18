package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status



@Repository
interface AttendanceRepository : JpaRepository<Attendance, Long> {
    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByuserId(user: Long): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findBymembershipId(membership: Long): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findBygymId(gym: Long): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByType(type: Type): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByMethod(method: Method): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByCheckintime(checkintime: LocalDateTime): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByCheckouttime(checkouttime: LocalDateTime): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByDuration(duration: Int): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByStatus(status: Status): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByNote(note: String): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByIp(ip: String): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByDevice(device: String): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByCreatedby(createdby: Long): List<Attendance>

    @EntityGraph(attributePaths = [
        "user",
        "membership",
        "membership.gym",
        "membership.user",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Attendance>
}
