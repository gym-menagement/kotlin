package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import com.gowoobro.gymspring.service.DaytypeService
import com.gowoobro.gymspring.entity.DaytypeResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/daytype")
class DaytypeController(
    private val daytypeService: DaytypeService) {

    private fun toResponse(daytype: Daytype): DaytypeResponse {
        return DaytypeResponse.from(daytype)
    }

    @GetMapping
    fun getDaytypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<DaytypeResponse>> {
        val res = daytypeService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getDaytype(@PathVariable id: Long): ResponseEntity<DaytypeResponse> {
        val res = daytypeService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getDaytypeByGym(@RequestParam gym: Long): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getDaytypeByName(@RequestParam name: String): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getDaytypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<DaytypeResponse>> {
        val res = daytypeService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = daytypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDaytype(@RequestBody request: DaytypeCreateRequest): ResponseEntity<DaytypeResponse> {
        return try {
            val res = daytypeService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDaytypes(@RequestBody requests: List<DaytypeCreateRequest>): ResponseEntity<List<DaytypeResponse>> {
        return try {
            val res = daytypeService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateDaytype(
        @PathVariable id: Long,
        @RequestBody request: DaytypeUpdateRequest
    ): ResponseEntity<DaytypeResponse> {
        val updatedRequest = request.copy(id = id)
        val res = daytypeService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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