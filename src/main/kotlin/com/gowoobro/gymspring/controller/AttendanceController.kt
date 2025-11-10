package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import com.gowoobro.gymspring.service.AttendanceService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status


@RestController
@RequestMapping("/api/attendance")
class AttendanceController(private val attendanceService: AttendanceService) {

    @GetMapping
    fun getAttendances(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Attendance>> {
        val result = attendanceService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getAttendance(@PathVariable id: Long): ResponseEntity<Attendance> {
        val result = attendanceService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getAttendanceByUser(@RequestParam user: Long): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/membership")
    fun getAttendanceByMembership(@RequestParam membership: Long): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByMembership(membership)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/gym")
    fun getAttendanceByGym(@RequestParam gym: Long): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getAttendanceByType(@RequestParam type: Type): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/method")
    fun getAttendanceByMethod(@RequestParam method: Method): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByMethod(method)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/checkintime")
    fun getAttendanceByCheckintime(@RequestParam checkintime: LocalDateTime): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByCheckintime(checkintime)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/checkouttime")
    fun getAttendanceByCheckouttime(@RequestParam checkouttime: LocalDateTime): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByCheckouttime(checkouttime)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/duration")
    fun getAttendanceByDuration(@RequestParam duration: Int): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByDuration(duration)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getAttendanceByStatus(@RequestParam status: Status): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getAttendanceByNote(@RequestParam note: String): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/ip")
    fun getAttendanceByIp(@RequestParam ip: String): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByIp(ip)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/device")
    fun getAttendanceByDevice(@RequestParam device: String): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByDevice(device)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createdby")
    fun getAttendanceByCreatedby(@RequestParam createdby: Long): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByCreatedby(createdby)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getAttendanceByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Attendance>> {
        val result = attendanceService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = attendanceService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAttendance(@RequestBody request: AttendanceCreateRequest): ResponseEntity<Attendance> {
        return try {
            val result = attendanceService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAttendances(@RequestBody requests: List<AttendanceCreateRequest>): ResponseEntity<List<Attendance>> {
        return try {
            val result = attendanceService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAttendance(
        @PathVariable id: Long,
        @RequestBody request: AttendanceUpdateRequest
    ): ResponseEntity<Attendance> {
        val updatedRequest = request.copy(id = id)
        val result = attendanceService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAttendance(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = attendanceService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteAttendances(@RequestBody entities: List<Attendance>): ResponseEntity<Map<String, Boolean>> {
        val success = attendanceService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}