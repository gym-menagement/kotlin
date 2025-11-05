package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Alarm
import com.gowoobro.gymspring.entity.AlarmCreateRequest
import com.gowoobro.gymspring.entity.AlarmUpdateRequest
import com.gowoobro.gymspring.service.AlarmService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/alarm")
class AlarmController(private val alarmService: AlarmService) {

    @GetMapping
    fun getAlarms(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Alarm>> {
        val result = alarmService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getAlarm(@PathVariable id: Long): ResponseEntity<Alarm> {
        val result = alarmService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getAlarmById(@RequestParam id: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/title")
    fun getAlarmByTitle(@RequestParam title: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByTitle(title)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/content")
    fun getAlarmByContent(@RequestParam content: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByContent(content)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getAlarmByType(@RequestParam type: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getAlarmByStatus(@RequestParam status: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getAlarmByUser(@RequestParam user: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getAlarmByDate(@RequestParam date: String): ResponseEntity<List<Alarm>> {
        val result = alarmService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = alarmService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAlarm(@RequestBody request: AlarmCreateRequest): ResponseEntity<Alarm> {
        return try {
            val result = alarmService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAlarms(@RequestBody requests: List<AlarmCreateRequest>): ResponseEntity<List<Alarm>> {
        return try {
            val result = alarmService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAlarm(
        @PathVariable id: Long,
        @RequestBody request: AlarmUpdateRequest
    ): ResponseEntity<Alarm> {
        val updatedRequest = request.copy(id = id)
        val result = alarmService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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