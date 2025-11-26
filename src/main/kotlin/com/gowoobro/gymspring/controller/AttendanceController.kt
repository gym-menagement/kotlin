package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import com.gowoobro.gymspring.service.AttendanceService
import com.gowoobro.gymspring.entity.AttendanceResponse
import com.gowoobro.gymspring.enums.attendance.Type
import com.gowoobro.gymspring.enums.attendance.Method
import com.gowoobro.gymspring.enums.attendance.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/attendance")
class AttendanceController(
    private val attendanceService: AttendanceService) {

    private fun toResponse(attendance: Attendance): AttendanceResponse {
        return AttendanceResponse.from(attendance)
    }

    @GetMapping
    fun getAttendances(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<AttendanceResponse>> {
        val res = attendanceService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getAttendance(@PathVariable id: Long): ResponseEntity<AttendanceResponse> {
        val res = attendanceService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getAttendanceByUser(@RequestParam user: Long): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usehealth")
    fun getAttendanceByUsehealth(@RequestParam usehealth: Long): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByUsehealth(usehealth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getAttendanceByGym(@RequestParam gym: Long): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getAttendanceByType(@RequestParam type: Type): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/method")
    fun getAttendanceByMethod(@RequestParam method: Method): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByMethod(method)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkintime")
    fun getAttendanceByCheckintime(@RequestParam checkintime: LocalDateTime): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByCheckintime(checkintime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkouttime")
    fun getAttendanceByCheckouttime(@RequestParam checkouttime: LocalDateTime): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByCheckouttime(checkouttime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getAttendanceByDuration(@RequestParam duration: Int): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByDuration(duration)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getAttendanceByStatus(@RequestParam status: Status): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getAttendanceByNote(@RequestParam note: String): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/ip")
    fun getAttendanceByIp(@RequestParam ip: String): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByIp(ip)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/device")
    fun getAttendanceByDevice(@RequestParam device: String): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByDevice(device)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createdby")
    fun getAttendanceByCreatedby(@RequestParam createdby: Long): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByCreatedby(createdby)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getAttendanceByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<AttendanceResponse>> {
        val res = attendanceService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = attendanceService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAttendance(@RequestBody request: AttendanceCreateRequest): ResponseEntity<AttendanceResponse> {
        return try {
            val res = attendanceService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAttendances(@RequestBody requests: List<AttendanceCreateRequest>): ResponseEntity<List<AttendanceResponse>> {
        return try {
            val res = attendanceService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAttendance(
        @PathVariable id: Long,
        @RequestBody request: AttendanceUpdateRequest
    ): ResponseEntity<AttendanceResponse> {
        val updatedRequest = request.copy(id = id)
        val res = attendanceService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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