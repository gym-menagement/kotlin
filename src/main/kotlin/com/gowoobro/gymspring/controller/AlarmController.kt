package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import com.gowoobro.gymspring.service.AlarmService
import com.gowoobro.gymspring.entity.AlarmResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.alarm.Type
import com.gowoobro.gymspring.enums.alarm.Status


@RestController
@RequestMapping("/api/alarm")
class AlarmController(private val alarmService: AlarmService) {

    @GetMapping
    fun getAlarms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<AlarmResponse>> {
        val result = alarmService.findAll(page, pageSize)
        val responsePage = result.map { AlarmResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getAlarm(@PathVariable id: Long): ResponseEntity<AlarmResponse> {
        val result = alarmService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(AlarmResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/title")
    fun getAlarmByTitle(@RequestParam title: String): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByTitle(title)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }

    @GetMapping("/search/content")
    fun getAlarmByContent(@RequestParam content: String): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByContent(content)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }

    @GetMapping("/search/type")
    fun getAlarmByType(@RequestParam type: Type): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByType(type)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }

    @GetMapping("/search/status")
    fun getAlarmByStatus(@RequestParam status: Status): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByStatus(status)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }

    @GetMapping("/search/user")
    fun getAlarmByUser(@RequestParam user: Long): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByUser(user)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getAlarmByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<AlarmResponse>> {
        val result = alarmService.findByDate(date)
        return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = alarmService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAlarm(@RequestBody request: AlarmCreateRequest): ResponseEntity<AlarmResponse> {
        return try {
            val result = alarmService.create(request)
            ResponseEntity.ok(AlarmResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAlarms(@RequestBody requests: List<AlarmCreateRequest>): ResponseEntity<List<AlarmResponse>> {
        return try {
            val result = alarmService.createBatch(requests)
            return ResponseEntity.ok(result.map { AlarmResponse.from(it) } )
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
        val result = alarmService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(AlarmResponse.from(result))
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