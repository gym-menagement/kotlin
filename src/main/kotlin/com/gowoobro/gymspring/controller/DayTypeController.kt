package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import com.gowoobro.gymspring.service.DaytypeService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/daytype")
class DaytypeController(private val daytypeService: DaytypeService) {

    @GetMapping
    fun getDaytypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Daytype>> {
        val result = daytypeService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getDaytype(@PathVariable id: Long): ResponseEntity<Daytype> {
        val result = daytypeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getDaytypeByGym(@RequestParam gym: Long): ResponseEntity<List<Daytype>> {
        val result = daytypeService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getDaytypeByName(@RequestParam name: String): ResponseEntity<List<Daytype>> {
        val result = daytypeService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getDaytypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Daytype>> {
        val result = daytypeService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = daytypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDaytype(@RequestBody request: DaytypeCreateRequest): ResponseEntity<Daytype> {
        return try {
            val result = daytypeService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDaytypes(@RequestBody requests: List<DaytypeCreateRequest>): ResponseEntity<List<Daytype>> {
        return try {
            val result = daytypeService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateDaytype(
        @PathVariable id: Long,
        @RequestBody request: DaytypeUpdateRequest
    ): ResponseEntity<Daytype> {
        val updatedRequest = request.copy(id = id)
        val result = daytypeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteDaytype(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = daytypeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteDaytypes(@RequestBody entities: List<Daytype>): ResponseEntity<Map<String, Boolean>> {
        val success = daytypeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}