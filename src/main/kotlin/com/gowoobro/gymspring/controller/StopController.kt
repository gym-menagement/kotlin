package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Stop
import com.gowoobro.gymspring.entity.StopCreateRequest
import com.gowoobro.gymspring.entity.StopUpdateRequest
import com.gowoobro.gymspring.service.StopService
import com.gowoobro.gymspring.entity.StopResponse
import com.gowoobro.gymspring.entity.UsehealthResponse
import com.gowoobro.gymspring.service.UsehealthService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/stop")
class StopController(
    private val stopService: StopService, private val usehealthService: UsehealthService) {

    private fun toResponse(stop: Stop):
    StopResponse {
        
        val usehealth = usehealthService.findById(stop.usehelth)
        val usehealthResponse = usehealth?.let{ UsehealthResponse.from(it) }
        
        return StopResponse.from(stop, usehealthResponse)
    }

    @GetMapping
    fun getStops(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<StopResponse>> {
        val result = stopService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getStop(@PathVariable id: Long): ResponseEntity<StopResponse> {
        val result = stopService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/usehelth")
    fun getStopByUsehelth(@RequestParam usehelth: Long): ResponseEntity<List<StopResponse>> {
        val result = stopService.findByUsehelth(usehelth)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/startday")
    fun getStopByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val result = stopService.findByStartday(startday)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/endday")
    fun getStopByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val result = stopService.findByEndday(endday)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/count")
    fun getStopByCount(@RequestParam count: Int): ResponseEntity<List<StopResponse>> {
        val result = stopService.findByCount(count)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getStopByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<StopResponse>> {
        val result = stopService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = stopService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createStop(@RequestBody request: StopCreateRequest): ResponseEntity<StopResponse> {
        return try {
            val result = stopService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createStops(@RequestBody requests: List<StopCreateRequest>): ResponseEntity<List<StopResponse>> {
        return try {
            val result = stopService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateStop(
        @PathVariable id: Long,
        @RequestBody request: StopUpdateRequest
    ): ResponseEntity<StopResponse> {
        val updatedRequest = request.copy(id = id)
        val result = stopService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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