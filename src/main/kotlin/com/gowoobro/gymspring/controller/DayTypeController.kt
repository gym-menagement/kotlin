package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Daytype
import com.gowoobro.gymspring.entity.DaytypeCreateRequest
import com.gowoobro.gymspring.entity.DaytypeUpdateRequest
import com.gowoobro.gymspring.service.DaytypeService
import com.gowoobro.gymspring.entity.DaytypeResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/daytype")
class DaytypeController(
    private val daytypeService: DaytypeService, private val gymService: GymService) {

    private fun toResponse(daytype: Daytype):
    DaytypeResponse {
        
        val gym = gymService.findById(daytype.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return DaytypeResponse.from(daytype, gymResponse)
    }

    @GetMapping
    fun getDaytypes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<DaytypeResponse>> {
        val result = daytypeService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getDaytype(@PathVariable id: Long): ResponseEntity<DaytypeResponse> {
        val result = daytypeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getDaytypeByGym(@RequestParam gym: Long): ResponseEntity<List<DaytypeResponse>> {
        val result = daytypeService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getDaytypeByName(@RequestParam name: String): ResponseEntity<List<DaytypeResponse>> {
        val result = daytypeService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getDaytypeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<DaytypeResponse>> {
        val result = daytypeService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = daytypeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createDaytype(@RequestBody request: DaytypeCreateRequest): ResponseEntity<DaytypeResponse> {
        return try {
            val result = daytypeService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createDaytypes(@RequestBody requests: List<DaytypeCreateRequest>): ResponseEntity<List<DaytypeResponse>> {
        return try {
            val result = daytypeService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = daytypeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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