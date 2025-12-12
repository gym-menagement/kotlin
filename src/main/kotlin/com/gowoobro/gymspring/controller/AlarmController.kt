package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import com.gowoobro.gymspring.entity.AlarmPatchRequest
import com.gowoobro.gymspring.service.AlarmService
import com.gowoobro.gymspring.entity.AlarmResponse
import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/alarm")
class AlarmController(
    private val alarmService: AlarmService) {

    private fun toResponse(alarm: Alarm): AlarmResponse {
        return AlarmResponse.from(alarm)
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
    fun getAlarms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (title != null || content != null || type != null || status != null || user != null || startdate != null || enddate != null || false) {
            var filtered = alarmService.findAll(0, Int.MAX_VALUE).content
            if (title != null) {
                filtered = filtered.filter { it.title == title }
            }
            if (content != null) {
                filtered = filtered.filter { it.content == content }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (user != null) {
                filtered = filtered.filter { it.user == user }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            alarmService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getAlarm(@PathVariable id: Long): ResponseEntity<AlarmResponse> {
        val res = alarmService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/title")
    fun getAlarmByTitle(@RequestParam title: String): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByTitle(title)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getAlarmByContent(@RequestParam content: String): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByContent(content)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getAlarmByType(@RequestParam type: Type): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getAlarmByStatus(@RequestParam status: Status): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getAlarmByUser(@RequestParam user: Long): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getAlarmByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<AlarmResponse>> {
        val res = alarmService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = alarmService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAlarm(@RequestBody request: AlarmCreateRequest): ResponseEntity<AlarmResponse> {
        return try {
            val res = alarmService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAlarms(@RequestBody requests: List<AlarmCreateRequest>): ResponseEntity<List<AlarmResponse>> {
        return try {
            val res = alarmService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAlarm(
        @PathVariable id: Long,
        @RequestBody request: AlarmUpdateRequest
    ): ResponseEntity<AlarmResponse> {
        val updatedRequest = request.copy(id = id)
        val res = alarmService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchAlarm(
        @PathVariable id: Long,
        @RequestBody request: AlarmPatchRequest
    ): ResponseEntity<AlarmResponse> {
        val patchRequest = request.copy(id = id)
        val res = alarmService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAlarm(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = alarmService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteAlarms(@RequestBody entities: List<Alarm>): ResponseEntity<Map<String, Boolean>> {
        val success = alarmService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}