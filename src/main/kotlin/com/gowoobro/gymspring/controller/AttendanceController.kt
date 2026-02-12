package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Attendance
import com.gowoobro.gymspring.entity.AttendanceCreateRequest
import com.gowoobro.gymspring.entity.AttendanceUpdateRequest
import com.gowoobro.gymspring.entity.AttendancePatchRequest
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

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getAttendances(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) usehealth: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) method: Int?,
        @RequestParam(required = false) startcheckintime: LocalDateTime?,
        @RequestParam(required = false) endcheckintime: LocalDateTime?,
        @RequestParam(required = false) startcheckouttime: LocalDateTime?,
        @RequestParam(required = false) endcheckouttime: LocalDateTime?,
        @RequestParam(required = false) duration: Int?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) ip: String?,
        @RequestParam(required = false) device: String?,
        @RequestParam(required = false) createdby: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || usehealth != null || gym != null || type != null || method != null || startcheckintime != null || endcheckintime != null || startcheckouttime != null || endcheckouttime != null || duration != null || status != null || note != null || ip != null || device != null || createdby != null || startdate != null || enddate != null || false) {
            var filtered = attendanceService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (usehealth != null) {
                filtered = filtered.filter { it.usehealthId == usehealth }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (method != null) {
                filtered = filtered.filter { it.method.ordinal == method }
            }
            if (startcheckintime != null || endcheckintime != null) {
                filtered = filtered.filter { filterByDateRange(it.checkintime, startcheckintime, endcheckintime) }
            }
            if (startcheckouttime != null || endcheckouttime != null) {
                filtered = filtered.filter { filterByDateRange(it.checkouttime, startcheckouttime, endcheckouttime) }
            }
            if (duration != null) {
                filtered = filtered.filter { it.duration == duration }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (ip != null) {
                filtered = filtered.filter { it.ip == ip }
            }
            if (device != null) {
                filtered = filtered.filter { it.device == device }
            }
            if (createdby != null) {
                filtered = filtered.filter { it.createdby == createdby }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            attendanceService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
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
    fun getCount(
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) startcheckintime: LocalDateTime?,
        @RequestParam(required = false) endcheckintime: LocalDateTime?
    ): ResponseEntity<Map<String, Long>> {
        val count = attendanceService.count(gym, startcheckintime, endcheckintime)
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

    @PatchMapping("/{id}")
    fun patchAttendance(
        @PathVariable id: Long,
        @RequestBody request: AttendancePatchRequest
    ): ResponseEntity<AttendanceResponse> {
        val patchRequest = request.copy(id = id)
        val res = attendanceService.patch(patchRequest)
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