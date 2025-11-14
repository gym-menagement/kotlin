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

import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status


@Repository
interface AttendanceRepository : JpaRepository<Attendance, Long> {
    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.membershipId = :membership")
    fun findByMembershipWithJoin(membership: Long): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.method = :method")
    fun findByMethodWithJoin(method: Method): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.checkintime = :checkintime")
    fun findByCheckintimeWithJoin(checkintime: LocalDateTime): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.checkouttime = :checkouttime")
    fun findByCheckouttimeWithJoin(checkouttime: LocalDateTime): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.duration = :duration")
    fun findByDurationWithJoin(duration: Int): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.ip = :ip")
    fun findByIpWithJoin(ip: String): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.device = :device")
    fun findByDeviceWithJoin(device: String): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.createdby = :createdby")
    fun findByCreatedbyWithJoin(createdby: Long): List<Attendance>

    @Query("SELECT m FROM Attendance m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.membership LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Attendance>
}