package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notificationsetting
import com.gowoobro.gymspring.entity.NotificationsettingCreateRequest
import com.gowoobro.gymspring.entity.NotificationsettingUpdateRequest
import com.gowoobro.gymspring.entity.NotificationsettingPatchRequest
import com.gowoobro.gymspring.service.NotificationsettingService
import com.gowoobro.gymspring.entity.NotificationsettingResponse
import com.gowoobro.gymspring.enums.notificationsetting.Enabled
import com.gowoobro.gymspring.enums.notificationsetting.Membershipexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Membershipnear
import com.gowoobro.gymspring.enums.notificationsetting.Attendanceenc
import com.gowoobro.gymspring.enums.notificationsetting.Gymannounce
import com.gowoobro.gymspring.enums.notificationsetting.Systemnotice
import com.gowoobro.gymspring.enums.notificationsetting.Paymentconfirm
import com.gowoobro.gymspring.enums.notificationsetting.Pauseexpiry
import com.gowoobro.gymspring.enums.notificationsetting.Weeklygoal
import com.gowoobro.gymspring.enums.notificationsetting.Personalrecord
import com.gowoobro.gymspring.enums.notificationsetting.Quietenabled

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/notificationsetting")
class NotificationsettingController(
    private val notificationsettingService: NotificationsettingService) {

    private fun toResponse(notificationsetting: Notificationsetting): NotificationsettingResponse {
        return NotificationsettingResponse.from(notificationsetting)
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
    fun getNotificationsettings(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) enabled: Int?,
        @RequestParam(required = false) membershipexpiry: Int?,
        @RequestParam(required = false) membershipnear: Int?,
        @RequestParam(required = false) attendanceenc: Int?,
        @RequestParam(required = false) gymannounce: Int?,
        @RequestParam(required = false) systemnotice: Int?,
        @RequestParam(required = false) paymentconfirm: Int?,
        @RequestParam(required = false) pauseexpiry: Int?,
        @RequestParam(required = false) weeklygoal: Int?,
        @RequestParam(required = false) personalrecord: Int?,
        @RequestParam(required = false) quietenabled: Int?,
        @RequestParam(required = false) quietstart: String?,
        @RequestParam(required = false) quietend: String?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startupdateddate: LocalDateTime?,
        @RequestParam(required = false) endupdateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || enabled != null || membershipexpiry != null || membershipnear != null || attendanceenc != null || gymannounce != null || systemnotice != null || paymentconfirm != null || pauseexpiry != null || weeklygoal != null || personalrecord != null || quietenabled != null || quietstart != null || quietend != null || startcreateddate != null || endcreateddate != null || startupdateddate != null || endupdateddate != null || startdate != null || enddate != null || false) {
            var filtered = notificationsettingService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (enabled != null) {
                filtered = filtered.filter { it.enabled.ordinal == enabled }
            }
            if (membershipexpiry != null) {
                filtered = filtered.filter { it.membershipexpiry.ordinal == membershipexpiry }
            }
            if (membershipnear != null) {
                filtered = filtered.filter { it.membershipnear.ordinal == membershipnear }
            }
            if (attendanceenc != null) {
                filtered = filtered.filter { it.attendanceenc.ordinal == attendanceenc }
            }
            if (gymannounce != null) {
                filtered = filtered.filter { it.gymannounce.ordinal == gymannounce }
            }
            if (systemnotice != null) {
                filtered = filtered.filter { it.systemnotice.ordinal == systemnotice }
            }
            if (paymentconfirm != null) {
                filtered = filtered.filter { it.paymentconfirm.ordinal == paymentconfirm }
            }
            if (pauseexpiry != null) {
                filtered = filtered.filter { it.pauseexpiry.ordinal == pauseexpiry }
            }
            if (weeklygoal != null) {
                filtered = filtered.filter { it.weeklygoal.ordinal == weeklygoal }
            }
            if (personalrecord != null) {
                filtered = filtered.filter { it.personalrecord.ordinal == personalrecord }
            }
            if (quietenabled != null) {
                filtered = filtered.filter { it.quietenabled.ordinal == quietenabled }
            }
            if (quietstart != null) {
                filtered = filtered.filter { it.quietstart == quietstart }
            }
            if (quietend != null) {
                filtered = filtered.filter { it.quietend == quietend }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startupdateddate != null || endupdateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.updateddate, startupdateddate, endupdateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            notificationsettingService.findAll(0, Int.MAX_VALUE).content
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
    fun getNotificationsetting(@PathVariable id: Long): ResponseEntity<NotificationsettingResponse> {
        val res = notificationsettingService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getNotificationsettingByUser(@RequestParam user: Long): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enabled")
    fun getNotificationsettingByEnabled(@RequestParam enabled: Enabled): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByEnabled(enabled)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/membershipexpiry")
    fun getNotificationsettingByMembershipexpiry(@RequestParam membershipexpiry: Membershipexpiry): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByMembershipexpiry(membershipexpiry)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/membershipnear")
    fun getNotificationsettingByMembershipnear(@RequestParam membershipnear: Membershipnear): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByMembershipnear(membershipnear)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/attendanceenc")
    fun getNotificationsettingByAttendanceenc(@RequestParam attendanceenc: Attendanceenc): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByAttendanceenc(attendanceenc)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gymannounce")
    fun getNotificationsettingByGymannounce(@RequestParam gymannounce: Gymannounce): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByGymannounce(gymannounce)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/systemnotice")
    fun getNotificationsettingBySystemnotice(@RequestParam systemnotice: Systemnotice): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findBySystemnotice(systemnotice)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/paymentconfirm")
    fun getNotificationsettingByPaymentconfirm(@RequestParam paymentconfirm: Paymentconfirm): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByPaymentconfirm(paymentconfirm)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/pauseexpiry")
    fun getNotificationsettingByPauseexpiry(@RequestParam pauseexpiry: Pauseexpiry): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByPauseexpiry(pauseexpiry)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/weeklygoal")
    fun getNotificationsettingByWeeklygoal(@RequestParam weeklygoal: Weeklygoal): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByWeeklygoal(weeklygoal)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/personalrecord")
    fun getNotificationsettingByPersonalrecord(@RequestParam personalrecord: Personalrecord): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByPersonalrecord(personalrecord)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/quietenabled")
    fun getNotificationsettingByQuietenabled(@RequestParam quietenabled: Quietenabled): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByQuietenabled(quietenabled)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/quietstart")
    fun getNotificationsettingByQuietstart(@RequestParam quietstart: String): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByQuietstart(quietstart)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/quietend")
    fun getNotificationsettingByQuietend(@RequestParam quietend: String): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByQuietend(quietend)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getNotificationsettingByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getNotificationsettingByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getNotificationsettingByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<NotificationsettingResponse>> {
        val res = notificationsettingService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = notificationsettingService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createNotificationsetting(@RequestBody request: NotificationsettingCreateRequest): ResponseEntity<NotificationsettingResponse> {
        return try {
            val res = notificationsettingService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createNotificationsettings(@RequestBody requests: List<NotificationsettingCreateRequest>): ResponseEntity<List<NotificationsettingResponse>> {
        return try {
            val res = notificationsettingService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateNotificationsetting(
        @PathVariable id: Long,
        @RequestBody request: NotificationsettingUpdateRequest
    ): ResponseEntity<NotificationsettingResponse> {
        val updatedRequest = request.copy(id = id)
        val res = notificationsettingService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchNotificationsetting(
        @PathVariable id: Long,
        @RequestBody request: NotificationsettingPatchRequest
    ): ResponseEntity<NotificationsettingResponse> {
        val patchRequest = request.copy(id = id)
        val res = notificationsettingService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteNotificationsetting(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = notificationsettingService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteNotificationsettings(@RequestBody entities: List<Notificationsetting>): ResponseEntity<Map<String, Boolean>> {
        val success = notificationsettingService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}