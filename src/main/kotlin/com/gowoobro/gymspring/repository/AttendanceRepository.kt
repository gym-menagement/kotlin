package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface AttendanceRepository : JpaRepository<Attendance, Long> {
    override fun findAll(pageable: Pageable): Page<Attendance>

    fun findByUser(user: Long): List<Attendance>

    fun findByMembership(membership: Long): List<Attendance>

    fun findByGym(gym: Long): List<Attendance>

    fun findByType(type: Int): List<Attendance>

    fun findByMethod(method: Int): List<Attendance>

    fun findByCheckintime(checkintime: LocalDateTime): List<Attendance>

    fun findByCheckouttime(checkouttime: LocalDateTime): List<Attendance>

    fun findByDuration(duration: Int): List<Attendance>

    fun findByStatus(status: Int): List<Attendance>

    fun findByNote(note: String): List<Attendance>

    fun findByIp(ip: String): List<Attendance>

    fun findByDevice(device: String): List<Attendance>

    fun findByCreatedby(createdby: Long): List<Attendance>

    fun findByDate(date: LocalDateTime): List<Attendance>
}