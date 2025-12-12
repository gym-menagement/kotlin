package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import com.gowoobro.gymspring.entity.AttendancePatchRequest
import com.gowoobro.gymspring.repository.AttendanceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status


@Service
@Transactional
class AttendanceService(private val attendanceRepository: AttendanceRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Attendance> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return attendanceRepository.findAll(pageable)
    }

    fun findById(id: Long): Attendance? {
        return attendanceRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return attendanceRepository.count()
    }


    fun findByUser(user: Long): List<Attendance> {
        return attendanceRepository.findByUser(user)
    }

    fun findByUsehealth(usehealth: Long): List<Attendance> {
        return attendanceRepository.findByUsehealth(usehealth)
    }

    fun findByGym(gym: Long): List<Attendance> {
        return attendanceRepository.findByGym(gym)
    }

    fun findByType(type: Type): List<Attendance> {
        return attendanceRepository.findByType(type)
    }

    fun findByMethod(method: Method): List<Attendance> {
        return attendanceRepository.findByMethod(method)
    }

    fun findByCheckintime(checkintime: LocalDateTime): List<Attendance> {
        return attendanceRepository.findByCheckintime(checkintime)
    }

    fun findByCheckouttime(checkouttime: LocalDateTime): List<Attendance> {
        return attendanceRepository.findByCheckouttime(checkouttime)
    }

    fun findByDuration(duration: Int): List<Attendance> {
        return attendanceRepository.findByDuration(duration)
    }

    fun findByStatus(status: Status): List<Attendance> {
        return attendanceRepository.findByStatus(status)
    }

    fun findByNote(note: String): List<Attendance> {
        return attendanceRepository.findByNote(note)
    }

    fun findByIp(ip: String): List<Attendance> {
        return attendanceRepository.findByIp(ip)
    }

    fun findByDevice(device: String): List<Attendance> {
        return attendanceRepository.findByDevice(device)
    }

    fun findByCreatedby(createdby: Long): List<Attendance> {
        return attendanceRepository.findByCreatedby(createdby)
    }

    fun findByDate(date: LocalDateTime): List<Attendance> {
        return attendanceRepository.findByDate(date)
    }


    fun create(request: AttendanceCreateRequest): Attendance {
        val entity = Attendance(
            user = request.user,
            usehealth = request.usehealth,
            gym = request.gym,
            type = request.type,
            method = request.method,
            checkintime = request.checkintime,
            checkouttime = request.checkouttime,
            duration = request.duration,
            status = request.status,
            note = request.note,
            ip = request.ip,
            device = request.device,
            createdby = request.createdby,
            date = request.date,
        )
        return attendanceRepository.save(entity)
    }

    fun createBatch(requests: List<AttendanceCreateRequest>): List<Attendance> {
        val entities = requests.map { request ->
            Attendance(
                user = request.user,
                usehealth = request.usehealth,
                gym = request.gym,
                type = request.type,
                method = request.method,
                checkintime = request.checkintime,
                checkouttime = request.checkouttime,
                duration = request.duration,
                status = request.status,
                note = request.note,
                ip = request.ip,
                device = request.device,
                createdby = request.createdby,
                date = request.date,
            )
        }
        return attendanceRepository.saveAll(entities)
    }

    fun update(request: AttendanceUpdateRequest): Attendance? {
        val existing = attendanceRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            user = request.user,
            usehealth = request.usehealth,
            gym = request.gym,
            type = request.type,
            method = request.method,
            checkintime = request.checkintime,
            checkouttime = request.checkouttime,
            duration = request.duration,
            status = request.status,
            note = request.note,
            ip = request.ip,
            device = request.device,
            createdby = request.createdby,
            date = request.date,
        )
        return attendanceRepository.save(updated)
    }

    fun delete(entity: Attendance): Boolean {
        return try {
            attendanceRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            attendanceRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Attendance>): Boolean {
        return try {
            attendanceRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: AttendancePatchRequest): Attendance? {
        val existing = attendanceRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            user = request.user ?: existing.user,
            usehealth = request.usehealth ?: existing.usehealth,
            gym = request.gym ?: existing.gym,
            type = request.type ?: existing.type,
            method = request.method ?: existing.method,
            checkintime = request.checkintime ?: existing.checkintime,
            checkouttime = request.checkouttime ?: existing.checkouttime,
            duration = request.duration ?: existing.duration,
            status = request.status ?: existing.status,
            note = request.note ?: existing.note,
            ip = request.ip ?: existing.ip,
            device = request.device ?: existing.device,
            createdby = request.createdby ?: existing.createdby,
            date = request.date ?: existing.date,
        )
        return attendanceRepository.save(updated)
    }
}