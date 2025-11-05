package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import com.gowoobro.gymspring.service.StopService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stop")
class StopController(private val stopService: StopService) {

    @GetMapping
    fun getStops(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Stop>> {
        val result = stopService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getStop(@PathVariable id: Long): ResponseEntity<Stop> {
        val result = stopService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getStopById(@RequestParam id: String): ResponseEntity<List<Stop>> {
        val result = stopService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/usehelth")
    fun getStopByUsehelth(@RequestParam usehelth: String): ResponseEntity<List<Stop>> {
        val result = stopService.findByUsehelth(usehelth)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startday")
    fun getStopByStartday(@RequestParam startday: String): ResponseEntity<List<Stop>> {
        val result = stopService.findByStartday(startday)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/endday")
    fun getStopByEndday(@RequestParam endday: String): ResponseEntity<List<Stop>> {
        val result = stopService.findByEndday(endday)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/count")
    fun getStopByCount(@RequestParam count: String): ResponseEntity<List<Stop>> {
        val result = stopService.findByCount(count)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getStopByDate(@RequestParam date: String): ResponseEntity<List<Stop>> {
        val result = stopService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = stopService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createStop(@RequestBody request: StopCreateRequest): ResponseEntity<Stop> {
        return try {
            val result = stopService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createStops(@RequestBody requests: List<StopCreateRequest>): ResponseEntity<List<Stop>> {
        return try {
            val result = stopService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateStop(
        @PathVariable id: Long,
        @RequestBody request: StopUpdateRequest
    ): ResponseEntity<Stop> {
        val updatedRequest = request.copy(id = id)
        val result = stopService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteStop(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = stopService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteStops(@RequestBody entities: List<Stop>): ResponseEntity<Map<String, Boolean>> {
        val success = stopService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}